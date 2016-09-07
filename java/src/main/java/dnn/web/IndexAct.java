package dnn.web;

import dnn.common.exception.SerException;
import dnn.common.json.Callback;
import dnn.common.json.ResponseText;
import dnn.common.mails.Email;
import dnn.common.mails.EmailUtil;
import dnn.common.response.ResponseContext;
import dnn.common.utils.CryptUtil;
import dnn.common.utils.PasswordHash;
import dnn.common.utils.RequestUtils;
import dnn.common.validation.Add;
import dnn.entity.user.User;
import dnn.enums.Status;
import dnn.service.user.ISerUser;
import dnn.service.user.session.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/3/29.
 */
@RestController
public class IndexAct {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexAct.class);

    @Autowired
    ISerUser serUser;

    /**
     * 重写向到首页
     *
     * @return 首页位置
     */
    @RequestMapping(value = {""})
    public ModelAndView index() {
        return new ModelAndView("redirect:/login");
    }


    @GetMapping("login")
    public ModelAndView loginContext() {
        return new ModelAndView("user/login");
    }

    @GetMapping("logout")
    public ModelAndView logout(@CookieValue("token") String token, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(token)) {
            UserSession.remove(token);
            Cookie cookie = RequestUtils.getUserTokenCookie(request);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("login")
    public ModelAndView login(User user) throws Throwable {
        ModelAndView modelAndView = new ModelAndView("user/login");
        String token = null;
        String errMsg = null;
        try {
            if (StringUtils.isBlank(user.getUsername())) {
                errMsg = "用户名不能为空";
            } else if (StringUtils.isBlank(user.getPassword())) {
                errMsg = "密码不能为空";
            } else if (!(user.getUsername().length() >= 5 && user.getUsername().length() <= 18)) {
                errMsg = "用户名长度在 5 到 18 之间";
            } else if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 18)) {
                errMsg = "密码长度在 6 到 18 之间";
            } else {
                token = serUser.login(user);
                if (StringUtils.isNotBlank(token)) {
                    Cookie tokenCookie = new Cookie("token", token);
                    ResponseContext.get().addCookie(tokenCookie);
                    return new ModelAndView("redirect:/");
                }
            }
            modelAndView.addObject("msg", errMsg);
        } catch (SerException se) {
            modelAndView.addObject("msg", se.getMessage());
        }
        return modelAndView;
    }


    public ResponseText ssologin(@Validated(Add.class) User user, BindingResult result, @Callback String callback) throws Throwable {
        boolean callbackFun = StringUtils.isNotBlank(callback);
        ResponseText rt = null;
        String token = serUser.login(user);
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(token)) {
            if (callbackFun) {
                sb.append(callback);
                sb.append(String.format("({token:\"%s\"})", token));
                ResponseContext.writeData(sb);
            } else {
                rt = new ResponseText();
                rt.setData(String.format("{token:\"%s\"}", token));
                Cookie tokenCookie = new Cookie("token", token);
                ResponseContext.get().addCookie(tokenCookie);
            }
        } else {
            if (callbackFun) {
                sb.append(callback);
                sb.append("({msg:\"LOGIN-0002\"})");
                ResponseContext.writeData(sb);
            } else {
                rt = new ResponseText();
                rt.setErrno(2);
                rt.setMsg("LOGIN-0002");
            }
        }
        return rt;
    }


}
