package dnn.dto.feedbackInfo;

import dnn.dto.BaseDto;
import dnn.enums.FeedbackStatus;

/**
 * Created by lgq on 16-9-4.
 */
public class FeedbackInfoDto extends BaseDto {
    private FeedbackStatus status;
    private String searchCondition;//搜索条件

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }
}
