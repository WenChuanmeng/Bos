package com.situ.bos.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.User;
import com.situ.bos.service.IUserService;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final String HOME = "home";
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
			User user = userService.login(model);
			if (user != null) {
				//用户名和密码正确
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
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
}
