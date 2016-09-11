package dnn.service.feedbackInfo;

import dnn.common.utils.UserContext;
import dnn.dto.SearchJson;
import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.exception.SerException;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.entity.feedbackInfo.specimen.ChemicalCell;
import dnn.entity.feedbackInfo.specimen.PhysicalEnergy;
import dnn.entity.user.User;
import dnn.entity.user.UserType;
import dnn.enums.*;
import dnn.service.ServiceImpl;
import dnn.service.user.ISerUser;
import dnn.service.user.session.Online;
import dnn.service.user.session.UserSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by lgq on 16-9-4.
 */
@Service
public class FeedbackInfoSerImpl extends ServiceImpl<FeedbackInfo,FeedbackInfoDto> implements ISerFeedbackInfo {

    @Autowired
    private ISerUser serUser;

    @Override
    public List<FeedbackInfo> findByPage(FeedbackInfoDto dto) throws SerException {
        // admin
        intoDto(dto);
        List<FeedbackInfo> feedbackInfos = super.findByPage(dto);
        initUser(feedbackInfos);
        return feedbackInfos;
    }

    @Override
    public Long count(FeedbackInfoDto dto) throws SerException {
        intoDto(dto);
        return super.count(dto);
    }

    private void initUser(  List<FeedbackInfo> feedbackInfos ) {
        Stream<FeedbackInfo> stream= feedbackInfos.stream();
        stream.forEach(model->{
            try{
                User user = serUser.findById(model.getUserId());
                model.setCustomerName(user.getUsername());
            }catch (SerException e){
                e.printStackTrace();
            }
        });
    }

    private  void  intoDto(FeedbackInfoDto dto){
//        UserType userType =UserContext.currentUser().getUserType();
//        String userId = UserContext.currentUser().getId();
        User user = new User();
        user.setUserType(UserType.MANAGER);//TODO 从当前登录的用户获取
        String userId ="57cfdf3179d23f8f570f49fd";
        Map<String,Object> maps =dto.getConditions();
        if(user.getUserType().equals(UserType.CUSTOM)){
            maps.put("userId",userId);
        }

        if(dto.getStatus()!=null){
            maps.put("feedbackStatus", dto.getStatus().name());
        }
        if(dto.getSearchCondition()!=null){
            maps.put("detectionNum", dto.getSearchCondition());//根据工单号查询
        }
        maps.put("finalCopyField",1);


    }

    /**
     * 订单信息提交
     * @param feedbackInfo
     * @throws SerException
     */
    @Override
    public void save(FeedbackInfo feedbackInfo) throws SerException {
        String chemicalCellSampleName = feedbackInfo.getChemicalCell().getSampleName();
        String physicalEnergySampleName = feedbackInfo.getPhysicalEnergy().getSampleName();
        if(StringUtils.isNotBlank(chemicalCellSampleName) && StringUtils.isNotBlank(physicalEnergySampleName)){
            throw  new SerException("电池只能选填一项");
        }
        if(StringUtils.isBlank(chemicalCellSampleName)){
            feedbackInfo.setChemicalCell(null);
        }else{
            feedbackInfo.setPhysicalEnergy(null);
        }
        feedbackInfo= getDealFeedbackInfo(feedbackInfo);
        feedbackInfo.setOperatorStatus(OperatorStatus.INIT);
        feedbackInfo.setCopyNum(1L);
        feedbackInfo.setFinalCopyField(1L);

        super.save(feedbackInfo);
    }

    @Override
    public List<FeedbackInfo> findByUserId(String userId)throws SerException {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("userId",userId);
        return findByCis(conditions);
    }

    @Override
    public FeedbackInfo findOneInfo(FeedbackInfo feedbackInfo) throws SerException {
        Map<String, Object> conditions = new HashMap<>(0);
        Map<String, Object> criteria = new HashMap<>(1);
        //得到最新的版本
        List<Object> field=new ArrayList<>(0);
        field.add("copyNum");
        criteria.put("userId",feedbackInfo.getUserId());
        criteria.put("detectionNum",feedbackInfo.getDetectionNum());
        FeedbackInfo maxByCopyNum = findByMax(field,criteria);

        User user = serUser.findById(feedbackInfo.getUserId());
        maxByCopyNum.setCustomerName(user.getUsername());

        return maxByCopyNum;
    }

