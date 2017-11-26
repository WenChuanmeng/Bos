package com.situ.bos.service;

import java.util.List;

import com.situ.bos.pojo.Region;
import com.situ.bos.vo.PageBean;

public interface IRegionService {

	/**
	 * save区域集合
	 * @param reList
	 */
	void saveBatch(List<Region> reList);

	/**
	 * 分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

}
