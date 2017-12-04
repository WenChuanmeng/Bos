package com.situ.bos.controller;

import java.util.List;

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
	
	/**
	 * 修改取派员信息
	 */
	public String update() {
		
		Staff staff = staffService.findById(model.getId());
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staffService.update(staff);
		return LIST;
	}
	
	/**
	 * 查找派送员
	 */
	public String listajax() {
		
		List<Staff> list = staffService.findListNotDelete();
		object2jsonList(list, new String[]{"decidedzones"});
		return NONE;
	}
}
