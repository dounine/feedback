package dnn.service.feedbackInfo;

import dnn.dto.SearchJson;
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
import dnn.enums.RestrictionType;
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
                User user = serUser.findById(model.getUser_id());
                model.setCustomerName(user.getUsername());
            }catch (SerException e){
                e.printStackTrace();
            }
        });
    }

    private  void  intoDto(FeedbackInfoDto dto){
        List<Online>  userSession=UserSession.sessions();
//        UserType userType= userSession.get(0).getUserType();
//        String userId = userSession.get(0).getId();
        User user = new User();

        String userId ="57cfdf3179d23f8f570f49fd";
        Map<String,Object> maps =dto.getConditions();
        if(user.getUserType().equals(UserType.CUSTOM)){
            maps.put("user_id",userId);
            maps.put("feedbackStatus", dto.getStatus().name());
        }
    }

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
