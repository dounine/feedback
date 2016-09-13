package dnn.dto.feedbackInfo;

import dnn.dto.BaseDto;
import dnn.enums.FeedbackStatus;

/**
 * Created by lgq on 16-9-4.
 */
public class FeedbackInfoDto extends BaseDto {
    private FeedbackStatus status;
    private String detectionNum;//工单编号搜索条件

    public FeedbackStatus getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    public String getDetectionNum() {
        return detectionNum;
    }

    public void setDetectionNum(String detectionNum) {
        this.detectionNum = detectionNum;
    }
}
