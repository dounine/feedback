package dnn.web.admin.clouddisk.captcha;

import com.dounine.clouddisk360.captthread.CaptchaThreadValidator;
import com.dounine.clouddisk360.captthread.CaptchaValidator;
import com.dounine.clouddisk360.parser.PassportParser;
import com.dounine.clouddisk360.parser.deserializer.login.LoginUserToken;
import com.dounine.clouddisk360.parser.deserializer.passport.Passport;
import com.dounine.clouddisk360.parser.deserializer.passport.PassportParameter;
import com.dounine.clouddisk360.store.BasePathCommon;
import dnn.common.json.ResponseText;
import dnn.web.admin.clouddisk.ClouddiskAction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("admin/clouddisk/captcha")
public class CaptchaAction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaAction.class);

	@RequestMapping(value = "valid", method = RequestMethod.POST)
	public ResponseText validCaptcha(String account, CaptchaValidator captchaValidator) {
		ResponseText responseText = new ResponseText();
		responseText.setErrno(1);
		CaptchaThreadValidator.validCaptchaValidator(account, captchaValidator);
		boolean appRun = true,tout=true;
		int scount = 0;
		while(appRun&&tout){
			try {
				Thread.sleep(200);//轮循查询登录响应内容
				scount+=1;
				if(scount>=30){
					responseText.setMsg("登录超时");
					responseText.setErrno(0);
					tout = false;
				}
				CaptchaValidator cv = CaptchaThreadValidator.getCaptchaValidator(account);
				if(StringUtils.isNotBlank(cv.getValidMsg())){//登录信息不为空,证明线程处理完毕
					responseText.setMsg(cv.getValidMsg());
					responseText.setErrno(cv.isSuccess()?0:1);
					appRun = false;
				}
			} catch (InterruptedException e) {
				responseText.setMsg(e.getMessage());
				appRun = false;
			}
		}
		return responseText;
	}

	@RequestMapping(value = "read",method = RequestMethod.GET)
	public void readCaptcha(String account, OutputStream outputStream){
		LoginUserToken loginUserToken = ClouddiskAction.LOGIN_USER_TOKEN;
		if(CaptchaThreadValidator.hasCaptcha(account)){
			CaptchaValidator captchaValidator = CaptchaThreadValidator.getCaptchaValidator(loginUserToken.getAccount());
			if(null!=captchaValidator&&!captchaValidator.isSuccess()){
				PassportParser passportParser = new PassportParser(loginUserToken);
				PassportParameter passportParameter = new PassportParameter();
				passportParameter.setManual(true);
				Passport passport = passportParser.parse(passportParameter);
				if(null!=passport){
					try {
						byte[] bs = FileUtils.readFileToByteArray(new File(passport.getCaptchaUrl()));
						outputStream.write(bs);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							outputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}else{
			try {
				File captchaFile = new File(BasePathCommon.BASE_PATH+ loginUserToken.getAccount()+"/captcha.jpg");
				if(captchaFile.exists()){
					outputStream.write(FileUtils.readFileToByteArray(captchaFile));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "needCaptcha")
	public boolean needCaptcha(String account) {
		boolean status = true;
		CaptchaValidator captchaValidator = CaptchaThreadValidator.getCaptchaValidator(account);
		if(null==captchaValidator){//没有验证码
			status = false;
		}else if(captchaValidator.isSuccess()){//需要验证码,但已经登录成功
			status = false;
		}
		return status;
	}

}
