package com.example.demo.domain.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/**
 * 定义用户
 * @author tianxuxu
 *
 */

@Entity
public class SSysUser implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	
	
	
	
	
	 @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
	 private List<SSysRole> roles;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SSysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SSysRole> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 我们还要配置用户和角色之间的多对多关系，正常情况下，角色和权限是两回事，所以我们还需要重写getAuthorities方法，将用户的角色和权限关联起来
	 */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> auths = new ArrayList<>();
        List<SSysRole> roles = this.getRoles();
        for (SSysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
