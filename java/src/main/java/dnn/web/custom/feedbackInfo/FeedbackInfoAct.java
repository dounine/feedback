package dnn.web.custom.feedbackInfo;

/**
 * Created by lgq on 16-9-4.
 */

import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.json.ResponseText;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.entity.feedbackInfo.specimen.ChemicalCell;
import dnn.entity.feedbackInfo.specimen.PhysicalEnergy;
import dnn.enums.DisposeType;
import dnn.service.feedbackInfo.ISerFeedbackInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("custom/feedbackInfo")
public class FeedbackInfoAct {
    @Autowired
    private ISerFeedbackInfo iSerFeedbackInfo;

    @PostMapping("save")
    public ResponseText save(FeedbackInfo feedbackInfo, String c_submitDate,String p_submitDate,String disposeType) throws Throwable {
//        feedbackInfo.setUser_id("57cbe7581c3404d62ad95515"); //所属用户
        LocalDate c_date=null;
        LocalDate p_date=null;

        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if(StringUtils.isNotBlank(c_submitDate)){
            c_date=LocalDate.parse(c_submitDate, DATE_FORMAT);
        }else{
            p_date =LocalDate.parse(p_submitDate, DATE_FORMAT);
        }
        ChemicalCell chemicalCell = new ChemicalCell();
        PhysicalEnergy physicalEnergy = new PhysicalEnergy();
        if (null!=c_date) {
            chemicalCell = feedbackInfo.getChemicalCell();
            chemicalCell.setSubmitDate(c_date);
            feedbackInfo.setChemicalCell(chemicalCell);
        } else {
            physicalEnergy = feedbackInfo.getPhysicalEnergy();
            physicalEnergy.setSubmitDate(p_date);
            feedbackInfo.setPhysicalEnergy(physicalEnergy);
        }

        DetectionInfo detectionInfo =new DetectionInfo();
        if( disposeType.equals("NOT")){
            detectionInfo.setDisposeType(DisposeType.NOT);
        }else if(disposeType.equals("PICKUP")){
            detectionInfo.setDisposeType(DisposeType.PICKUP);
        }else if(disposeType.equals("BACK")){
            detectionInfo.setDisposeType(DisposeType.BACK);
        }
        feedbackInfo.setDetectionInfo(detectionInfo);

        iSerFeedbackInfo.save(feedbackInfo);
        return new ResponseText<>();
    }

    @GetMapping("findById")
    public ResponseText findById(String id) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findById(id));
        return text;
    }

    @GetMapping("findBySampleName")
    public ResponseText findBySampleName(String SampleName) throws Throwable {
        ResponseText text = new ResponseText(iSerFeedbackInfo.findBySampleName(SampleName));
        return text;
    }

    @GetMapping("maps")
    public ResponseText maps(FeedbackInfoDto dto) throws Throwable {
        Map<String,Object> maps = new HashMap<>();
        maps.put("rows",iSerFeedbackInfo.findByPage(dto));
        maps.put("count",iSerFeedbackInfo.count(dto));
        return new ResponseText(maps);
    }


}
