package com.situ.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.IRegionDao;
import com.situ.bos.pojo.Region;
import com.situ.bos.service.IRegionService;
import com.situ.bos.vo.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;
	
	@Override
	public void saveBatch(List<Region> reList) {
		if (reList != null && reList.size() > 0) {
			for (Region region : reList) {
				regionDao.saveOrUpdate(region);
			}
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}
}
