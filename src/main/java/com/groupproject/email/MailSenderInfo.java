package com.groupproject.email;
import java.util.Properties;    
public class MailSenderInfo {    
    // åé?é®ä»¶çæå¡å¨çIPåç«¯å?   
    private String mailServerHost;    
    private String mailServerPort = "25";    
    // é®ä»¶åé?èçå°å    
    private String fromAddress;    
    // é®ä»¶æ¥æ¶èçå°å    
    private String toAddress;    
    // ç»éé®ä»¶åé?æå¡å¨çç¨æ·ååå¯ç     
    private String userName;    
    private String password;    
    // æ¯å¦é?¦èº«ä»½éªè¯    
    private boolean validate = false;    
    // é®ä»¶ä¸»é¢    
    private String subject;    
    // é®ä»¶çææ¬åå®?   
    private String content;    
    // é®ä»¶éä»¶çæä»¶å    
    private String[] attachFileNames;      
    /**   
      * è·å¾é®ä»¶ä¼è¯å±æ?   
      */    
    public Properties getProperties(){    
      Properties p = new Properties();    
      p.put("mail.smtp.host", this.mailServerHost);    
      p.put("mail.smtp.port", this.mailServerPort);    
      p.put("mail.smtp.auth", validate ? "true" : "false");    
      return p;    
    }    
    public String getMailServerHost() {    
      return mailServerHost;    
    }    
    public void setMailServerHost(String mailServerHost) {    
      this.mailServerHost = mailServerHost;    
    }   
    public String getMailServerPort() {    
      return mailServerPort;    
    }   
    public void setMailServerPort(String mailServerPort) {    
      this.mailServerPort = mailServerPort;    
    }   
    public boolean isValidate() {    
      return validate;    
    }   
    public void setValidate(boolean validate) {    
      this.validate = validate;    
    }   
    public String[] getAttachFileNames() {    
      return attachFileNames;    
    }   
    public void setAttachFileNames(String[] fileNames) {    
      this.attachFileNames = fileNames;    
    }   
    public String getFromAddress() {    
      return fromAddress;    
    }    
    public void setFromAddress(String fromAddress) {    
      this.fromAddress = fromAddress;    
    }   
    public String getPassword() {    
      return password;    
    }   
    public void setPassword(String password) {    
      this.password = password;    
    }   
    public String getToAddress() {    
      return toAddress;    
    }    
    public void setToAddress(String toAddress) {    
      this.toAddress = toAddress;    
    }    
    public String getUserName() {    
      return userName;    
    }   
    public void setUserName(String userName) {    
      this.userName = userName;    
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
    public void setContent(String textContent) {    
      this.content = textContent;    
    }    
} 