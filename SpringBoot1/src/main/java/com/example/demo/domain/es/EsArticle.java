package com.example.demo.domain.es;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Document注解里面的几个属性，类比mysql的话是这样： index –> DB type –> Table Document –> row
 * @Id注解加上后，在Elasticsearch里相应于该列就是主键了，在查询时就可以直接用主键查询，后面一篇会讲到。其实和mysql非常类似，基本就是一个数据库。 @Persistent
 * 
 * 在使用spring data elasticsearch开发，需要将索引和映射信息配置实体类上面
@Document文档对象（索引信息、文档类型）
@Id文档主键 唯一标识
@Field每个文档的字段配置（类型、是否分词、是否存储、分词器）
 * 
 * @Inherited
 * @Retention(RetentionPolicy.RUNTIME) @Target({ElementType.TYPE})
 *                                     public @interface Document {
 * 
 *                                     String indexName();//索引库的名称，个人建议以项目的名称命名
 * 
 *                                     String type() default
 *                                     "";//类型，个人建议以实体的名称命名
 * 
 *                                     short shards() default 5;//默认分区数
 * 
 *                                     short replicas() default 1;//每个分区默认的备份数
 * 
 *                                     String refreshInterval() default
 *                                     "1s";//刷新间隔
 * 
 *                                     String indexStoreType() default
 *                                     "fs";//索引文件存储类型 }
 * 
 * @Persistent
 * @Inherited
 * @Retention(RetentionPolicy.RUNTIME) @Target({ElementType.TYPE})
 *                                     public @interface Document {
 * 
 *                                     String indexName();//索引库的名称，个人建议以项目的名称命名
 * 
 *                                     String type() default
 *                                     "";//类型，个人建议以实体的名称命名
 * 
 *                                     short shards() default 5;//默认分区数
 * 
 *                                     short replicas() default 1;//每个分区默认的备份数
 * 
 *                                     String refreshInterval() default
 *                                     "1s";//刷新间隔
 * 
 *                                     String indexStoreType() default
 *                                     "fs";//索引文件存储类型 }
 * 
 * @Field注解的定义如下：
 * 
 * @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.FIELD) @Documented
 * @Inherited public @interface Field {
 * 
 *            FieldType type() default FieldType.Auto;#自动检测属性的类型
 * 
 *            FieldIndex index() default FieldIndex.analyzed;#默认情况下分词
 * 
 *            DateFormat format() default DateFormat.none;
 * 
 *            String pattern() default "";
 * 
 *            boolean store() default false;#默认情况下不存储原文
 * 
 *            String searchAnalyzer() default "";#指定字段搜索时使用的分词器
 * 
 *            String indexAnalyzer() default "";#指定字段建立索引时指定的分词器
 * 
 *            String[] ignoreFields() default {};#如果某个字段需要被忽略
 * 
 *            boolean includeInParent() default false; }
 * @author tianxuxu
 *因为我们希望Article作为我们文章的搜索入口，所以我们在Article类上添加@Document注解。
 *添加@Document注解会对实体中的所有属性建立索引，
 */

@Document(indexName = "projectname", type = "article", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
public class EsArticle implements Serializable {
	@Id
	private Long id;
	/** 标题 */
	private String title;
	/** 摘要 */
	private String abstracts;
	/** 内容 */
	private String content;
	/** 发表时间 */
	private Date postTime;
	/** 点击率 */
	private Long clickCount;
	/** 作者 */
	private Author author;
	/** 所属教程 */
	private Tutorial tutorial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	@Override
	public String toString() {
		return "Article{" + "id=" + id + ", title='" + title + '\'' + ", abstracts='" + abstracts + '\'' + ", content='"
				+ content + '\'' + ", postTime=" + postTime + ", clickCount=" + clickCount + ", author=" + author
				+ ", tutorial=" + tutorial + '}';
	}
}