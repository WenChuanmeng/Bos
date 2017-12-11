package com.situ.bos.service;

import com.situ.bos.pojo.User;
import com.situ.bos.vo.PageBean;

public interface IUserService {

	/**
	 * 根据用户名和密码查询用户
	 * @param model
	 * @return TUser
	 */
	User login(User model);

	/**
	 * 修改密码
	 * @param id
	 * @param password
	 */
	void editPassword(String id, String password);

	/**
	 * 添加用户
	 * @param model
	 * @param roleIds
	 */
	void add(User model, String[] roleIds);

	/**
	 * 分页
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

}
