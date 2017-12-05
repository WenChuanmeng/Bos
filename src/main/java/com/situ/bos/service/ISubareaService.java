package com.situ.bos.service;

import java.util.List;

import com.situ.bos.pojo.Subarea;
import com.situ.bos.vo.PageBean;

public interface ISubareaService {

	/**
	 * 分页查询
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 添加分区
	 * @param model
	 */
	void add(Subarea model);

	/**
	 * 查找所有分区的信息
	 * @return List<Subarea>
	 */
	List<Subarea> findAll();

	/**
	 * 查询所有未关联到定去的分区
	 * @return List<Subarea>
	 */
	List<Subarea> findListNotAssociation();

	List<Subarea> findListByDecidezoneId(String decidezoneId);

}
