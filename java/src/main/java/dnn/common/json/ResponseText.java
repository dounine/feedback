package dnn.common.json;

/**
 * Created by huanghuanlai on 16/4/1.
 */
public class ResponseText {

    public static final int SUCCESS_CODE = 0;

    private int errno = SUCCESS_CODE;
    private Object data;
    private Object msg;

    public ResponseText(){}
    public ResponseText(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
