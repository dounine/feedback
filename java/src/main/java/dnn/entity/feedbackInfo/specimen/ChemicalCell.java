package dnn.entity.feedbackInfo.specimen;

/**
 * Created by lgq on 16-9-4.
 * 化学电池
 */
public class ChemicalCell extends Battery{
    private Double weight; // 重量
    private String parameter; //冲放电参数

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
