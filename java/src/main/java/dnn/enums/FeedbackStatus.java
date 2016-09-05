package dnn.enums;

/**
 * Created by lgq on 16-9-5.
 */
public enum FeedbackStatus {
    UNTREATED(0),//未处理
    INHAND(1),//处理中
    FINISH(2);//处理完毕
    private int code;

    FeedbackStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
