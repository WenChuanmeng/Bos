package com.situ.bos.service;

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

}
