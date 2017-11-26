package com.situ.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.ISubareaDao;
import com.situ.bos.pojo.Subarea;
import com.situ.bos.service.ISubareaService;
import com.situ.bos.vo.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Autowired
	private ISubareaDao subareaDao;
	
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public void add(Subarea model) {
		subareaDao.save(model);
	}

}
