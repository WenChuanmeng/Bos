package com.situ.bos.service;

import com.situ.bos.pojo.Decidedzone;
import com.situ.bos.vo.PageBean;

public interface IDecidedzoneService {

	/**
	 * 保存定区
	 * @param model
	 * @param subareaid
	 */
	void save(Decidedzone model, String[] subareaid);

	/**
	 * 分页
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

}
