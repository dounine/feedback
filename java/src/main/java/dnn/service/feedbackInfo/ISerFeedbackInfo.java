package dnn.service.feedbackInfo;

import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.exception.SerException;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.service.IService;

import java.util.List;

/**
 * Created by lgq on 16-9-4.
 *
 */
public interface ISerFeedbackInfo extends IService<FeedbackInfo,FeedbackInfoDto> {

    List<FeedbackInfo> findByUserId(String user_id)throws SerException;

}
