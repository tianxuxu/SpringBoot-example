package com.example.demo.controller.index;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.json.JsonResult;
import com.example.demo.util.pojo.EnvResource;

@RestController
@RequestMapping("/index")
public class HelloController {
	
	@Autowired
	private EnvResource envResource;

	@GetMapping("/hi")
	public String hi() {
		return "Hello world!!!";
	}
	
	@GetMapping("/getResource")
	public JsonResult getResource() {
		EnvResource config=new EnvResource();
		BeanUtils.copyProperties(envResource, config);
		return new JsonResult().ok(config);
	}
}
