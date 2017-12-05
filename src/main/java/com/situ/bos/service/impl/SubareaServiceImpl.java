package com.situ.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(detachedCriteria);
	}

	@Override
	public List<Subarea> findListByDecidezoneId(String decidezoneId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		//添加过滤条件
		dc.add(Restrictions.eq("decidedzone.id", decidezoneId));
		return subareaDao.findByCriteria(dc);
	}

}
