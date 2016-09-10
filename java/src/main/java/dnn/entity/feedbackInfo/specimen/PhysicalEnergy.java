package dnn.entity.feedbackInfo.specimen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by lgq on 16-9-4.
 * 物理电池
 */
public class PhysicalEnergy extends Battery {

    private String serialNumber; //样品序列号
    private String materialTypes; //单体电池材料类型
    private LocalDateTime physicalEnergySubmitDate; //送样日期


    //单体相关参数
    private String monomer_Size; //单体尺寸
    private String monomer_thickness;//单体厚度
    private String monomer_acreage; //单体面积
    private String monomer_type; //减反射膜类型

    private String tempered ; //(玻璃)钢化,非钢化
    private String tempered_thickness;//厚度

    private String series_battery;// 串联电池数
    private String parallel_battery;//并联电池数
    private String voltage ; //系统标准电压
    private Boolean parameter; //电气原理图(参数...)
    private Boolean identifying; //标识

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMaterialTypes() {
        return materialTypes;
    }

    public void setMaterialTypes(String materialTypes) {
        this.materialTypes = materialTypes;
    }

    public String getMonomer_Size() {
        return monomer_Size;
    }

    public void setMonomer_Size(String monomer_Size) {
        this.monomer_Size = monomer_Size;
    }

    public String getMonomer_thickness() {
        return monomer_thickness;
    }

    public void setMonomer_thickness(String monomer_thickness) {
        this.monomer_thickness = monomer_thickness;
    }

    public String getMonomer_acreage() {
        return monomer_acreage;
    }

    public void setMonomer_acreage(String monomer_acreage) {
        this.monomer_acreage = monomer_acreage;
    }

    public String getMonomer_type() {
        return monomer_type;
    }

    public void setMonomer_type(String monomer_type) {
        this.monomer_type = monomer_type;
    }

    public String getTempered() {
        return tempered;
    }

    public void setTempered(String tempered) {
        this.tempered = tempered;
    }

    public String getTempered_thickness() {
        return tempered_thickness;
    }

    public void setTempered_thickness(String tempered_thickness) {
        this.tempered_thickness = tempered_thickness;
    }

    public String getSeries_battery() {
        return series_battery;
    }

    public void setSeries_battery(String series_battery) {
        this.series_battery = series_battery;
    }

    public String getParallel_battery() {
        return parallel_battery;
    }

    public void setParallel_battery(String parallel_battery) {
        this.parallel_battery = parallel_battery;
    }

    public Boolean getParameter() {
        return parameter;
    }

    public void setParameter(Boolean parameter) {
        this.parameter = parameter;
    }

    public Boolean getIdentifying() {
        return identifying;
    }

    public void setIdentifying(Boolean identifying) {
        this.identifying = identifying;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public LocalDateTime getPhysicalEnergySubmitDate() {
        return physicalEnergySubmitDate;
    }

    public void setPhysicalEnergySubmitDate(LocalDateTime physicalEnergySubmitDate) {
        this.physicalEnergySubmitDate = physicalEnergySubmitDate;
    }
}
