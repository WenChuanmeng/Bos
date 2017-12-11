package com.situ.bos.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.situ.bos.dao.IFunctionDao;
import com.situ.bos.dao.IUserDao;
import com.situ.bos.pojo.Function;
import com.situ.bos.pojo.User;

public class BOSRealm extends AuthorizingRealm {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IFunctionDao functionDao;
	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("BOSRealm.doGetAuthenticationInfo()");
		UsernamePasswordToken mytoken = (UsernamePasswordToken) token;
		String username = mytoken.getUsername();
		User user = userDao.findByUsername(username);
		
		if (user == null) {
			//用户名不存在
			return null;
		}
		
		//r如果查询到，再由框架对比数据库中查询到的密码和页面提交的密码是否一致
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}
	

	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录用户的对象
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		//根据当前登录用户查询数据库，获取实际对应的权限
		List<Function> list = null;
		if (user.getUsername().equals("admin")) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
			//内置管理员获取查询所有的权限
			list = functionDao.findByCriteria(detachedCriteria);
		} else {
			list = functionDao.findFunctionListByUserId(user.getId());
		}
		
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}

}
