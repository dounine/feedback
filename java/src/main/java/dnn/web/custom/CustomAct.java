package dnn.web.custom;

import dnn.common.json.ResponseText;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.enums.FeedbackStatus;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("custom")
public class CustomAct {

    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @GetMapping("")
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:/custom/index");
    }

    @GetMapping("index")
    public ModelAndView index(HttpServletRequest request) {

        return new ModelAndView("custom/index");
    }

    @GetMapping("findAllByFeedbackStatus")
    public ResponseText findBySampleName(String feedbackStatus) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findAllByFeedbackStatus(feedbackStatus));
        return text;
    }




}
