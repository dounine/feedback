package dnn.web.user;

/**
 * Created by lgq on 16-9-7.
 */

import dnn.common.mails.Email;
import dnn.common.mails.EmailUtil;
import dnn.common.utils.CryptUtil;
import dnn.common.utils.PasswordHash;
import dnn.common.utils.PropertyUtil;
import dnn.entity.user.User;
import dnn.enums.Status;
import dnn.service.user.ISerUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lgq on 16-9-3.
 */
@RequestMapping("register")
@RestController
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
    public ModelAndView register(User user) throws Throwable {
        String errMsg = null;
        if (StringUtils.isBlank(user.getUsername())) {
            errMsg = "用户名不能为空";
        } else if (StringUtils.isBlank(user.getPassword())) {
            errMsg = "密码不能为空";
        } else if (!(user.getUsername().length() >= 5 && user.getUsername().length() <= 18)) {
            errMsg = "用户名长度在 5 到 18 之间";
        } else if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 18)) {
            errMsg = "密码长度在 6 到 18 之间";
        } else {
            user.setStatus(Status.UNREVIEW);//未审核
            serUser.save(user);
        }
        return new ModelAndView("redirect:/register");
    }

    /**
     * 注册邮箱激活
     *
     * @param code
     * @return
     * @throws Throwable
     */
    @GetMapping("activate")
    public ModelAndView email_activate(String code, String sid, HttpServletRequest request) throws Throwable {
        ModelAndView modelAndView = null;
        if (StringUtils.isNotBlank(sid)) {
            sid = request.getParameter("amp;sid");
        }
        String str = CryptUtil.decryptBASE64(sid);
        String email = str.split("#")[0];
        String time = str.split("#")[1];
        LocalDateTime d_time = LocalDateTime.parse(time);
        if (PasswordHash.validatePassword(email, code)  //是否比配,时间是否超时
                && d_time.plusHours(1).isBefore(LocalDateTime.now())) {
            modelAndView = new ModelAndView("admin/register"); //注册页面

        } else {
            modelAndView = new ModelAndView("admin/timeout");//验证超时
            // time out
        }

        return modelAndView;
    }

    /**
     * 注册邮箱验证
     *
     * @param email
     * @return
     * @throws Throwable
     */
    @PostMapping("valid")
    public ModelAndView email_valid(String email) throws Throwable {
        String subject = "Feedback 注册验证";
        String content = null;
        String errMsg = null;
        StrBuilder sb = new StrBuilder();
        ModelAndView modelAndView = null;
        Map<String, Object> map = new HashMap<>(1);
        map.put("details.email", email);
        if (null != serUser.findOne(map)) {
            errMsg = "该邮箱已被注册!";
            modelAndView = new ModelAndView("user/login");
            modelAndView.addObject("msg", errMsg);
            return modelAndView;
        } else {
            Email em = new Email(email);
            sb.append("请点击该链接或复制到浏览器进行注册验证:");
            sb.append(PropertyUtil.getInstance().getProperty("domain.name")); //域名
            sb.append("/register/activate");
            sb.append("?code=");
            sb.append(PasswordHash.createHash(email));
            sb.append("?sid=");
            sb.append(CryptUtil.encryptBASE64(LocalDateTime.now().toString() + "#" + email));
            content = sb.toString();
            em.initEmailInfo(subject, content);
            EmailUtil.SendMail(em);
            String host = email.split("@")[1];
            host = host.substring(0,host.indexOf("."));
            return new ModelAndView("redirect: https://mail."+host+".com"); //处理跳转到注册邮箱域名...

        }
    }


}
