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
        /*List<Online>  userSession=UserSession.sessions();
        UserType userType= userSession.get(0).getUserType();
        String userId = userSession.get(0).getId();

        Map<String,Object> maps =dto.getConditions();
        if(userType.equals(UserType.CUSTOM)){
            maps.put("userId",userId);
            maps.put("feedbackStatus", dto.getStatus().name());
//            maps.put("detectionNum", dto.getSearchCondition());
        }*/

        User user = new User();
        String userId ="57cfdf3179d23f8f570f49fd";
        Map<String,Object> maps =dto.getConditions();
        if(user.getUserType().equals(UserType.CUSTOM)){
            maps.put("userId",userId);
            maps.put("feedbackStatus", dto.getStatus().name());
            maps.put("detectionNum", dto.getSearchCondition());//根据工单号查询
        }
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
        Map<String, Object> conditions = new HashMap<>();
        //得捣最新的版本
        List<Object> field=new ArrayList<>(0);
        field.add("copyNum");
        FeedbackInfo maxByCopyNum = findByMax(field);

        conditions.put("detectionNum",feedbackInfo.getDetectionNum());
        conditions.put("copyNum",maxByCopyNum.getCopyNum());
        conditions.put("userId",feedbackInfo.getUserId());

//        FeedbackInfo feedbackInfo1 =findOne(conditions);
        return findOne(conditions);
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
        List<Online>  userSession=UserSession.sessions();
        UserType userType= userSession.get(0).getUserType();

        FeedbackInfo copyInfo = findById(feedbackInfo.getId());
        if(copyInfo.getOperatorStatus().equals(OperatorStatus.INIT) && userType.equals(UserType.CUSTOM)){
            feedbackInfo.setOperatorStatus(OperatorStatus.INIT);
        }else if(copyInfo.getOperatorStatus().equals(OperatorStatus.INIT) && userType.equals(UserType.MANAGER)){
            feedbackInfo.setOperatorStatus(OperatorStatus.MANAGERCONFIRM);
        }else if(copyInfo.getOperatorStatus().equals(OperatorStatus.MANAGERCONFIRM) && userType.equals(UserType.CUSTOM)){
            feedbackInfo.setOperatorStatus(OperatorStatus.CUSTOMCONFIRM);
        }else if(copyInfo.getOperatorStatus().equals(OperatorStatus.MANAGERCONFIRM) && userType.equals(UserType.MANAGER)){
            feedbackInfo.setOperatorStatus(OperatorStatus.MANAGERCONFIRM);
        }else if(copyInfo.getOperatorStatus().equals(OperatorStatus.CUSTOMCONFIRM) && userType.equals(UserType.CUSTOM)){
            feedbackInfo.setOperatorStatus(OperatorStatus.CUSTOMFINALCONFIRM);
        }else if(copyInfo.getOperatorStatus().equals(OperatorStatus.CUSTOMCONFIRM) && userType.equals(UserType.MANAGER)){
            feedbackInfo.setOperatorStatus(OperatorStatus.MANAGERCONFIRM);
        }

        //得捣最新的版本
        List<Object> field=new ArrayList<>(0);
        field.add("copyNum");
        FeedbackInfo maxByCopyNum = findByMax(field);
        feedbackInfo.setCopyNum(maxByCopyNum.getCopyNum()+001L);

        super.save(feedbackInfo);
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
        field.add("copyNum");
        FeedbackInfo maxByCopyNum = findByMax(field);
        feedbackInfo.setCopyNum(maxByCopyNum.getCopyNum()+001L);
        feedbackInfo.setOperatorStatus(OperatorStatus.HANDLECONFIRM);
        super.save(feedbackInfo);
    }

    @Override
    public void uploadChargeFile(HttpServletRequest request) {
        UploadContext upload = new UploadContext() {
            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public int getContentLength() {
                return 0;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }
        };

        String rootPath2=getClass().getResource("../../../../../").getFile().toString();
        rootPath2 = rootPath2+"/doc";
        File templeFile =new File(rootPath2);
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024*5); // 设置缓冲区大小，这里是5M
            factory.setRepository(templeFile);// 设置缓冲区目录
            ServletFileUpload uploadfile = new ServletFileUpload(factory);

            uploadfile.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

            List<FileItem> items = uploadfile.parseRequest(request);// 得到所有的文件
            Iterator<FileItem> i = items.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                String fileName = fi.getName();
                if (fileName != null) {
                    File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题
                    File savedFile = new File(rootPath2, fullFile.getName());
                    fi.write(savedFile);
                }
            }
            System.out.print("upload succeed");
        } catch (Exception e) {

        }
    }

    public FeedbackInfo getDealFeedbackInfo(FeedbackInfo feedbackInfo) {

        List<Online>  userSession=UserSession.sessions();
        UserType userType= userSession.get(0).getUserType();
        String userId = userSession.get(0).getId();
        if(userType.equals(UserType.CUSTOM)){
            feedbackInfo.setUserId(userId);//设置当前用户的user_id
//            feedbackInfo.setUserId("57cea14279d26cd0708e9d18");//设置当前用户的user_id
            //设置工单号
            makeDetectionNum(feedbackInfo,userId);
        }
        return feedbackInfo;
    }

    private FeedbackInfo makeDetectionNum(FeedbackInfo feedbackInfo,String userId){
        String detectionNum=null;
        Long detectionNo=001L;
        Date date = new Date();

        List<Object> field = new ArrayList<>(1);
        if(feedbackInfo.getChemicalCell()!=null){
            field.add("chemicalCell.chemicalCellSubmitDate");
        }else{
            field.add("physicalEnergy.physicalEnergySubmitDate");
        }

        field.add(userId);
        FeedbackInfo feedbackInfos=null;
        try {
            feedbackInfos = findByMax(field);
        }catch (SerException e){
            e.printStackTrace();
        }
        if(feedbackInfos.getDetectionNo() !=0){
            detectionNo = feedbackInfos.getDetectionNo()+001L;
        }else{
            detectionNo = 001L;
        }
        detectionNum = date.getYear()+"ST"+detectionNo;
        feedbackInfo.setDetectionNo(detectionNo);
        feedbackInfo.setDetectionNum(detectionNum);

        return feedbackInfo;
    }
}
