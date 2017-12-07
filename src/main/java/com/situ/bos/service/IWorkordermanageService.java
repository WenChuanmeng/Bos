package com.situ.bos.service;

import com.situ.bos.pojo.Workordermanage;
import com.situ.bos.vo.PageBean;

public interface IWorkordermanageService {

	/**
	 * 快速录入工作单
	 * @param model
	 */
	void save(Workordermanage model);

	/**
	 * 分页
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

}
