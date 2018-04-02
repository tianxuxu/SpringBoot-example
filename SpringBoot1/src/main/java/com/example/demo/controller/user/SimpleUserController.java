package com.example.demo.controller.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.pojo.SysUser;
import com.example.demo.service.user.UserService;
import com.example.demo.util.json.JsonResult;

/**
 * 作用体现Mybatis 简单的操作不需要自己去实现
 * @author tianxuxu
 *
 */
@RestController
@RequestMapping("/simpleUsers")
public class SimpleUserController {

	
	@Autowired
	private UserService  userService;
	
	private String userId="123456";
	
	
	@GetMapping("/c")
	public JsonResult saveUser() throws Exception{
		SysUser user=new SysUser();
		user.setId(userId);
		user.setUsername("demo" + new Date());
		user.setNickname("demo" + new Date());
		user.setPassword("root");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		userService.saveUser(user);
		return new JsonResult().ok("保存成功");
	}

	@GetMapping("/u")
	public JsonResult updateUser() {
		SysUser  sysUser=userService.queryUserById(userId);
		sysUser.setUsername("fuck"+new Date());
		userService.updateUser(sysUser);
		return  new JsonResult().ok("更新成功");
	}

	@GetMapping("/d")
	public JsonResult deleteUser() {
		userService.deleteUser(userId);
		return  new JsonResult().ok("删除成功");
	}

	//失败的方法
	@GetMapping("/r")
	public JsonResult queryUserById() {
		SysUser  sysUser=userService.queryUserById(userId);
		return  new JsonResult().ok(sysUser);
	}
	
}