    @Override
    public void updateOneInfo(FeedbackInfo feedbackInfo) throws SerException {
        String chemicalCellSampleName = feedbackInfo.getChemicalCell().getSampleName();
        String physicalEnergySampleName = feedbackInfo.getPhysicalEnergy().getSampleName();
        if(StringUtils.isNotBlank(chemicalCellSampleName) && StringUtils.isNotBlank(physicalEnergySampleName)){
            throw  new SerException("电池只能选填一项");
        }
        if(StringUtils.isBlank(chemicalCellSampleName)){
            feedbackInfo.setChemicalCell(null);
        }else{
            feedbackInfo.setPhysicalEnergy(null);
        }
//        UserType userType =UserContext.currentUser().getUserType();
        UserType userType= UserType.MANAGER;//TODO 从当前登录的用户获取
        //得到最新的版本
        List<Object> field=new ArrayList<>(0);
        Map<String,Object> map =new HashMap<>(0);
        Map<String,Object> updateMap =new HashMap<>(1);
        field.add("copyNum");
        map.put("detectionNum",feedbackInfo.getDetectionNum());
        map.put("userId",feedbackInfo.getUserId());
        FeedbackInfo maxByCopyNum = findByMax(field,map);
        super.UpdateByCis2(maxByCopyNum,"finalCopyField",0L);

        String str1=maxByCopyNum.getOperatorStatus().name().toString();
        String str2=null;
        if(userType.equals(UserType.CUSTOM)){
            switch (str1){
                case  "INIT":
                    feedbackInfo.setOperatorStatus(OperatorStatus.INIT);//客户自行修改还是init
                case  "MANAGERCONFIRM":
                    feedbackInfo.setOperatorStatus(OperatorStatus.CUSTOMCONFIRM);//客户确认
                case  "CUSTOMCONFIRM":
                    feedbackInfo.setOperatorStatus(OperatorStatus.CUSTOMFINALCONFIRM);//客户最终确认
                case  "CHARGENOT":
                    feedbackInfo.setOperatorStatus(OperatorStatus.CHARGECONFIRM);//客户最终确认
            }
        }else if(userType.equals(UserType.MANAGER)){
            switch (str1){
                case  "INIT":
                    feedbackInfo.setOperatorStatus(OperatorStatus.MANAGERCONFIRM);//管理员在init状态修改后变为managerconfirm
                case  "MANAGERCONFIRM":
                    feedbackInfo.setOperatorStatus(OperatorStatus.MANAGERCONFIRM);//管理员确认
            }
        }


        feedbackInfo.setCopyNum(maxByCopyNum.getCopyNum()+001L);
        feedbackInfo.setDetectionNo(maxByCopyNum.getDetectionNo());
        feedbackInfo.setFinalCopyField(1L);
        if(feedbackInfo.getOperatorStatus()!=null){
            super.save(feedbackInfo);
        }else{
           Exception e =new Exception();
            e.printStackTrace();
        }

    }

    @Override
    public void confirmFeedback(FeedbackInfo feedbackInfo) throws SerException {
        String chemicalCellSampleName = feedbackInfo.getChemicalCell().getSampleName();
        String physicalEnergySampleName = feedbackInfo.getPhysicalEnergy().getSampleName();
        if(StringUtils.isNotBlank(chemicalCellSampleName) && StringUtils.isNotBlank(physicalEnergySampleName)){
            throw  new SerException("电池只能选填一项");
        }
        if(StringUtils.isBlank(chemicalCellSampleName)){
            feedbackInfo.setChemicalCell(null);
        }else{
            feedbackInfo.setPhysicalEnergy(null);
        }

        //得捣最新的版本
        List<Object> field=new ArrayList<>(0);
        Map<String,Object> map =new HashMap<>(0);
//        UserType userType =UserContext.currentUser().getUserType();
        UserType userType= UserType.MANAGER;//TODO 从当前登录的用户获取
        field.add("copyNum");
        map.put("userId",feedbackInfo.getUserId());
        map.put("detectionNum",feedbackInfo.getDetectionNum());
        FeedbackInfo maxByCopyNum = findByMax(field,map);

        if(maxByCopyNum.getOperatorStatus().equals(OperatorStatus.CHARGECONFIRM) && userType.equals(UserType.MANAGER)){
            feedbackInfo.setOperatorStatus(OperatorStatus.HANDLECONFIRM);//客户最终确认
        }
        feedbackInfo.setCopyNum(maxByCopyNum.getCopyNum()+001L);
        super.save(feedbackInfo);
    }

    @Override
    public int deleteFeedback(FeedbackInfo feedbackInfo) throws SerException {
        Map<String ,Object> map =new HashMap<>(1);
        map.put("detectionNum",feedbackInfo.getDetectionNum());
        map.put("userId",feedbackInfo.getUserId());
        int result = super.removeByCis(map);
        return result;
    }

    public FeedbackInfo getDealFeedbackInfo(FeedbackInfo feedbackInfo) {
//        UserType userType =UserContext.currentUser().getUserType();
//        String userId = UserContext.currentUser().getId();
        User user = new User();
        user.setUserType(UserType.CUSTOM);
        String userId = "57cfdf3179d23f8f570f49fd";//TODO 从当前登录的用户获取
        if (user.getUserType().equals(UserType.CUSTOM)) {
            feedbackInfo.setUserId(userId);//设置当前用户的user_id
            //设置工单号
            makeDetectionNum(feedbackInfo, userId);
        }
        return feedbackInfo;
    }

    private FeedbackInfo makeDetectionNum(FeedbackInfo feedbackInfo,String userId){
        String detectionNum=null;
        Long detectionNo=001L;
        Date date = new Date();

        Map<String,Object> map =new HashMap<>(0);
        List<Object> field = new ArrayList<>(1);

        field.add("detectionNum");
        field.add(userId);
        map.put("userId",userId);
        FeedbackInfo feedbackInfos=null;
        try {
            feedbackInfos = findByMax(field,map);
        }catch (SerException e){
            e.printStackTrace();
        }
        if(feedbackInfos != null){
            if(feedbackInfos.getDetectionNo() !=0){
                detectionNo = feedbackInfos.getDetectionNo()+001L;
            }else{
                detectionNo = 001L;
            }
        }

        detectionNum = date.getYear()+1900+"ST"+detectionNo;
        feedbackInfo.setDetectionNo(detectionNo);
        feedbackInfo.setDetectionNum(detectionNum);

        return feedbackInfo;
    }
}
