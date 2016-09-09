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

    @GetMapping("findAllByFeedbackStatus")
    public ResponseText findBySampleName(String feedbackStatus ,String page,String offset ,String searchCondition) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findAllByFeedbackStatus(feedbackStatus,page,offset,searchCondition));
        return text;
    }

    @GetMapping("findcall")
    public ResponseText findcall() throws Throwable {
        FeedbackInfoDto fbkinfoDto =new FeedbackInfoDto();
        System.out.println(fbkinfoDto.getLimit());
        System.out.println(fbkinfoDto.getOffset());
        System.out.println(fbkinfoDto.getPage());
        fbkinfoDto.setLimit(2);
        fbkinfoDto.setPage(2);
        fbkinfoDto.setOffset(3);
        SearchJson searchJson = new SearchJson();
        searchJson.setSearchName(RestrictionType.EQ);
        String[] condition ={"chemicalCell.sampleName","String","化学"};
        searchJson.setSearchField(condition);
        List<SearchJson> searchJsons = new ArrayList<>();
        searchJsons.add(searchJson);
        fbkinfoDto.setSearchJsons(searchJsons);

        Map<String, Object> result = new HashMap<String, Object>();
        String ll =(String)result.get("呼呼");

//        ResponseText text = new ResponseText(iSerFeedbackInfo.findAll());
        ResponseText text = new ResponseText(iSerFeedbackInfo.findByPage(fbkinfoDto));
        text.setMsg(iSerFeedbackInfo.countByCis(null)+"哈萨达:"+iSerFeedbackInfo.count(fbkinfoDto));
        return text;
    }



}
