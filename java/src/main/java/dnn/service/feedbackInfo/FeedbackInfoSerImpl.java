package dnn.service.feedbackInfo;

import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.exception.SerException;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.entity.feedbackInfo.specimen.ChemicalCell;
import dnn.entity.feedbackInfo.specimen.PhysicalEnergy;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.enums.DisposeType;
import dnn.enums.FeedbackStatus;
import dnn.service.ServiceImpl;
import dnn.service.user.ISerUser;
import dnn.service.user.session.Online;
import dnn.service.user.session.UserSession;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lgq on 16-9-4.
 */
@Service
public class FeedbackInfoSerImpl extends ServiceImpl<FeedbackInfo,FeedbackInfoDto> implements ISerFeedbackInfo {

    @Autowired
    private ISerUser serUser;
    /**
     * 订单信息提交
     * @param feedbackInfo
     * @throws SerException
     */
    @Override
    public void save(FeedbackInfo feedbackInfo) throws SerException {
        String c_sampleName = feedbackInfo.getChemicalCell().getSampleName();
        String p_sampleName = feedbackInfo.getPhysicalEnergy().getSampleName();
        if(StringUtils.isNotBlank(c_sampleName) && StringUtils.isNotBlank(p_sampleName)){
            throw  new SerException("电池只能选填一项");
        }
        if(StringUtils.isBlank(c_sampleName)){
            feedbackInfo.setChemicalCell(null);
        }else{
            feedbackInfo.setPhysicalEnergy(null);
        }
        super.save(feedbackInfo);
    }

    @Override
    public List<FeedbackInfo> findByUserId(String user_id)throws SerException {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("user_id",user_id);
        return findByCis(conditions);
    }

    /**
     *
     * @param SampleName 样品名
     * @return
     * @throws SerException
     */
    @Override
    public List<FeedbackInfo> findBySampleName(String SampleName) throws SerException {
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("chemicalCell.sampleName",SampleName);
        return findByFuzzy(conditions);
    }

    @Override
    public List<Map<String, Object>> findAllByFeedbackStatus(String feedbackStatus) throws SerException {
        List<FeedbackInfo> feedbackinfo=null;
        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        String status=null;
        User userinfo = null;

        if(feedbackStatus.equals(FeedbackStatus.UNTREATED.name().toString())){
            status=feedbackStatus;
        }else if(feedbackStatus.equals(FeedbackStatus.INHAND.name().toString())){
            status=feedbackStatus;
        }else if(feedbackStatus.equals(FeedbackStatus.FINISH.name().toString())){
            status=feedbackStatus;
        }
        Map<String, Object> conditions = new HashMap<String, Object>();
        String userType = "CUSTOM";//TODO 获取当前用户类型
        conditions.put("feedbackStatus", status);
        if (userType.equals(UserType.CUSTOM.name().toString())) {
            conditions.put("user_id", "57cd507d3eb209a4f930552c");//TODO 修改
        }
        Long count = countByCis(conditions);
        if (count > 0) {
            feedbackinfo = findByCis(conditions);
            for (FeedbackInfo fdinfo : feedbackinfo) {
                Map<String, Object> result = new HashMap<String, Object>();
                userinfo = serUser.findById(fdinfo.getUser_id());
                result.put("customerName", userinfo.getUsername());
                result.put("feedbackinfo", fdinfo);
                list.add(result);
            }
        } else {
            list = null;
        }
        return list;
    }

    @Override
    public FeedbackInfo getDealFeedbackInfo(FeedbackInfo feedbackInfo, String c_submitDate,String p_submitDate,String disposeType) throws Throwable{
        LocalDate c_date = null;
        LocalDate p_date = null;

        DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        if (StringUtils.isNotBlank(c_submitDate)) {
            c_date = LocalDate.parse(c_submitDate, DATE_FORMAT);
        } else {
            p_date = LocalDate.parse(p_submitDate, DATE_FORMAT);
        }
        ChemicalCell chemicalCell = new ChemicalCell();
        PhysicalEnergy physicalEnergy = new PhysicalEnergy();
        if (null != c_date) {
            chemicalCell = feedbackInfo.getChemicalCell();
            chemicalCell.setSubmitDate(c_date);
            feedbackInfo.setChemicalCell(chemicalCell);
        } else {
            physicalEnergy = feedbackInfo.getPhysicalEnergy();
            physicalEnergy.setSubmitDate(p_date);
            feedbackInfo.setPhysicalEnergy(physicalEnergy);
        }

        DetectionInfo detectionInfo = new DetectionInfo();
        if (disposeType.equals("NOT")) {
            detectionInfo.setDisposeType(DisposeType.NOT);
        } else if (disposeType.equals("PICKUP")) {
            detectionInfo.setDisposeType(DisposeType.PICKUP);
        } else if (disposeType.equals("BACK")) {
            detectionInfo.setDisposeType(DisposeType.BACK);
        }
        feedbackInfo.setDetectionInfo(detectionInfo);

        feedbackInfo.setUser_id("567tfgg68787ghjg78798");//设置当前用户的user_id
        return feedbackInfo;
    }
}
