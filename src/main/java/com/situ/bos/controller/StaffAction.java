package com.situ.bos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Staff;
import com.situ.bos.service.IStaffService;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	@Autowired
	private IStaffService staffService;
	
	public String add() {
		staffService.add(model);
		return LIST;
	}
	
	public String pageQuery() {
		
		staffService.pageQuery(pageBean);
		object2jsonByEasyUI(pageBean);
		return NONE;
	}
	
	/**
	 * 删除取派员
	 */
	public String  deleteBatch() {
		staffService.deleteBatch(ids);
		return LIST;
	}
}
