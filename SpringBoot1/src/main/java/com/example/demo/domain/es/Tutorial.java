package com.example.demo.domain.es;

import java.io.Serializable;

public class Tutorial implements Serializable{
	private Long id;
	private String name;//教程名称
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
	
	//setters and getters
	//toString
	
	
}
