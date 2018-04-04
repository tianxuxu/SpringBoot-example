package com.example.demo.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.security.SSysUser;

public interface SSysUserRepository extends JpaRepository<SSysUser, Long> {
	// 需要注意的是这里只需要一个根据用户名查询出用户的方法即可，不需要通过用户名和密码去查询
	SSysUser findByUsername(String username);
}
