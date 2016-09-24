package dnn.web.user;

import com.mongodb.WriteResult;
import dnn.common.json.ResponseText;
import dnn.common.utils.RequestUtils;
import dnn.common.validation.Add;
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
import java.util.Map;

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

    @GetMapping("list")
    public ModelAndView listView() throws Throwable {
        ModelAndView modelAndView = new ModelAndView("admin/list");
        modelAndView.addObject("users",serUser.findAll());
        return modelAndView;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(Add.class) User user, BindingResult result) throws Throwable {
        ModelAndView modelAndView = new ModelAndView("admin/list");
            serUser.register(user);
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @GetMapping("online")
    public Map<String, Object> online() {
        return serUser.listOnline();
    }

    /**
     * 审核用户
     * @param user
     * @return
     * @throws Throwable
     */
    @GetMapping("auditiingUser")
    public ResponseText auditiingUser(User user) throws Throwable {
        WriteResult writeResult = serUser.auditiingUser(user);
        ResponseText text = new ResponseText(writeResult);
        return text;
    }


}
