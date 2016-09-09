package dnn.dto.feedbackInfo;

import dnn.dto.BaseDto;
import dnn.enums.FeedbackStatus;

/**
 * Created by lgq on 16-9-4.
 */
public class FeedbackInfoDto extends BaseDto {
    private FeedbackStatus status;

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

}
