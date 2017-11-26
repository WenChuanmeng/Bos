package com.situ.bos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Subarea;
import com.situ.bos.service.ISubareaService;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

	@Autowired
	private ISubareaService subareaService;
	
	public String pageQuery() {
		subareaService.pageQuery(pageBean);
		return NONE;
	}
	
	public String add() {
		subareaService.add(model);
		return LIST;
	}
}
