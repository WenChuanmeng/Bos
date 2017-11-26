package com.situ.bos.dao;

import java.util.List;

import com.situ.bos.dao.base.IBaseDao;
import com.situ.bos.pojo.Region;

public interface IRegionDao extends IBaseDao<Region> {

	/**
	 * 根据输入的条件查找
	 * @param q
	 * @return
	 */
	List<Region> findListByQ(String q);

}
