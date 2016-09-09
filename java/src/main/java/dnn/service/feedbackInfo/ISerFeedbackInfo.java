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

    List<FeedbackInfo> findByUserId(String user_id)throws SerException;
    List<FeedbackInfo> findBySampleName(String SampleName) throws SerException;

    List<Map<String, Object>> findAllByFeedbackStatus( String feedbackStatus ,String page,String offset ,String searchCondition) throws SerException;
    public FeedbackInfo getDealFeedbackInfo(FeedbackInfo feedbackInfo, String c_submitDate,String p_submitDate,String disposeType) throws Throwable;

    }
