package com.situ.bos.service;

import com.situ.bos.pojo.User;

public interface IUserService {

	/**
	 * 根据用户名和密码查询用户
	 * @param model
	 * @return TUser
	 */
	User login(User model);

}
