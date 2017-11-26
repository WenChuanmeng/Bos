package com.situ.bos.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.situ.bos.pojo.User;
import com.situ.bos.util.BosUtils;

public class BosLoginIntercepter extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		User user = BosUtils.getLoginUser();
		if (user == null) {
			//没有登录，跳转到登录页面
			return "login";
		}
		return invocation.invoke();
	}

}
