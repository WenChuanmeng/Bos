package com.situ.bos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Customer;
import com.situ.bos.pojo.Decidedzone;
import com.situ.bos.service.ICustomerService;
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

	@Autowired
	private ICustomerService customerService;
	public String add() {
		
		decidedzoneService.save(model, subareaid);
		return LIST;
	}
	
	public String pageQuery() {
		
		decidedzoneService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"currentPage","detachedCriteria","currentSize","pageSize","subareas", "decidedzones"});
		return NONE;
	}
	
	public String findListNotAssociation() {
		
		List<Customer> list = customerService.findListNotAssociation();
		object2jsonList(list);
		return NONE;
	}
	
	public String findListHasAssociation() {
		
		List<Customer> list = customerService.findListHasAssociation(model.getId());
		object2jsonList(list);
		return NONE;
	}
	
	private List<Integer> customerIds ;
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	
	public String assigncustomerstodecidedzone() {
		customerService.assigncustomerstodecidedzone(model.getId(), customerIds);
		return NONE;
	}

	
	
	
}
