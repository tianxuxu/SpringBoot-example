package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.example.demo.mapper")
// 扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
public class SpringBoot1Application {// extends SpringBootServletInitializer{

	/**
	 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
	 */
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) {
	 * builder.sources(this.getClass()); return super.configure(builder); }
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot1Application.class, args);
	}
}
