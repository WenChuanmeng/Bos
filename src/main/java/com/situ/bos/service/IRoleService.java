package com.situ.bos.service;

import java.util.List;

import com.situ.bos.pojo.Role;
import com.situ.bos.vo.PageBean;

public interface IRoleService {

	void save(Role model, String functionIds);

	void pageQuery(PageBean pageBean);

	List<Role> findAll();

}
