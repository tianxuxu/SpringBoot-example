package com.example.demo.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.domain.security.SSysUser;
import com.example.demo.repository.security.SSysUserRepository;

/**
 * 首先这里我们需要重写UserDetailsService接口，然后实现该接口中的loadUserByUsername方法，通过该方法查询到对应的用户，这里之所以要实现UserDetailsService接口，是因为在Spring Security中我们配置相关参数需要UserDetailsService类型的数据。
 * @author tianxuxu
 *认证鉴权信息的Bean，采用我们自定义的从数据库中获取用户信息的LightSwordUserDetailService类。
 */
public class CustomUserService  implements UserDetailsService  {

	@Autowired
    SSysUserRepository userRepository;
	
	//即从数据库中取出用户名、密码以及权限相关的信息。最后返回一个UserDetails 实现类。
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		 SSysUser user = userRepository.findByUsername(s);
	        if (user == null) {
	            throw new UsernameNotFoundException("用户名不存在");
	        }
	       
	        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
	        return user;
	}	

}
