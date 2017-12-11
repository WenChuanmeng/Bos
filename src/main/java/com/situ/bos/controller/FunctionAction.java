package com.situ.bos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Function;
import com.situ.bos.service.IFunctionService;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	@Autowired
	private IFunctionService functionService;
	
	public String listajax() {
		
		List<Function> list = functionService.findAll();
		object2jsonList(list, new String[]{"parentFunction","roles"});
		return NONE;
	}
	
	public String add() {
		
		functionService.save(model);
		return LIST;
	}
	
	public String pageQuery() {
		
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	public String findMenu() {
		
		List<Function> list = functionService.findMenu();
		object2jsonList(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
}
