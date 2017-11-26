package com.situ.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.IUserDao;
import com.situ.bos.pojo.User;
import com.situ.bos.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User login(User model) {
		
		return userDao.findByUsernameAndPassword(model);
	}

	@Override
	public void editPassword(String id, String password) {
		userDao.excuteUpdate("user.editPassword", password, id);
	}

}
