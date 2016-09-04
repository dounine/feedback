package dnn.web.custom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("custom")
public class CustomAction {

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("custom/index");
    }

    @GetMapping("aa")
    public ModelAndView aa(HttpServletRequest request) {
        return new ModelAndView("custom/index");
    }

}
