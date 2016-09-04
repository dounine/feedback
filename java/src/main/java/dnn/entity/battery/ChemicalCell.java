package dnn.entity.battery;

import dnn.entity.BaseEntity;
import dnn.enums.PackType;

import java.time.LocalDateTime;

/**
 * Created by lgq on 16-9-4.
 * 化学电池
 */
public class ChemicalCell extends BaseEntity{
    private String sampleName; //样品名称
    private String ts ; //型号规格
    private String trademark;//注册商标
    private String amount ;// 样品数量
    private LocalDateTime submitDate; //送样日期
    private String size; //外形尺寸
    private Double weight; // 重量
    private String parameter; //冲放电参数
    private PackType packType;//包装类型
    private boolean damage ; //是否损坏

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

    public PackType getPackType() {
        return packType;
    }

    public void setPackType(PackType packType) {
        this.packType = packType;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }
}
