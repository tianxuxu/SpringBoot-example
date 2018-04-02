package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@RestController
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.example.demo.mapper")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
public class SpringBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot1Application.class, args);
	}
	
	
	@GetMapping("/hi")
	public String hello() {
		return "HelloWorld2!!!";
	}
}
