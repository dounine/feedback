package dnn.web.custom.untreated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("custom/untreated")
public class UntreatedAct {

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:/custom/untreated/index");
    }

    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return new ModelAndView("custom/untreated/index");
    }

}
