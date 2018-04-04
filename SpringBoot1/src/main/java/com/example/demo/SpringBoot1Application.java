package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.example.demo.mapper")
// 扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
// 开启定时任务
@EnableScheduling
public class SpringBoot1Application extends SpringBootServletInitializer {

	/**
	 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
	 */
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) {
	 * builder.sources(this.getClass()); return super.configure(builder); }
	 */
	public static void main(String[] args) {
		//// app.setBannerMode(Banner.Mode.OFF);
		/*
		 * 
		 * Banner.Mode.OFF:关闭;
		 * 
		 * Banner.Mode.CONSOLE:控制台输出，默认方式;
		 * 
		 * Banner.Mode.LOG:日志输出方式;
		 * 
		 */
		SpringApplication.run(SpringBoot1Application.class, args);
	}

	// 从http到https的自动跳转

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		// Connector监听的http的端口号
		connector.setPort(8080);
		connector.setSecure(false);
		// 监听到http的端口号后转向到的https的端口号
		connector.setRedirectPort(9001);
		return connector;
	}

	/*
	 * //Tomcat large file upload connection reset
	 * 
	 * @Bean//自定义 容器 不过内嵌容器 容器对Jsp支持有一些限制 TomcatJetty只支持war包 Undertow不支持JSPS
	 * 创建自定义的error.jsp不会覆盖默认的error handing视图 public
	 * TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
	 * TomcatEmbeddedServletContainerFactory tomcat = new
	 * TomcatEmbeddedServletContainerFactory(); tomcat.addConnectorCustomizers(new
	 * MyTomcatConnectorCustomizer()); return tomcat; }
	 * //tomcatEmbedded这段代码是为了解决上传文件大于10M出现连接重置的问题。此异常内容GlobalException也捕获不到
	 * 编程方式配置内嵌的servelet容器 能够获取到很多的自定义配置内容 通过set方法进行配置 class
	 * MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer{
	 * 
	 * @SuppressWarnings("rawtypes")
	 * 
	 * @Override public void customize(Connector connector) { AbstractHttp11Protocol
	 * protocolHandler = (AbstractHttp11Protocol)connector.getProtocolHandler();
	 * protocolHandler.setMaxSwallowSize(-1);
	 * 
	 * }
	 * 
	 * }
	 */
}
