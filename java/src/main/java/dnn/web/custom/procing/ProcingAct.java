package dnn.web.custom.procing;

import dnn.common.json.ResponseText;
import dnn.enums.FeedbackStatus;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("custom/procing")
public class ProcingAct {

    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:/custom/procing/index");
    }

    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return new ModelAndView("custom/procing/index");
    }

    @GetMapping("findAllByFeedbackStatus")
    public ResponseText findBySampleName(String feedbackStatus) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findAllByFeedbackStatus(feedbackStatus));
        return text;
    }
}
