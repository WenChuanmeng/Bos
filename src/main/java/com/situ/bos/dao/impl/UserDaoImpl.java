package com.situ.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.bos.dao.IUserDao;
import com.situ.bos.dao.base.impl.BaseDaoImpl;
import com.situ.bos.pojo.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findByUsernameAndPassword(User model) {
		String hql = "From User u WHERE u.username=? AND u.password=?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, model.getUsername(), model.getPassword());
		if (list != null && list.size() >0) {
			System.out.println(list.get(0));
			return list.get(0);
		}
		return null;
	}

}
