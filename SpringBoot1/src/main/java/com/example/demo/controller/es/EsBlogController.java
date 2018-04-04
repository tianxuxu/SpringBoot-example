package com.example.demo.controller.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.es.EsBlog;
import com.example.demo.service.es.EsBlogService;
import com.example.demo.util.json.JsonResult;

@RestController
@RequestMapping("/esBlog")
public class EsBlogController {

	@Autowired
	private EsBlogService esBlogService;
	
	@GetMapping("/save")
	public JsonResult save() {
		EsBlog esBlog=new EsBlog();
		esBlogService.save(esBlog);
		return new JsonResult().ok("数据保存成功");
	}
	
}
