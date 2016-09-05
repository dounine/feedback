package dnn.web.feedbackInfo;

/**
 * Created by lgq on 16-9-4.
 */

import dnn.common.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.json.ResponseText;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.entity.feedbackInfo.invoice.InvoiceInfo;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.entity.feedbackInfo.specimen.ChemicalCell;
import dnn.enums.DisposeType;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("customer/feedbackInfo")
public class FeedbackInfoAct {
    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @PostMapping("save")
    public ResponseText save(FeedbackInfo feedbackInfo) throws Throwable {
        feedbackInfo.setUser_id("57cbe7581c3404d62ad95515"); //所属用户

        ChemicalCell chemicalCell = new ChemicalCell();
        chemicalCell.setParameter("xxxx");
        chemicalCell.setWeight("50");

        DetectionInfo detectionInfo = new DetectionInfo();//检测信息
        detectionInfo.setBasis("aaa");
        detectionInfo.setDisposeType(DisposeType.BACK);
        detectionInfo.setRemark("备注");

        InvoiceInfo invoiceInfo = new InvoiceInfo();//开票信息
        invoiceInfo.setAddress("南宁");
        invoiceInfo.setCard("111123312332132");
        invoiceInfo.setBank("工商银行");

        feedbackInfo.setInvoiceInfo(invoiceInfo);
        feedbackInfo.setDetectionInfo(detectionInfo);
        feedbackInfo.setChemicalCell(chemicalCell);

        iSerFeedbackInfo.save(feedbackInfo);
        return new ResponseText<>();
    }


    @GetMapping("findById")
    public ResponseText findById(String id) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findById(id));
        return text;
    }

    @GetMapping("maps")
    public ResponseText maps(FeedbackInfoDto dto) throws Throwable {
        Map<String,Object> maps = new HashMap<>();
        maps.put("rows",iSerFeedbackInfo.findByPage(dto));
        maps.put("count",iSerFeedbackInfo.count());
        return new ResponseText(maps);
    }


}
