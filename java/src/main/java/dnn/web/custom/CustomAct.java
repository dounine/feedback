package dnn.web.custom;

import dnn.common.json.ResponseText;
import dnn.dto.SearchJson;
import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.enums.FeedbackStatus;
import dnn.enums.RestrictionType;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
