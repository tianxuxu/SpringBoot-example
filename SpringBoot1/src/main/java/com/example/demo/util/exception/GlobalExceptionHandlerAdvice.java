package com.example.demo.util.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 * 
 * @author tianxuxu
 *
 */
@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

	public static final String DEMO_ERROR_VIEW = "/thymeleaf/error";

	@ExceptionHandler(value=Exception.class)//这是针对于所有的exception处理  其实可以细化
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {

		e.printStackTrace();

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", reqest.getRequestURL());
		mav.addObject("errMsg",e.getMessage());
		mav.addObject("stackTrace",e.getStackTrace());
		mav.setViewName(DEMO_ERROR_VIEW);
		return mav;
	}

}
