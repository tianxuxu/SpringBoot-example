package com.example.demo.controller.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping("/toupload")
	public String toUpload() {
		return "upload";
	}

	@RequestMapping("/upload")
	public String upload(MultipartFile file, ModelMap map, HttpServletRequest request) {
		if (file.isEmpty()) {
			map.put("uploadResult", "文件不能为空");
			return "upload";
		}
		File filePath = new File("d:/myweb/upload");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File realPath = new File(filePath + "/" + file.getOriginalFilename());
		try {
			/*
			 * 
			 * springboot内置tomcat 如果用 file.transferTo(dest);会牵扯文件路径找不到问题
			 */
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(realPath));
			map.put("uploadResult", "文件上传成功");
		} catch (IllegalStateException | IOException e) {

			e.printStackTrace();
		}

		return "upload";
	}
}
