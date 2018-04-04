package com.example.demo.controller.es;

import java.util.Date;
import java.util.Iterator;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.es.Author;
import com.example.demo.domain.es.EsArticle;
import com.example.demo.domain.es.Tutorial;
import com.example.demo.repository.es.EsArticleRepository;
/**
 * 其实非常类似于普通的DB查询，还支持很多条件查询，findAll，findTop之类的，就是JPA那一套可以直接用，因为继承的ElasticsearchRepository本身就是一个PagingAndSortingRepository。 
 * @author tianxuxu
 *
 */
@RestController
@RequestMapping("/es")
public class EsArticleController {
    @Autowired
    private EsArticleRepository articleSearchRepository;

    @RequestMapping("/add")
    public void testSaveArticleIndex() {
        Author author = new Author();
        author.setId(1L);
        author.setName("tianshouzhi");
        author.setRemark("java developer");

        Tutorial tutorial = new Tutorial();
        tutorial.setId(1L);
        tutorial.setName("elastic search");

        EsArticle article = new EsArticle();
        article.setId(1L);
        article.setTitle("springboot integreate elasticsearch");
        article.setAbstracts("springboot integreate elasticsearch is very easy");
        article.setTutorial(tutorial);
        article.setAuthor(author);
        article.setContent("elasticsearch based on lucene,"
                + "spring-data-elastichsearch based on elaticsearch"
                + ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
        article.setPostTime(new Date());
        article.setClickCount(1L);

        
        articleSearchRepository.save(article);
    }

    @RequestMapping("/query")
    public void testSearch() {
        String queryString = "springboot";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<EsArticle> searchResult = articleSearchRepository.search(builder);
        Iterator<EsArticle> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
