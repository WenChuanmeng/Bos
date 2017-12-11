package com.situ.bos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Role;
import com.situ.bos.service.IRoleService;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	@Autowired
	private IRoleService roleService;
	
	//属性驱动，接收权限的id
	private String functionIds;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	public String add() {
		
		roleService.save(model, functionIds);
		return LIST;
	}
	
	public String pageQuery() {
		
		roleService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"functions", "users"});
		return NONE;
	}
	
	public String listajax() {
		
		List<Role> list = roleService.findAll();
		object2jsonList(list, new String[]{"functions", "users"});
		return NONE;
	}
}
