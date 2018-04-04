package com.example.demo.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.service.security.impl.CustomUserServiceImpl;



/**
 * 配置spring security
 * @author tianxuxu
 *
 */

/**
 * 这里只做了基本的配置，设置了登录url、登录成功后跳转的url、退出后跳转到的url。
 * 使用@EnableGlobalMethodSecurity(prePostEnabled = true)这个注解， 可以开启security的注解，
 * 我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
 * 
 * @author tianxuxu
 *
 */
// SpringSecurity配置

/*
 * 默认的只有一个User用户和随机 密码 1.通过 @EnableWebSecurity注解开启Spring
 * Security的功能。使用@EnableGlobalMethodSecurity(prePostEnabled =
 * true)这个注解，可以开启security的注解，我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
 * 
 * 2.extends 继承 WebSecurityConfigurerAdapter 类，并重写它的方法来设置一些web安全的细节。我们结合@
 * EnableWebSecurity注解和继承WebSecurityConfigurerAdapter，来给我们的系统加上基于web的安全机制。
 * 
 * 3.在configure(HttpSecurity http)方法里面，默认的认证代码是： http .authorizeRequests()
 * .anyRequest().authenticated() .and() .formLogin().and() .httpBasic(); 作者：灵魂剑客
 * 链接：https://www.jianshu.com/p/08cc28921fd0 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * （1）覆盖写userDetailsService方法，具体的LightSwordUserDetailService实现类，我们下面紧接着会讲。
 * 
 * （2）默认不拦截静态资源的url pattern。我们也可以用下面的WebSecurity这个方式跳过静态资源的认证
 * 
 * 提供的常见底层特性被打开HSTS XSS CSRF 缓存
 */
@Configuration
// @Order(SecurityProperties.ACCESS_OVERIED_OERDER)//只覆盖应用访问规则
// @Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)//只覆盖actuator访问规则
// @EnableWebSecurity //会对静态资源进行拦截 只有拥有权限后才会放开 关掉Spring的默认安全配置
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) // 添加方法级别的保护
public class DemoWebSecurtityConfig extends WebSecurityConfigurerAdapter {
	// 可以使用基于表单的验证
	// 熟悉默认配置 ApplicationEventPubilsher
	// 需要重写并覆盖该方法
	@Bean
	UserDetailsService customUserService() {
		return new CustomUserServiceImpl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// manger使用我们自己的service来获取用户信息
		auth.userDetailsService(customUserService());
		// auth.inMemoryAuthentication().withUser("root").password("root").roles("User").and().withUser("admin")
		// .password("admin").roles("ADMIN",
		// "USER").and().withUser("user").password("user").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		// super.configure(http);

		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login?error").permitAll().and().logout().permitAll();

		// http.csrf().disable();
		// http.authorizeRequests().antMatchers("/").permitAll()
		// //.antMatchers("/1","/2").permitAll()//默认不拦截静态资源 和下面方法同理
		// .anyRequest().authenticated().and()
		// .formLogin().loginPage("/login")
		// .defaultSuccessUrl("/httpapi").permitAll().and()
		// .logout().permitAll();
		//
		// http.logout().logoutSuccessUrl("/");//退出默认跳转页面
	}

	// public void configure(WebSecurity web) throws Exception {
	// web
	// .ignoring()
	// .antMatchers("/resources/**");
	// }
}

/*
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解 public class
 * DemoWebSecurtityConfig extends WebSecurityConfigurerAdapter {
 * 
 * @Override protected AuthenticationManager authenticationManager() throws
 * Exception { // TODO Auto-generated method stub return
 * super.authenticationManager(); }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { //
 * TODO Auto-generated method stub //允许所有用户访问"/"和"/home"
 * http.authorizeRequests() .antMatchers("/", "/home").permitAll()
 * //其他地址的访问均需验证权限 .anyRequest().authenticated() .and() .formLogin()
 * //指定登录页是"/login" .loginPage("/login")
 * .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello" .permitAll() .and()
 * .logout() .logoutSuccessUrl("/home")//退出登录后的默认url是"/home" .permitAll(); }
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception{ auth .userDetailsService(customUserDetailsService())
 * .passwordEncoder(passwordEncoder());
 * 
 * }
 * 
 *//**
	 * 设置用户密码的加密方式为MD5加密
	 * 
	 * @return
	 */
/*
 * @Bean public Md5PasswordEncoder passwordEncoder() { return new
 * Md5PasswordEncoder(); }
 * 
 * 
 *//**
	 * 自定义UserDetailsService，从数据库中读取用户信息
	 * 
	 * @return
	 *//*
		 * @Bean public CustomUserDetailService customUserDetailService() { throw new
		 * CustomUserDeatilService(); }
		 * 
		 * }
		 */