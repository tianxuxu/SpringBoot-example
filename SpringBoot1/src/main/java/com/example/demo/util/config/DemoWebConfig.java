package com.example.demo.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 配置spring security
 * @author tianxuxu
 *
 */

/**
 * 这里只做了基本的配置，设置了登录url、登录成功后跳转的url、退出后跳转到的url。 
 * 使用@EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，
 * 可以开启security的注解，
 * 我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。 
 * @author tianxuxu
 *
 */
//SpringMVC配置

@Configuration
public class DemoWebConfig extends WebMvcConfigurerAdapter{

	//当用户访问login时跳转到login.html页面。
	/**
	    * 统一注册纯RequestMapping跳转View的Controller
	    */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		//这里我们直接采用ViewControllerRegistry来注册一个纯路径映射的Controller方法。
		registry.addViewController("/login").setViewName("login");
		// //将所有/static/** 访问都映射到classpath:/static/ 目录下
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
}

/*
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class DemoWebSecurtityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		   //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello"
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/home")//退出登录后的默认url是"/home"
                .permitAll();
	}
	
	 @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		    auth
            .userDetailsService(customUserDetailsService())
            .passwordEncoder(passwordEncoder());

	}

	*//**
     * 设置用户密码的加密方式为MD5加密
     * @return
     *//*
    @Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}
	
	
	*//**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     *//*
    @Bean
	public CustomUserDetailService customUserDetailService() {
		throw new CustomUserDeatilService();
	}
	
}
*/