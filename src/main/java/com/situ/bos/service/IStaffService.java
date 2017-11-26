package com.situ.bos.service;

import com.situ.bos.pojo.Staff;
import com.situ.bos.vo.PageBean;

public interface IStaffService {

	/**
	 * 添加派送员
	 * @param model
	 */
	void add(Staff model);

	/**
	 * 对去取派员进行分页
	 * @param pageBean
	 */
	void pageQuery(PageBean pageBean);

	/**
	 * 删除取派员
	 * @param ids
	 */
	void deleteBatch(String ids);

	/**
	 * 根据id查找取派员
	 * @param id
	 * @return Staff
	 */
	Staff findById(String id);

	/**
	 * 修改取派员
	 * @param staff
	 */
	void update(Staff staff);

}
