package dnn.entity.feedbackInfo.specimen;

/**
 * Created by lgq on 16-9-4.
 * 化学电池
 */
public class ChemicalCell extends Battery{
    private String parameter; //冲放电参数
    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
