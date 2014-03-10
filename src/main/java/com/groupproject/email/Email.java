package com.groupproject.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
	private String username="xhmiaoyuan@163.com";
	private String password="MY19901111989my";	
	private String title="Notification";
	private String sendserver="smtp.163.com";
	public void setEmail(String email,String content){
		 MailSenderInfo mailInfo = new MailSenderInfo();    
	      mailInfo.setMailServerHost(sendserver);    
	      mailInfo.setMailServerPort("25");    
	      mailInfo.setValidate(true);    
	      mailInfo.setUserName(username);    
	      mailInfo.setPassword(password);//您的邮箱密码    
	      mailInfo.setFromAddress(username);    
	      mailInfo.setToAddress(email);    
	      mailInfo.setSubject(title);    
	      mailInfo.setContent(content);    
	         //这个类主要来发�?邮件   
	      SimpleMailSender sms = new SimpleMailSender();   
	          sms.sendTextMail(mailInfo);//发�?文体格式    
	          sms.sendHtmlMail(mailInfo);//发�?html格式   
		
	}
}
