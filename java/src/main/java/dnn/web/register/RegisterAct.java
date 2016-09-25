package dnn.web.register;

import dnn.common.exception.SerException;
import dnn.common.json.ResponseText;
import dnn.common.mails.Email;
import dnn.common.mails.EmailUtil;
import dnn.common.utils.CryptUtil;
import dnn.common.utils.PasswordHash;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.enums.Status;
import dnn.service.user.ISerUser;
import dnn.web.CaptchaAct;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/23.
 */
@RestController
@RequestMapping("register")
public class RegisterAct {

    @Autowired
    ISerUser serUser;
    @Autowired
    PropertiesFactoryBean propertiesFactoryBean;

    @GetMapping("index")
    public ModelAndView index(){
        return new ModelAndView("register/index");
    }

    @GetMapping("mail")
    public ModelAndView mail(){
        return new ModelAndView("register/mail");
    }

    @GetMapping("sendMailInfo")
    public ModelAndView sendMailInfo(){
        return new ModelAndView("register/sendMailInfo");
    }

    @GetMapping("accountInfo")
    public ModelAndView accountInfo(){
        return new ModelAndView("register/accountInfo");
    }
    @GetMapping("finish")
    public ModelAndView finish(){
        return new ModelAndView("register/finish");
    }

    @PostMapping("perfect")
    public ResponseText register(User user) throws Throwable {
        user.setStatus(Status.UNREVIEW);//未审核
        //// TODO: 16-9-23  
//        serUser.UpdateByCis2(user,"details.company",user.getDetails().getCompany());
//        serUser.UpdateByCis2(user,"details.address",user.getDetails().getAddress());
//        serUser.UpdateByCis2(user,"details.postcodes",user.getDetails().getPostcodes());
//        serUser.UpdateByCis2(user,"details.telephone",user.getDetails().getTelephone());
//        serUser.UpdateByCis2(user,"details.fax",user.getDetails().getFax());
//        serUser.UpdateByCis2(user,"details.contact",user.getDetails().getContact());
        return new ResponseText();
    }

    /**
     * 注册邮箱激活
     *
     * @param code
     * @return
     * @throws Throwable
     */
    @PostMapping("activate")
    public ResponseText email_activate(String code, String sid, String uid, HttpServletRequest request) throws Throwable {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(sid) || StringUtils.isBlank(uid)) {
            throw new SerException("激活链接地扯不正确,请检查");
        }
        ResponseText responseText = new ResponseText();
        if (StringUtils.isBlank(sid)) {
            sid = request.getParameter("sid");
        }
        String str = CryptUtil.decryptBASE64(sid);
        String time = str.split("#")[0];
        String email = str.split("#")[1];
        LocalDateTime d_time = LocalDateTime.parse(time);
        if (PasswordHash.validatePassword(email, code)  //是否比配,时间是否超时
                && d_time.plusHours(1).isAfter(LocalDateTime.now())) {
            Map<String, Object> map = new HashMap<>(1);
            map.put("id", uid);
            User user = serUser.findOne(map);
            if (null != user && user.getStatus() == Status.NOACTIVE) {
//                user.setStatus(Status.UNREVIEW);//未审核
//       todo         serUser.UpdateByCis2(user, "status", Status.UNREVIEW);
            }
            responseText.setMsg("注册成功");
        } else {
            responseText.setErrno(2);
            responseText.setMsg("验证超时,必需在一小时内验证");
        }

        return responseText;
    }

    /**
     * 注册邮箱验证
     */
    @PostMapping("valid")
    public ResponseText email_valid(String captchaVal, String email, String password, HttpServletRequest request) throws Throwable {
        String ACTIVE_PATH = propertiesFactoryBean.getObject().getProperty("domain.name");
        if(StringUtils.isBlank(captchaVal)){
            throw new SerException("验证码不能为空!");
        }
        if(!CaptchaAct.validCaptcha(captchaVal)){
            throw new SerException("请输入正确的验证码!");
        }
        if (StringUtils.isBlank(email)) {
            throw new SerException("邮箱地扯不能为空!");
        }
        if (StringUtils.isBlank(password)) {
            throw new SerException("密码不能为空!");
        }
        if (StringUtils.isBlank(ACTIVE_PATH)) {
            throw new SerException("激活回调地扯不能为空!");
        }
        String subject = "Feedback 注册验证";
        String content = null;
        StrBuilder sb = new StrBuilder();
        ModelAndView modelAndView = null;
        Map<String, Object> map = new HashMap<>(1);
        map.put("username", email);
        User user = serUser.findOne(map);
        if (null != user && user.getStatus() == Status.THAW) {
            ResponseText responseText = new ResponseText();
            responseText.setMsg("该邮箱已被注册!");
            responseText.setErrno(2);
            return responseText;
        }else if (null != user && user.getStatus() == Status.UNREVIEW){
            ResponseText responseText = new ResponseText();
            responseText.setMsg("该邮箱已激活,请联系管理员进行审核使用!");
            responseText.setErrno(2);
            return responseText;
        } else {
            sb.append("<a href='link_url'>请点击该链接</a>或复制以下链接到浏览器进行注册验证:<br/><br/>");
            Email em = new Email(email);
            StringBuilder urlSb = new StringBuilder(ACTIVE_PATH);
            urlSb.append("?code=");
            String code = PasswordHash.createHash(email);
            urlSb.append(code);
            urlSb.append("&sid=");
            String sid = CryptUtil.encryptBASE64(LocalDateTime.now().toString() + "#" + email);
            urlSb.append(sid);

            User newUser = new User();
            newUser.setUsername(email);
            newUser.setPassword(password);
            newUser.setUserType(UserType.CUSTOM);
            newUser.setStatus(Status.NOACTIVE);//未注册

            User _user = null;
            map = new HashMap<>(1);
            map.put("username", email);
            if (null != user &&user.getStatus().equals(Status.NOACTIVE)) {
                _user = serUser.findOne(map);
            }else{
                serUser.save(newUser);
                _user = serUser.findOne(map);
            }
            urlSb.append("&uid=");

            if (null == _user) {
                throw new SerException("注册失败");
            }
            urlSb.append(_user.getId());

            sb.append(urlSb.toString());
            content = sb.toString();
            content = sb.toString().replace("link_url", urlSb.toString());
            em.initEmailInfo(subject, content);
            boolean status = EmailUtil.SendMail(em);
            String host = email.split("@")[1];
            if(status){
                return new ResponseText("https://mail." + host);
            }else{
                ResponseText responseText = new ResponseText();
                responseText.setMsg("邮件发送失败,请联系管理员");
                responseText.setErrno(3);
                return responseText;
            }
        }
    }
}


