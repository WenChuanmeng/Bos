package com.situ.bos.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.situ.bos.common.ServerResponse;
import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Customer;
import com.situ.bos.pojo.User;
import com.situ.bos.service.ICustomerService;
import com.situ.bos.service.IUserService;
import com.situ.bos.util.BosUtils;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	@Autowired
	private IUserService userService;
	//验证码‘
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String login() {
		String checkCodeTemp = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkCode) && checkCode.equalsIgnoreCase(checkCodeTemp)) {
			//验证码正确
			//使用shiro框架进行认证
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
			try {
				subject.login(token);
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return LOGIN;
			}
			return HOME;
		} else {
			//验证码错误
			this.addActionError("验证码错误");
			return LOGIN;
		}
	}
	
	public String loginOld() {
		String checkCodeTemp = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (StringUtils.isNotBlank(checkCode) && checkCode.equalsIgnoreCase(checkCodeTemp)) {
			//验证码正确
			User user = userService.login(model);
			if (user != null) {
				//用户名和密码正确
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return HOME;
			} else {
				//用户名或密码错误
				this.addActionError("用户名或密码错误");
				return LOGIN;
			}
		} else {
			//验证码错误
			this.addActionError("验证码错误");
			return LOGIN;
		}
	}
	
	
	public String loginOut() {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	public String editPassword() {
		Integer result = 0;
		User user = BosUtils.getLoginUser();
		
		try {
			userService.editPassword(user.getId(), model.getPassword());
		} catch (Exception e) {
			result = 1;
		}
		
		if (result == 0) {
			object2json(new ServerResponse().createSUCCESS("修改成功"));
		} else {
			object2json(new ServerResponse().createERROR("修改失败"));

		}
		return NONE;
	}
	
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}


	public String add() {
		userService.add(model, roleIds);
		return LIST;
	}
	
	public String pageQuery() {
		
		userService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"currentPage","deCriteria","currentSize", "noticebills", "roles"});
		return NONE;
	}
}
