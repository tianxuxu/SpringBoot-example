package com.example.demo.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.util.pojo.Msg;

@Controller
public class IndexController {

	// @Autowired
	// private JavaMailSender mailSender; //自动注入的Bean

	@GetMapping("/")
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		// request.setAttribute("ctx", request.getContextPath());
		return "index";
	}
}