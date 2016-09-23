package dnn.entity.feedbackInfo;

import dnn.entity.BaseEntity;
import dnn.entity.feedbackInfo.invoice.InvoiceInfo;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.entity.feedbackInfo.specimen.ChemicalCell;
import dnn.entity.feedbackInfo.specimen.PhysicalEnergy;
import dnn.enums.FeedbackStatus;
import dnn.enums.OperatorStatus;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.expression.spel.ast.Operator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by lgq on 16-9-4.
 * 反馈订单信息
 */
public class FeedbackInfo extends BaseEntity {
    private String userId;//客户id
    private LocalDateTime createTime = LocalDateTime.now();
    private DetectionInfo detectionInfo ; //检测信息
    private InvoiceInfo invoiceInfo; //发票信息
    private ChemicalCell chemicalCell; //化学电池
    private PhysicalEnergy physicalEnergy; //物理电池
    private FeedbackStatus feedbackStatus ; //处理状态
    private String detectionNum;//编号: 年份ST(001`以上)
    private Long detectionNo;//(001--)
    private OperatorStatus operatorStatus;//用户操作状态
    private Long copyNum;//版本号
    private Long finalCopyField;//最终版本字段标志
    @Transient
    private String customerName ;//用户名



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public DetectionInfo getDetectionInfo() {
        return detectionInfo;
    }

    public void setDetectionInfo(DetectionInfo detectionInfo) {
        this.detectionInfo = detectionInfo;
    }

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public ChemicalCell getChemicalCell() {
        return chemicalCell;
    }

    public void setChemicalCell(ChemicalCell chemicalCell) {
        this.chemicalCell = chemicalCell;
    }

    public PhysicalEnergy getPhysicalEnergy() {
        return physicalEnergy;
    }

    public void setPhysicalEnergy(PhysicalEnergy physicalEnergy) {
        this.physicalEnergy = physicalEnergy;
    }

    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(FeedbackStatus feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDetectionNum() {
        return detectionNum;
    }

    public void setDetectionNum(String detectionNum) {
        this.detectionNum = detectionNum;
    }

    public Long getDetectionNo() {
        return detectionNo;
    }

    public void setDetectionNo(Long detectionNo) {
        this.detectionNo = detectionNo;
    }

    public OperatorStatus getOperatorStatus() {
        return operatorStatus;
    }

    public void setOperatorStatus(OperatorStatus operatorStatus) {
        this.operatorStatus = operatorStatus;
    }

    public Long getCopyNum() {
        return copyNum;
    }

    public void setCopyNum(Long copyNum) {
        this.copyNum = copyNum;
    }

    public Long getFinalCopyField() {
        return finalCopyField;
    }

    public void setFinalCopyField(Long finalCopyField) {
        this.finalCopyField = finalCopyField;
    }
}
