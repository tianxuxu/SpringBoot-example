package com.example.demo.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.SysUser;
import com.example.demo.service.user.UserService;
import com.example.demo.util.json.JsonResult;

/**
 * 完成一些复杂的sql查询 这些都是测试所用的接口 完成了实现 不过自己还没有验证
 * 
 * @author tianxuxu
 *
 */

@RestController
@RequestMapping("/complicateUsers")
public class ComplicatedUserController {

	@Autowired
	private UserService userService;

	/*
	 * 按照指定模板模糊查询
	 */
	public List<SysUser> queryUserList(SysUser user) {
		return userService.queryUserList(user);
	}

	public JsonResult queryUserListPaged() {
		int page = 1;
		int pageSize = 10;
		SysUser user = new SysUser();
		user.setNickname("demo");

		return new JsonResult().ok(userService.queryUserListPaged(user, page, pageSize));
	}

	// 定制查询没有实现
	@GetMapping("/queryUserByIdCustom")
	public JsonResult queryUserByIdCustom(@RequestParam("userId") String userId) {

		return new JsonResult().ok(userService.queryUserByIdCustom(userId));
	}

	// 体现事务下的保存
	@GetMapping("saveUserTransactional")
	public JsonResult saveUserTransactional() {
		SysUser user = new SysUser();
		user.setId("123456");
		user.setUsername("complicated" + new Date());
		user.setNickname("complicated" + new Date());
		user.setPassword("abc123");
		user.setIsDelete(0);
		user.setRegistTime(new Date());
		userService.saveUserTransactional(user);

		return new JsonResult().ok("保存成功");
	}

}
