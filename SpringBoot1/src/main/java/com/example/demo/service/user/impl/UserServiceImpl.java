package com.example.demo.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.example.demo.mapper.SysUserMapper;
import com.example.demo.pojo.SysUser;
import com.example.demo.service.user.UserService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	/**
	 * 插入一项纪录
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(SysUser user) throws Exception {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sysUserMapper.insert(user);
	}

	@Override
	/**
	 * 更新一项纪录
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(SysUser user) {
		// TODO Auto-generated method stub
		sysUserMapper.updateByPrimaryKey(user);
	}

	@Override
	/**
	 * 删除一项纪录
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		sysUserMapper.deleteByPrimaryKey(userId);
	}

	@Override
	/**
	 * 查询一项纪录
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserById(String userId) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sysUserMapper.selectByPrimaryKey(userId);
	}

	// =================================复杂的sql语句

	/**
	 * 模糊指定条件查询 前后模糊
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserList(SysUser user) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 通用的查询对象
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();

		// 如果用户名为非null
		if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
			// criteria.andEqualTo("username", user.getUsername());
			criteria.andLike("username", "%" + user.getUsername() + "%");
		}
		// 用户昵称为非null
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}

		List<SysUser> userList = sysUserMapper.selectByExample(example);

		return userList;

	}

	/*
	 * 分页查询 指定分页大小 数目
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		// 开始分页
		PageHelper.startPage(page, pageSize);

		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		// 排序
		example.orderBy("registTime").desc();
		List<SysUser> userList = sysUserMapper.selectByExample(example);

		return userList;
	}

	@Override
	// 没有实现定制化
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserByIdCustom(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// 查看异常情况下是否会回滚
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserTransactional(SysUser user) {
		// TODO Auto-generated method stub
		sysUserMapper.insert(user);

		int a = 1 / 0;

		user.setIsDelete(1);
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

}
