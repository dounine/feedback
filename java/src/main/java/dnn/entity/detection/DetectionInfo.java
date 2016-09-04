package dnn.entity.detection;

import dnn.enums.DisposeType;

/**
 * Created by lgq on 16-9-4.
 * 检测信息
 */
public class DetectionInfo {
    private String basis; //检测依据
    private String testItem;//检测项目
    private DisposeType disposeType;//样品处理
    private String remark;//备注

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getTestItem() {
        return testItem;
    }

    public void setTestItem(String testItem) {
        this.testItem = testItem;
    }

    public DisposeType getDisposeType() {
        return disposeType;
    }

    public void setDisposeType(DisposeType disposeType) {
        this.disposeType = disposeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
