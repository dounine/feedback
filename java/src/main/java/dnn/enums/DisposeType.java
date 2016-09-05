package dnn.enums;

/**
 * Created by lgq on 16-9-4.
 * 样品处理类型
 */
public enum DisposeType {
    NOT(0),//不处理
    PICKUP(1),//自取
    BACK(2);//退回
    private int code;

    DisposeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
