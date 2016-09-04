package dnn.common.json;

/**
 * Created by huanghuanlai on 16/4/1.
 */
public class ResponseText<T> {

    public static final int SUCCESS_CODE = 0;

    private int errno = SUCCESS_CODE;
    private T data;
    private Object msg;

    public ResponseText(){}
    public ResponseText(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
