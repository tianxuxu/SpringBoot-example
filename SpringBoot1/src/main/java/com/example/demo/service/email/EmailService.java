package com.example.demo.service.email;


/**
 * 发送邮件的服务类
 * @author tianxuxu
 *
 */
public interface EmailService {

	public void sendSimpleMail(String toAdress) throws Exception;
	
	public void sendHtmlMail(String toAdress);
	
	public void sendAttachmentsMail(String toAdress);
	
	public void sendInlineMail(String toAdress);
	
	public void sendTemplateMail(String toAdress);
}
