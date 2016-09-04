package dnn.enums;

/**
 * Created by lgq on 16-9-4.
 * 电池材料
 */
public enum  MaterialType {
    MYSTE(0),//单晶硅
    MANYMYSTE(1),//多晶硅
    N(2),//N型
    P(3),//P型
    OTHER(4);//其他
    private int code;

    MaterialType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
