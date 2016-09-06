package dnn.common.mails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: [ ISSP智能服务共享平台 ]
 * @Package: [ oa.common.mails ]
 * @Author: [ liguiqin ]
 * @CreateTime: [ 2016年2月23日 下午5:19:47 ]
 * @Copy: [ bjike.com ]
 * @Version: [ v1.0 ]
 * @Description: [ 邮件实体类，用来初始化邮件信息 ]
 */
public class Email {
    private String sender;//发送人
    private String senderName = "Feedback平台";//发送人名称（默认）
    private List<String> receiver;//接收人
    private String subject;//发送主题
    private String content;//发送内容
    private String username;//登录服务器用户名
    private String password;//登录服务器密码
    private String host;//邮件服务器
    private List<String> cc_address;//抄送人人
    private List<String> bcc_address;//密送人
    private Map<String, String> imgMap;//发送图片地址
    private List<String> appendixPath;//发送附件地址

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public Email(String sender, List<String> receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public Email(String sender, String senderName, List<String> receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.senderName = senderName;
    }

    public Email(String sender, String receiver) {
        this.sender = sender;
        this.receiver = new ArrayList<>(1);
        this.receiver.add(receiver);
    }

    public void initEmailInfo(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public void initEmailInfo(String subject, String content, Map<String, String> imgMap, List<String> appendixPath) {
        this.subject = subject;
        this.content = content;
        this.imgMap = imgMap;
        this.appendixPath = appendixPath;
    }

    public void initEmailInfo(String subject, String content, Map<String, String> imgMap) {
        this.subject = subject;
        this.content = content;
        this.imgMap = imgMap;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getImgMap() {
        return imgMap;
    }

    public void setImgMap(Map<String, String> imgMap) {
        this.imgMap = imgMap;
    }

    public List<String> getAppendixPath() {
        return appendixPath;
    }

    public void setAppendixPath(List<String> appendixPath) {
        this.appendixPath = appendixPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public List<String> getCc_address() {
        return cc_address;
    }

    public void setCc_address(List<String> cc_address) {
        this.cc_address = cc_address;
    }

    public List<String> getBcc_address() {
        return bcc_address;
    }

    public void setBcc_address(List<String> bcc_address) {
        this.bcc_address = bcc_address;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
