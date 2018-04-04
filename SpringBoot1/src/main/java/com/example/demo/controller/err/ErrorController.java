package com.example.demo.controller.err;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.util.json.JsonResult;

@Controller
@RequestMapping("err")
public class ErrorController {

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {

		int a = 1 / 0;

		return "thymeleaf/error";
	}

	@RequestMapping("/ajaxerror")
	public String ajaxerror() {

		return "thymeleaf/ajaxerror";
	}

	@RequestMapping("/getAjaxerror")
	@ResponseBody
	public JsonResult getAjaxerror() {

		int a = 1 / 0;

		return JsonResult.ok();
	}
}
