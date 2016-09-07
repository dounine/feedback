package dnn.service.feedbackInfo;

import dnn.dto.feedbackInfo.FeedbackInfoDto;
import dnn.common.exception.SerException;
import dnn.entity.feedbackInfo.FeedbackInfo;
import dnn.entity.feedbackInfo.detection.DetectionInfo;
import dnn.service.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lgq on 16-9-4.
 */
@Service
public class FeedbackInfoSerImpl extends ServiceImpl<FeedbackInfo,FeedbackInfoDto> implements ISerFeedbackInfo {

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


   public static void main(String[] args){

   }

    @Override
    public List<FeedbackInfo> findByUserId(String user_id)throws SerException {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("user_id",user_id);
        return findByCis(conditions);
    }
}
