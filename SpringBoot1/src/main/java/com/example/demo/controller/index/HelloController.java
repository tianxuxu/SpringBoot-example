package com.example.demo.controller.index;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	
	
	
	
	
	//如果希望控制到权限层面，
	//可以使用@PreAuthorize(“hasPermission()”)。
	//这里只是用了其中的一个用法，更多的使用方法可以去看官方文档。
	//需要注意的是，Spring Security默认的角色前缀是”ROLE_”,
	//使用hasRole方法时已经默认加上了，因此我们在数据库里面的用户角色应该是“ROLE_user”，
	//在user前面加上”ROLE_”前缀。
	//表示访问这个方法需要user角色
	@PreAuthorize("hasRole('ADMIN')")
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
