package dnn.web.custom.procing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("custom/procing")
public class ProcingAct {

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:/custom/procing/index");
    }

    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return new ModelAndView("custom/procing/index");
    }

}