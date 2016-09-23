package dnn.web.custom.feedbackInfo;

/**
 * Created by lgq on 16-9-4.
 */

import dnn.common.json.ResponseText;
import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("custom/feedbackInfo")
public class FeedbackInfoAct {
    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    /**
     * 保存
     *
     * @param feedbackInfo
     * @return
     * @throws Throwable
     */
    @PostMapping("save")
    public ResponseText save(FeedbackInfo feedbackInfo) throws Throwable {
        iSerFeedbackInfo.save(feedbackInfo);
        return new ResponseText();
    }

    @GetMapping("findById")
    public ResponseText findById(String id) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findById(id));
        return text;
    }

    /**
     * 查看所有分页工单
     *
     * @param dto (feedbackstatus or detectionNum(编号))
     * @return
     * @throws Throwable
     */
    @GetMapping("maps")
    public ResponseText maps(FeedbackInfoDto dto) throws Throwable {
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", iSerFeedbackInfo.findByPage(dto));
        maps.put("count", iSerFeedbackInfo.count(dto));
        return new ResponseText(maps);
    }

    /**
     * 查看详细内容
     *
     * @param feedbackInfo (userId and detectionNum(编号))
     * @return 返回最新的版本
     * @throws Throwable
     */
    @GetMapping("feedbackDetail")
    public ResponseText feedbackDetail(FeedbackInfo feedbackInfo) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findOneInfo(feedbackInfo));
        return text;
    }

    /**
     * 工单流程状态修改
     *
     * @param feedbackInfo (userId and detectionNum(编号))
     * @return 修改的是副本
     * @throws Throwable
     */
    @GetMapping("updateFeedbackOperateStatus")
    public ResponseText updateFeedbackOperateStatus(FeedbackInfo feedbackInfo) throws Throwable {
        iSerFeedbackInfo.updateOneInfo(feedbackInfo);
        ResponseText text = new ResponseText();
        return text;
    }

    /**
     * 管理员最后对工单受理确认
     *
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

    /**
     * 删除
     *
     * @param feedbackInfo (userId and detectionNum(编号))
     * @return
     * @throws Throwable
     */
    @GetMapping("deleteFeedback")
    public ResponseText deleteFeedback(FeedbackInfo feedbackInfo) throws Throwable {
        int result = iSerFeedbackInfo.deleteFeedback(feedbackInfo);
        ResponseText text = new ResponseText(result);
        return text;
    }

}
