package com.example.demo.dao.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;

/**
 * 支持jpa 丰富的jpa映射
 * 
 * @author tianxuxu
 *
 */
public class MongoDBDao {

	// 尝试使用mongodb://localhost/test连接到MongoDB服务器

	@Autowired
	private MongoDbFactory mDbFactory;

	@Autowired
	private MongoTemplate mTemplate;

	public void example() {
		DB db = mDbFactory.getDb();
	}

}
