package com.situ.bos.dao;

import com.situ.bos.dao.base.IBaseDao;
import com.situ.bos.pojo.User;

public interface IUserDao extends IBaseDao<User> {

	/**
	 * 根据用户名和密码查询用户
	 * @param model
	 * @return TUser
	 */
	User findByUsernameAndPassword(User model);

}
