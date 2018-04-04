package com.example.demo.domain.es;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName ="blog",type="blog" )
public class EsBlog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EsBlog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
