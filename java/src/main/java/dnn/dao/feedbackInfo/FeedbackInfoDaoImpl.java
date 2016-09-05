package dnn.dao.feedbackInfo;

import dnn.common.dto.feedbackInfo.FeedbackInfoDto;
import dnn.dao.DaoImpl;
import dnn.entity.feedbackInfo.FeedbackInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by lgq on 16-9-4.
 */
@Repository
public class FeedbackInfoDaoImpl extends DaoImpl<FeedbackInfo, FeedbackInfoDto> implements IFeedbackInfoDao {
}
