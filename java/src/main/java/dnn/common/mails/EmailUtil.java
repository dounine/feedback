package dnn.common.mails;

import dnn.common.beans.PropertiesLoader;
import dnn.common.utils.PropertyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @ProjectName: [ ISSP智能服务共享平台 ]
 * @Package: [ oa.common.mails ]
 * @Author: [ liguiqin ]
 * @CreateTime: [ 2016年2月24日 上午11:43:09 ]
 * @Copy: [ bjike.com ]
 * @Version: [ v1.0 ]
 * @Description: [ 邮件发送工具类 ]
 */
public class EmailUtil {
    private static Logger CLONSOLE = LoggerFactory.getLogger(EmailUtil.class);

    //  邮件附件列表
    private static List<EmailAttachment> accessoriesList = new ArrayList<>();
    //简单验证邮箱格式是否正确正则
    private static String reg = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";// 邮箱格式正


    public static boolean SendMail(Email em) throws Exception {
        accessoriesList.removeAll(accessoriesList);
        init_email(em);
        // 发送带附件的邮件
        if (null != em.getAppendixPath()) {
            for (String path : em.getAppendixPath()) {
                AddAccessories(path);
            }
        }
        if (!sendHtmlEMail(em)) {
            CLONSOLE.info("邮件发失败");
            return false;
        } else {
            CLONSOLE.info("邮件发送成功");
            return true;
        }

    }


    private static void init_email(Email em) throws Exception {
        // 初始化邮件服务器
        em.setHost("smtp.163.com");
        PropertiesLoader loader = PropertyUtil.getInstance();
        // 登陆邮件服务器的用户名和密码 (默认使用系统用户发送)
        if (StringUtils.isBlank(em.getUsername()) ||
                StringUtils.isBlank(em.getPassword())) {
            em.setUsername(loader.getProperty("email.username"));
            em.setPassword(loader.getProperty("email.password"));
        }
        if (StringUtils.isBlank(em.getSender())) {
            em.setSender(loader.getProperty("email.username"));
            em.setSenderName(loader.getProperty("email.sender.name"));
        }


        if (null == em.getReceiver() || StringUtils.isBlank(em.getContent()) || StringUtils.isBlank(em.getSubject()) || StringUtils.isBlank(em.getSender())) {
            throw new Exception("收件人/发送人/主题/内容 不能为空!");
        }

        validateEmail(em);

    }

    public static boolean validateEmail(Email email) throws Exception {
        if (!email.getSender().matches(reg)) {
            throw new Exception("发件人邮件格式错误！");
        }
        if (null == email.getReceiver() || 0 == email.getReceiver().size()) {
            throw new Exception("收件人不能为空！");
        }
        for (String rver : email.getReceiver()) {
            if (!rver.matches(reg)) {
                throw new Exception("该<" + rver + ">收件人邮件格式错误！");
            }
        }
        return true;
    }


    /**
     * 添加附件
     *
     * @param path
     */
    private static void AddAccessories(String path) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        //是否为远程文件
        if (path.split("http")[0].equals("")) {
            attachment.setURL(new URL(path));
        } else {
            attachment.setPath(path);
        }
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        // 文件描述
        attachment.setDescription("venustech");
        try {
            attachment.setName(MimeUtility.encodeText(path.substring(path.lastIndexOf("/") + 1)));// 解决中文字符问题
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        accessoriesList.add(attachment);
    }

    /**
     * 发送HTML格式邮件，可以添加多个附件
     *
     * @return
     */
    private static boolean sendHtmlEMail(Email em) throws Exception {
        CLONSOLE.info("邮件发送中...");
        try {
            HtmlEmail email = new HtmlEmail();
            // smtp host
            email.setHostName(em.getHost());
            email.setCharset("UTF-8");

            email.setAuthentication(em.getUsername(), em.getPassword());
            // 接收人
            for (String receiver : em.getReceiver()) {
                email.addTo(receiver, getUserName(receiver));
            }

            if (null != em.getCc_address()) {
                // 抄送
                for (String cc : em.getCc_address()) {
                    email.addCc(cc, cc);
                }
            }
            if (null != em.getBcc_address()) {
                // 密送
                for (String bcc : em.getBcc_address()) {
                    email.addBcc(bcc, bcc);
                }
            }

            // 发送人
            email.setFrom(em.getSender(), em.getSenderName());
            // 标题
            email.setSubject(em.getSubject());
            // 邮件内容
            email.setHtmlMsg(em.getContent());
            // 添加附件(附件，可以定义多个附件对象)
            for (EmailAttachment att : accessoriesList) {
                email.attach(att);
            }
            //添加图片
            if (null != em.getImgMap()) {
                URL url = null;
                String cid = null;
                // 准备邮件数据
                // 准备邮件正文数据
                MimeBodyPart text = new MimeBodyPart();
                text.setContent(em.getContent(), "text/html;charset=UTF-8");

                for (Entry<String, String> m : em.getImgMap().entrySet()) {
                    //如果是网络路径
                    if (m.getValue().split("http")[0].equals("")) {
                        url = new URL(m.getValue());
                        cid = email.embed(url, m.getKey());
                        em.setContent(em.getContent().replaceAll(m.getKey(), cid));
                    } else {
                        // 准备图片数据
                        MimeBodyPart image = new MimeBodyPart();
                        DataHandler dh = new DataHandler(new FileDataSource(m.getValue()));
                        image.setDataHandler(dh);
                        image.setContentID(m.getKey());
                        // 描述数据关系
                        MimeMultipart mm = new MimeMultipart();
                        mm.addBodyPart(text);
                        mm.addBodyPart(image);
                        mm.setSubType("related");
                        email.addPart(mm);
                    }

                }


            }

            //email.addHeader("Disposition-Notification-To", "xinaml@qq.com");
            // 发送
            email.setHtmlMsg(em.getContent());
            email.send();

        } catch (EmailException e) {
            return false;
        }
        return true;
    }


    /**
     * 截取邮件地址前面部分作为收件人名称
     *
     * @param username
     * @return
     */
    private static String getUserName(String username) {
        return username.split("@")[0];
    }

}