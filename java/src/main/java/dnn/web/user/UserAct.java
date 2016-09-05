package dnn.web.user;

import dnn.common.exception.SerException;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by huanghuanlai on 16/9/3.
 */
@RestController
@RequestMapping("user")
public class UserAct {

    @Autowired
    protected ISerUser serUser;

    @GetMapping("list")
    public ModelAndView listView() throws Throwable {
        ModelAndView modelAndView = new ModelAndView("admin/list");
        modelAndView.addObject("users",serUser.findAll());
        return modelAndView;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(Add.class) User user, BindingResult result) throws Throwable {
        ModelAndView modelAndView = new ModelAndView("admin/list");
        try {
            serUser.save(user);
        }catch (SerException se){
            modelAndView.addObject("msg",se.getMessage());
        }
        modelAndView.addObject("users",serUser.findAll());
        return modelAndView;
    }

    @GetMapping("online")
    public Map<String, Object> online() {
        return serUser.listOnline();
    }


}
