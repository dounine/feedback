package dnn.enums;

/**
 * Created by lgq on 16-9-4.
 * 包装类型
 */
public enum PackType {
    NOT(0),//无包装
    CARTON(1),//纸箱
    WOODEN(2);//木箱
    private int code;
    PackType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
