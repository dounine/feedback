package dnn.entity.order;

import dnn.entity.BaseEntity;
import dnn.entity.invoice.InvoiceInfo;
import dnn.entity.specimen.Battery;
import dnn.entity.detection.DetectionInfo;
import dnn.entity.specimen.ChemicalCell;
import dnn.entity.specimen.PhysicalEnergy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by lgq on 16-9-4.
 * 反馈订单信息
 */
@Document
public class FeedbackInfo extends BaseEntity {
    private String user_id;
    private LocalDateTime createTime = LocalDateTime.now();
    private DetectionInfo detectionInfo ; //检测信息
    private InvoiceInfo invoiceInfo; //发票信息
    private ChemicalCell chemicalCell; //化学电池
    private PhysicalEnergy physicalEnergy; //物理电池

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
}
