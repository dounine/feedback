package dnn.service.feedbackInfo;

import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.exception.SerException;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by lgq on 16-9-4.
 *
 */
public interface ISerFeedbackInfo extends IService<FeedbackInfo,FeedbackInfoDto> {

    List<FeedbackInfo> findByUserId(String userId)throws SerException;
    FeedbackInfo findOneInfo(FeedbackInfo feedbackInfo)throws SerException;
    void updateOneInfo(FeedbackInfo feedbackInfo)throws SerException;
    void confirmFeedback(FeedbackInfo feedbackInfo)throws SerException;
    void deleteFeedback(FeedbackInfo feedbackInfo)throws SerException;

    }
