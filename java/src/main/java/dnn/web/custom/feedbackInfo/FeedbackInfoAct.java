package dnn.web.custom.feedbackInfo;

/**
 * Created by lgq on 16-9-4.
 */

import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.json.ResponseText;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.entity.feedbackInfo.specimen.ChemicalCell;
import dnn.entity.feedbackInfo.specimen.PhysicalEnergy;
import dnn.entity.user.User;
import dnn.enums.DisposeType;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import dnn.service.user.ISerUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("custom/feedbackInfo")
public class FeedbackInfoAct {
    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @PostMapping("save")
    public ResponseText save(FeedbackInfo feedbackInfo, String c_submitDate,String p_submitDate,String disposeType) throws Throwable {
//        feedbackInfo.setUser_id("57cbe7581c3404d62ad95515"); //所属用户
        FeedbackInfo feedbackInfos = iSerFeedbackInfo.getDealFeedbackInfo(feedbackInfo,c_submitDate,p_submitDate,disposeType);
        iSerFeedbackInfo.save(feedbackInfos);
        return new ResponseText<>();
    }

    @GetMapping("findById")
    public ResponseText findById(String id) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findById(id));
        return text;
    }


    @GetMapping("maps")
    public ResponseText maps(FeedbackInfoDto dto) throws Throwable {
        Map<String,Object> maps = new HashMap<>();
        maps.put("rows",iSerFeedbackInfo.findByPage(dto));
        maps.put("count",iSerFeedbackInfo.count(dto));
        return new ResponseText(maps);
    }

}
