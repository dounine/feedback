package dnn.web.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin")
public class AdminAction {

    @RequestMapping(value = {"**/content", "**/index","**/menu"}, method = RequestMethod.GET)
    public ModelAndView content(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        requestUrl = requestUrl.substring(requestUrl.indexOf("/admin/") + 7);
        return new ModelAndView(new StringBuilder("admin/").append(requestUrl).toString());
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("admin/index");
    }

}
