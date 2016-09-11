package dnn.web.user;

/**
 * Created by lgq on 16-9-7.
 */

import dnn.common.exception.SerException;
import dnn.common.json.ResponseText;
import dnn.common.mails.Email;
import dnn.common.mails.EmailUtil;
import dnn.common.utils.CryptUtil;
import dnn.common.utils.PasswordHash;
import dnn.common.utils.PropertyUtil;
import dnn.common.validation.Add;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.enums.Status;
import dnn.service.user.ISerUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lgq on 16-9-3.
 */
@RestController
@RequestMapping("register")
public class RegisterAct {

    @Autowired
    private ISerUser serUser;

    @RequestMapping(value = {""})
    public ModelAndView index() {
        return new ModelAndView("redirect:/login");
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     * @throws Throwable
     */
    @PostMapping("reg")
    public ResponseText register(@Validated(Add.class) User user, BindingResult result) throws Throwable {
        user.setStatus(Status.UNREVIEW);//未审核
        serUser.save(user);
        return new ResponseText<>();
    }

    /**
     * 注册邮箱激活
     *
     * @param code
     * @return
     * @throws Throwable
     */
    @GetMapping("activate")
    public ResponseText email_activate(String code, String sid,String uid, HttpServletRequest request) throws Throwable {
        if(StringUtils.isBlank(code)||StringUtils.isBlank(sid)||StringUtils.isBlank(uid)){
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
            if(null!=user&&user.getStatus()==Status.NOACTIVE){
//                user.setStatus(Status.UNREVIEW);//未审核
                serUser.UpdateByCis2(user,"status",Status.UNREVIEW);
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
     *
     * @param email
     * @return
     * @throws Throwable
     */
    @PostMapping("valid")
    public ResponseText email_valid(String email,String password,HttpServletRequest request) throws Throwable {
        if(StringUtils.isBlank(email)){
            throw new SerException("邮箱地扯不能为空!");
        }
        if(StringUtils.isBlank(password)){
            throw new SerException("密码不能为空!");
        }
        String subject = "Feedback 注册验证";
        String content = null;
        StrBuilder sb = new StrBuilder();
        ModelAndView modelAndView = null;
        Map<String, Object> map = new HashMap<>(1);
        map.put("details.email", email);
        User user = serUser.findOne(map);
        if (null != serUser.findOne(map)&&user.getStatus()!=Status.THAW) {
            ResponseText responseText =new ResponseText();
            responseText.setMsg("该邮箱已被注册!");
            responseText.setErrno(2);
            return responseText;
        } else {
            String activeUrl = PropertyUtil.getInstance().getProperty("domain.name")+"/register/activate";
            sb.append("<a href='link_url'>请点击该链接</a>或复制以下链接到浏览器进行注册验证:<br/><br/>");
            Email em = new Email(email);
            StringBuilder urlSb = new StringBuilder(activeUrl);
            urlSb.append("?code=");
            urlSb.append(PasswordHash.createHash(email));
            urlSb.append("&sid=");
            urlSb.append(CryptUtil.encryptBASE64(LocalDateTime.now().toString() + "#" + email));

            User newUser = new User();
            newUser.setUsername(email);
            newUser.setPassword(password);
            newUser.setUserType(UserType.CUSTOM);
            newUser.setStatus(Status.NOACTIVE);//未审核
            serUser.save(newUser);

            map = new HashMap<>(1);
            map.put("username", email);
            User _user = serUser.findOne(map);

            urlSb.append("&uid=");

            if(null==_user){
                throw new SerException("注册失败");
            }
            urlSb.append(_user.getId());

            sb.append(urlSb.toString());
            content = sb.toString();
            content = sb.toString().replace("link_url",urlSb.toString());
            em.initEmailInfo(subject, content);
            EmailUtil.SendMail(em);
            String host = email.split("@")[1];
            host = host.substring(0,host.indexOf("."));

            return new ResponseText("https://mail."+host+".com");
        }
    }


}
