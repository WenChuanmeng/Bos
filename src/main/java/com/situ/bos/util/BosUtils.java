package com.situ.bos.util;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.situ.bos.pojo.User;

public class BosUtils {

	//获取session对象
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	//获取当前登录用户的
	public static User getLoginUser() {
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}
