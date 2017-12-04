package com.situ.bos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Decidedzone;
import com.situ.bos.service.IDecidedzoneService;
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

	//属性驱动接收多个分区id
	private String[] subareaid;

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	@Autowired
	private IDecidedzoneService decidedzoneService;

	public String add() {
		
		decidedzoneService.save(model, subareaid);
		return LIST;
	}
	
	public String pageQuery() {
		
		decidedzoneService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"currentPage","detachedCriteria","currentSize","pageSize","subareas", "decidedzones"});
		return NONE;
	}
}
