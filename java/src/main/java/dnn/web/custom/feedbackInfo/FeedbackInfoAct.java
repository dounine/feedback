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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("custom/feedbackInfo")
public class FeedbackInfoAct {
    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @PostMapping("save")
    public ResponseText save(FeedbackInfo feedbackInfo) throws Throwable {
//        feedbackInfo.setUser_id("57cbe7581c3404d62ad95515"); //所属用户
//        FeedbackInfo feedbackInfos = iSerFeedbackInfo.getDealFeedbackInfo(feedbackInfo);
        iSerFeedbackInfo.save(feedbackInfo);
        return new ResponseText<>();
    }

    @GetMapping("findById")
    public ResponseText findById(String id) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findById(id));
        return text;
    }

    /**
     * 查看所有工单
     * 或根据参数feedbackstatus  或  工单号  查看
     * @param dto
     * @return
     * @throws Throwable
     */
    @GetMapping("maps")
    public ResponseText maps(FeedbackInfoDto dto) throws Throwable {
        Map<String,Object> maps = new HashMap<>();
        maps.put("rows",iSerFeedbackInfo.findByPage(dto));
        maps.put("count",iSerFeedbackInfo.count(dto));
        return new ResponseText(maps);
    }


    /**
     * 用来测试  ,要删除的
     * @return
     * @throws Throwable
     */
    @PostMapping("maps1")
    public ResponseText maps1() throws Throwable {
//        feedbackInfo.setUser_id("57cbe7581c3404d62ad95515"); //所属用户
        List<Object> field = new ArrayList<>(1);
        field.add("chemicalCell.chemicalCellSubmitDate");
        String userId="57cea14279d26cd0708e9d18";
        field.add(userId);
        FeedbackInfo s=iSerFeedbackInfo.findByMax(field,null);

        return new ResponseText<>(s);
    }

    /**
     *通过用户id和工单号查看 最新的一个
     * @param feedbackInfo
     * @return  返回最新的版本
     * @throws Throwable
     */
    @GetMapping("findByFeedbackNum")
    public ResponseText findByFeedbackNum(FeedbackInfo feedbackInfo) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findOneInfo(feedbackInfo));
        return text;
    }

    /**
     * 注意:参数里面一定要包括该单的_id
     * @param feedbackInfo
     * @return 修改的是副本
     * @throws Throwable
     */
    @GetMapping("updateByFeedbackNum")
    public ResponseText updateByFeedbackNum(FeedbackInfo feedbackInfo) throws Throwable {
        iSerFeedbackInfo.updateOneInfo(feedbackInfo);
        ResponseText text = new ResponseText();
        return text;
    }

    /**
     * 管理员最后对工单受理确认
     * @param feedbackInfo
     * @return
     * @throws Throwable
     */
    @GetMapping("confirmFeedback")
    public ResponseText confirmFeedback(FeedbackInfo feedbackInfo) throws Throwable {
        iSerFeedbackInfo.confirmFeedback(feedbackInfo);
        ResponseText text = new ResponseText();
        return text;
    }

}
