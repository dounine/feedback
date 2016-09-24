package dnn.web.user;

import dnn.common.json.ResponseText;
import dnn.common.utils.RequestUtils;
import dnn.common.validation.Add;
import dnn.dto.user.UserDto;
import dnn.entity.user.User;
import dnn.service.user.ISerUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@RestController
@RequestMapping("user")
public class UserAct {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAct.class);

    @Autowired
    protected ISerUser serUser;

    @GetMapping("loginT")
    public ModelAndView loginTpl(){
        return new ModelAndView("login/login");
    }

    @GetMapping("checkLogin")
    public ResponseText checkLogin(HttpServletRequest request){
        Cookie cookie = RequestUtils.getUserTokenCookie(request);
        if(null!=cookie){
            LOGGER.info(cookie.getValue());
        }
        return new ResponseText();
    }

    @GetMapping("findByUsername")
    public ResponseText findByUsername(String username) throws Throwable {
        return new ResponseText(serUser.findByUsername(username));
    }

    @GetMapping("findById")
    public ResponseText findById(String id) throws Throwable {
        return new ResponseText(serUser.findById(id));
    }

    @GetMapping("list")
    public ResponseText listView(UserDto userDto) throws Throwable {
        return new ResponseText(serUser.findByPage(userDto));
    }

    @PostMapping("add")
    public ResponseText add(@Validated(Add.class) User user, BindingResult result) throws Throwable {
        serUser.register(user);
        return new ResponseText(user);
    }

}
