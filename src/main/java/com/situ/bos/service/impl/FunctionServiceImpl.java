package com.situ.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.IFunctionDao;
import com.situ.bos.pojo.Function;
import com.situ.bos.pojo.User;
import com.situ.bos.service.IFunctionService;
import com.situ.bos.util.BosUtils;
import com.situ.bos.vo.PageBean;
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

	@Autowired
	private IFunctionDao functionDao;
	@Override
	public List<Function> findAll() {
		
		return functionDao.findAll();
	}
	@Override
	public void save(Function model) {
		Function function = model.getParentFunction();
		if (function != null && function.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
	@Override
	public List<Function> findMenu() {
		List<Function> list = null;
		User user = BosUtils.getLoginUser();
		if (user.getUsername().equals("admin")) {
			list = functionDao.findMenu();
		} else {
			list = functionDao.findMenuByUserId(user.getId());
		}
		return list;
	}

}
