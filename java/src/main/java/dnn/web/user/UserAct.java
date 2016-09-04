package dnn.web.user;

import dnn.common.json.Callback;
import dnn.common.json.ResponseText;
import dnn.common.response.ResponseContext;
import dnn.common.validation.Add;
import dnn.entity.user.User;
import dnn.service.user.ISerUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@RestController
@RequestMapping("user")
public class UserAct {

    @Autowired
    protected ISerUser serUser;

    @GetMapping("login")
    public ModelAndView loginContext(){
        return new ModelAndView("user/login");
    }

    @PostMapping("add")
    public ResponseText add(@Validated(Add.class) User user, BindingResult result)throws Throwable{
        serUser.save(user);
        return new ResponseText();
    }

    @GetMapping("online")
    public Map<String,Object> online(){
        return serUser.listOnline();
    }

    @PostMapping("login")
    public ResponseText login(@Validated(Add.class) User user, BindingResult result, @Callback String callback) throws Throwable {
        boolean callbackFun = StringUtils.isNotBlank(callback);
        ResponseText rt = null;
        String token = serUser.login(user);
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isNotBlank(token)){
            if(callbackFun){
                sb.append(callback);
                sb.append(String.format("({token:\"%s\"})",token));
                ResponseContext.writeData(sb);
            }else{
                rt = new ResponseText();
                rt.setData(String.format("{token:\"%s\"}",token));
                Cookie tokenCookie = new Cookie("token",token);
                ResponseContext.get().addCookie(tokenCookie);
            }
        }else{
            if(callbackFun){
                sb.append(callback);
                sb.append("({msg:\"LOGIN-0002\"})");
                ResponseContext.writeData(sb);
            }else{
                rt = new ResponseText();
                rt.setErrno(2);
                rt.setMsg("LOGIN-0002");
            }
        }
        return rt;
    }



}
