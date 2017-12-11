package com.situ.bos.dao;

import java.util.List;

import com.situ.bos.dao.base.IBaseDao;
import com.situ.bos.pojo.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	/**
	 * 根据用户的id查找权限
	 * @param id
	 * @return
	 */
	List<Function> findFunctionListByUserId(String id);

	/**
	 * 查找权限
	 * @return
	 */
	List<Function> findMenu();

	/**
	 * 根据用户的id查找权限
	 * @param id
	 * @return
	 */
	List<Function> findMenuByUserId(String id);

}
