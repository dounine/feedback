package dnn.web.custom;

import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("customer")
public class CustomAct {

    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @GetMapping("")
    public ModelAndView home() {
        return new ModelAndView("redirect:/customer/index");
    }

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("customer/index");
    }

    @GetMapping("wrapbody")
    public ModelAndView wrapbody() {
        return new ModelAndView("customer/wrapbody");
    }

    @GetMapping("menu")
    public ModelAndView menu() {
        return new ModelAndView("customer/menu");
    }

    @GetMapping("content")
    public ModelAndView content() {
        return new ModelAndView("customer/content");
    }

    @GetMapping("unpro")
    public ModelAndView unpro() {
        return new ModelAndView("customer/unpro");
    }

    @GetMapping("proing")
    public ModelAndView proing() {
        return new ModelAndView("customer/proing");
    }

    @GetMapping("proed")
    public ModelAndView proed() {
        return new ModelAndView("customer/proed");
    }

    @GetMapping("userinfo")
    public ModelAndView userinfo() {
        return new ModelAndView("customer/userinfo");
    }

}
