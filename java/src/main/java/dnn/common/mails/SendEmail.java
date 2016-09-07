package dnn.common.mails;

import sun.security.provider.MD5;

import java.util.*;

/**
 * 
 * @ProjectName:		[ ISSP智能服务共享平台 ]
 * @Package:			[ oa.common.mails ]
 * @Author:				[ liguiqin ]
 * @CreateTime:			[ 2016年2月23日 下午5:24:28 ]
 * @Copy:				[ bjike.com ]
 * @Version:			[ v1.0 ]
 * @Description:   		[ 发送邮件实例 ]
 */
public class SendEmail {
	/**
	 * 发送内容内嵌图片  ：  这是一张图片<img src='cid:imageName1' />第二张图片<img src='cid:imageName2' />
	 * @param
	 *  ，要添加密送人或者抄送人 直接初始化邮件实体类相应list列表即可 要添加密送人或者抄送人
	 * @throws Exception
	 */
	public static void main(String[] args)throws Exception{


		//初始化发送人与接收人列表
		Email email = new Email("xinaml@qq.com"); //使用系统账户，不需要再设置用户登录名及密码

		//发送人名字（默认为 ISSP智能服务共享平台）
		email.setSenderName("小明...");


		//设置邮件要发送的内容以及标题信息
		email.initEmailInfo("这叫下午福利茶时间  ", " 这是一张图片<img src='cid:imageName1' />第二张图片<img src='cid:imageName2' /><strong>资源部有人吗</strong>测试  ！");

		//附件路径list
		List<String> appendixPath = new ArrayList<>();
		
		//图片名称及路径map
		Map<String, String> imgMap = new HashMap<>(); 
		
		//附件文件路径为本地服务器绝对路径的文件如D://dir/file.txt 或者远程文件请以 http开头
		appendixPath.add("D:\\黎贵钦日报补传-.xlsx");
		email.setAppendixPath(appendixPath);
		//上传后的图片，必须全部是真实url地址如http://xxx.jpg 如 http://101.200.198.169/static/admin/img/user/head/h/2.jpg
		//或者要么是全部是本地的文件路径如D://dir/a.jpg
		//不能混搭使用，否则图片可能会显示不正常
		//imgMap.put("imageName1", "a1.jpg");
		//imgMap.put("imageName2", "a2.jpg");
		
		imgMap.put("imageName1", "http://101.200.198.169/static/admin/img/user/head/h/1.jpg");
		imgMap.put("imageName2", "http://101.200.198.169/static/admin/img/user/head/h/2.jpg");
		email.setImgMap(imgMap);
		//---------------------------------验证并发送邮件

		EmailUtil.SendMail(email);

	}

}
