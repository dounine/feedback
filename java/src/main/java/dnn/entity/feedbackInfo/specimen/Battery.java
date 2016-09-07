package dnn.entity.feedbackInfo.specimen;

import dnn.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private LocalDate submitDate; //送样日期
    private Date sudate;
    private String size; //外形尺寸
    private String packTypes;//包装类型
    private Boolean damage ; //是否损坏
    private String weight; // 重量


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

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPackTypes() {
        return packTypes;
    }

    public void setPackTypes(String packTypes) {
        this.packTypes = packTypes;
    }

    public Boolean getDamage() {
        return damage;
    }

    public void setDamage(Boolean damage) {
        this.damage = damage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
