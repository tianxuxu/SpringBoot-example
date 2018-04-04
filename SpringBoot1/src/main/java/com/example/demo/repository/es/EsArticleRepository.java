package com.example.demo.repository.es;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.domain.es.EsArticle;


/**
 * CURD和分页排序查询
CurdRepository提供增删改查save、delete、findAll、findOne
PagingAndSortingRepository提供分页和排序
查询标题方法
分页条件查询，只需要在查询方法中，添加Pageable对象
排序条件查询，只需要在查询方法中，添加Sort对象
 * @author tianxuxu
 *
 */
public interface EsArticleRepository extends ElasticsearchRepository<EsArticle, Long>{

	//自己额外定义的两个方法不用去实现
	List<EsArticle> findByTitle(String title);

    Page<EsArticle> findByTitle(String title, Pageable pageable);
}
