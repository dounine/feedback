package dnn.entity.feedbackInfo.specimen;

import dnn.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by lgq on 16-9-4.
 * 电池父类
 */
public class Battery extends BaseEntity{
    private String sampleName; //样品名称
    private String ts ; //型号规格
    private String trademark;//注册商标
    private String amount ;// 样品数量
    private LocalDateTime submitDate; //送样日期
    private String size; //外形尺寸
    private List<String> packTypes;//包装类型
    private Boolean damage ; //是否损坏

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDateTime submitDate) {
        this.submitDate = submitDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getPackTypes() {
        return packTypes;
    }

    public void setPackTypes(List<String> packTypes) {
        this.packTypes = packTypes;
    }

    public Boolean getDamage() {
        return damage;
    }

    public void setDamage(Boolean damage) {
        this.damage = damage;
    }
}
