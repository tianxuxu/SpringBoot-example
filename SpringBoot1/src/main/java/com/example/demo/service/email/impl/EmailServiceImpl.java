package com.example.demo.service.email.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.demo.service.email.EmailService;

import freemarker.template.Template;


public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean

	@Value("${spring.mail.username}")
	private String Sender; // 读取配置文件中的参数

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer; // 自动注入
	
	public void sendSimpleMail(String toAdress) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(Sender);
		
		message.setTo(toAdress); // 自己给自己发送邮件
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
		
		
	}

	
	public void sendHtmlMail(String toAdress) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(toAdress);
			helper.setSubject("标题：发送Html内容");

			StringBuffer sb = new StringBuffer();
			sb.append("<h1>大标题-h1</h1>").append("<p style='color:#F00'>红色字</p>")
					.append("<p style='text-align:right'>右对齐</p>");
			helper.setText(sb.toString(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

	
	public void sendAttachmentsMail(String toAdress) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(toAdress);
			helper.setSubject("主题：带附件的邮件");
			helper.setText("带附件的邮件内容"); // 注意项目路径问题，自动补用项目路径
			FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/Koala.jpg"));
			// 加入邮件
			helper.addAttachment("Koala.jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

	
	public void sendInlineMail(String toAdress) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(toAdress);
			helper.setSubject("主题：带静态资源的邮件");
			// 第二个参数指定发送的是HTML格式,同时cid:是固定的写法
			helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:picture' /></body></html>", true);

			FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/Koala.jpg"));
			helper.addInline("picture", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

	

	
	/**
	 *  ，Spring提供FreeMarkerTemplateUtils工具类来完成解析模板的任务。
	 *  替换模版文件参数,用帮助类(FreeMarkerTemplateUtils)得到文本数据流
	 */
	
	public void sendTemplateMail(String toAdress) {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(toAdress);
			helper.setSubject("主题：模板邮件");
			
			Map<String,String> map = new HashMap<>();
			map.put("imgurl", "https://www.baidu.com/img/bd_logo1.png");
			map.put("linkurl", "www.baidu.com");
			map.put("username", "啦啦德玛西亚");
			
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			helper.setText(html, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}
