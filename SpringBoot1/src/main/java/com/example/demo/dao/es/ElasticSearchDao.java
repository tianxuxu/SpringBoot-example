package com.example.demo.dao.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.domain.es.EsArticle;

@Component
public class ElasticSearchDao {

	//通过ElasticsearchTemplate创建索引和添加映射
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	public void createIndex() {
        elasticsearchTemplate.createIndex(EsArticle.class);
        elasticsearchTemplate.putMapping(EsArticle.class);
    }
}
