package com.situ.bos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Customer;
import com.situ.bos.pojo.Noticebill;
import com.situ.bos.service.ICustomerService;
import com.situ.bos.service.INoticebillService;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	@Autowired
	private INoticebillService noticebillService;
	
	@Autowired
	private ICustomerService customerService;
	
	public String findCustomerByTelephone() {
		Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
		object2json(customer);
		return NONE;
	}
	
	public String add() {
		
		noticebillService.save(model);
		return "noticebill_add";
	}
}
