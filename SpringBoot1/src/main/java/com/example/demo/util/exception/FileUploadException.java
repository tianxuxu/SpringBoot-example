package com.example.demo.util.exception;

import org.springframework.boot.autoconfigure.web.MultipartProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice 它通常用于定义{@link ExceptionHandler @ExceptionHandler}、
 *                   {@link InitBinder @InitBinder}和{@link ModelAttribute @ModelAttribute}
 * 
 *                   方法适用于所有{@link RequestMapping @RequestMapping}方法。
 * 
 * @author niugang 文件上传异常处理类
 *
 */

@ControllerAdvice
/**
 * 从配置文件根据前缀读取设置 处理需要字段外还需要，set方法，否则值注入不进来
 * 
 * @author niugang
 *
 */
@ConfigurationProperties(prefix = "spring.http.multipart")
public class FileUploadException {
	private String maxFileSize;

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(MultipartException.class)
	public ModelAndView handleError1(MultipartException e) {

		ModelAndView modelAndView = new ModelAndView("upload");
		// 如果application.properties里面没有设置，则读取系统默认的文件大小
		if (this.getMaxFileSize() == null) {
			MultipartProperties multipartProperties = new MultipartProperties();
			modelAndView.addObject("uploadResult", "最大上传为：" + multipartProperties.getMaxFileSize());
		} else {
			modelAndView.addObject("uploadResult", "最大上传为：" + this.getMaxFileSize());
		}

		return modelAndView;
	}
}
