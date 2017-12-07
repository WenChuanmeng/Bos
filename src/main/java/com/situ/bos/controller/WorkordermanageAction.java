package com.situ.bos.controller;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.common.ServerResponse;
import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Workordermanage;
import com.situ.bos.service.IWorkordermanageService;
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage>{

	@Autowired
	private IWorkordermanageService workordermanagerService;
	
	public String add() {
		 Integer res = 1;
		try {
			workordermanagerService.save(model);
		} catch (Exception e) {
			res = 0;
		}
		
		if (res == 0) {
			object2json(new ServerResponse().createERROR("添加失败"));
		} 
		return NONE;
	}
	
	public String pageQuery() {
		workordermanagerService.pageQuery(pageBean);
		object2jsonByEasyUI(pageBean);
		return NONE;
	}
}
