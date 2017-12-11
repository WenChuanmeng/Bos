package com.situ.bos.service;

import java.util.List;

import com.situ.bos.pojo.Function;
import com.situ.bos.vo.PageBean;

public interface IFunctionService {

	List<Function> findAll();

	void save(Function model);

	void pageQuery(PageBean pageBean);

	/**
	 * 查找权限
	 * @return List<Function>
	 */
	List<Function> findMenu();

}
