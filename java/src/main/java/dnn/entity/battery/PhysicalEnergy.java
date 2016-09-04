package dnn.entity.battery;

import dnn.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * Created by lgq on 16-9-4.
 * 物理电池
 */
public class PhysicalEnergy extends BaseEntity {
    private String sampleName; //样品名称
    private String ts ; //型号规格
    private String trademark;//注册商标
    private String amount ;// 样品数量
    private LocalDateTime submitDate; //送样日期
    private String size; //外形尺寸
    private String serialNumber; //样品序列号

}
