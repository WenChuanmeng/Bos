package com.situ.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.IUserDao;
import com.situ.bos.pojo.Role;
import com.situ.bos.pojo.User;
import com.situ.bos.service.IUserService;
import com.situ.bos.vo.PageBean;

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

	@Override
	public void add(User model, String[] roleIds) {
		userDao.save(model);
		if (roleIds != null && roleIds.length > 0) {
			for (String roleId : roleIds) {
				Role role = new Role(roleId);
				model.getRoles().add(role);
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

}
