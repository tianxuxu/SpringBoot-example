package com.example.demo.service.es.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.es.EsBlog;
//import com.example.demo.repository.es.BlogRepository;
import com.example.demo.service.es.EsBlogService;


@Service
public class EsBlogServiceImpl implements EsBlogService {

	//@Autowired 
    //private BlogRepository blogRepository;
	
	private Integer id=new Integer(1);
	
	@Override
	public void save(EsBlog esBlog) {
		// TODO Auto-generated method stub

		EsBlog blog1=new EsBlog();
		blog1.setId(new Double(Math.random()).longValue());
		blog1.setName(UUID.randomUUID().toString());
		
		//blogRepository.save(blog1);
		
	}

}
