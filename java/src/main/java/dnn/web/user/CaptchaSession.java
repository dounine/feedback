package dnn.web.user;

/**
 * Created by lgq on 16-9-23.
 */
public class CaptchaSession{
    private String key;
    private String val;

    public CaptchaSession(String key,String val){
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}

