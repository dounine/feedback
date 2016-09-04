package dnn.web.user;

import dnn.common.json.ResponseText;
import dnn.common.validation.Add;
import dnn.entity.user.User;
import dnn.service.user.ISerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@RestController
@RequestMapping("user")
public class UserAct {

    @Autowired
    protected ISerUser serUser;

    @PostMapping("add")
    public ResponseText add(@Validated(Add.class) User user, BindingResult result)throws Throwable{
        serUser.save(user);
        return new ResponseText();
    }

    @GetMapping("online")
    public Map<String,Object> online(){
        return serUser.listOnline();
    }





}
