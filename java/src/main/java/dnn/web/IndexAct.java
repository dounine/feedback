package dnn.web;

import dnn.common.json.Callback;
import dnn.common.json.ResponseText;
import dnn.common.response.ResponseContext;
import dnn.common.utils.UserContext;
import dnn.common.validation.Add;
import dnn.dao.user.IUserDao;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.service.user.ISerUser;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huanghuanlai on 16/3/29.
 */
@RestController
public class IndexAct {

    @Autowired
    IUserDao userDao;
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
        if(UserContext.isLogin()){
            if(UserContext.currentUser().getUserType().equals(UserType.MANAGER)){
                return new ModelAndView("redirect:/admin");//TODO 管理员暂时也跳转到customer页面,请自行修改
            }else{
                return new ModelAndView("redirect:/customer");
            }
        }else{
            return new ModelAndView("login/index");
        }
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        UserContext.removeUserSession();
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("login")
    public ResponseText ssologin(@Validated(Add.class) User user, BindingResult result, @Callback String callback) throws Throwable {
        boolean callbackFun = StringUtils.isNotBlank(callback);
        ResponseText rt = null;
        serUser.login(user);
        StringBuffer sb = new StringBuffer();
        UserType userType = UserContext.currentUser().getUserType();
        if(callbackFun){
            sb.append(callback);
            sb.append(String.format("({\"userType\":\"%s\"})",userType));
            ResponseContext.writeData(sb);
        }else{
            rt = new ResponseText();
            rt.setData(String.format("{\"userType\":\"%s\"}",userType));
        }
        return rt;
    }


}
