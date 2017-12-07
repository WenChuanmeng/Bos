package com.situ.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.bos.dao.IWorkordermanageDao;
import com.situ.bos.pojo.Workordermanage;
import com.situ.bos.service.IWorkordermanageService;
import com.situ.bos.vo.PageBean;
@Service
public class WorkordermanageServiceImpl implements IWorkordermanageService{

	@Autowired
	private IWorkordermanageDao workordermanagerDao;
	@Override
	public void save(Workordermanage model) {
		workordermanagerDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		workordermanagerDao.pageQuery(pageBean);
	}

}
