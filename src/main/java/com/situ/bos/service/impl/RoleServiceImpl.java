package com.situ.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.bos.dao.IRoleDao;
import com.situ.bos.pojo.Function;
import com.situ.bos.pojo.Role;
import com.situ.bos.service.IRoleService;
import com.situ.bos.vo.PageBean;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	@Override
	public void save(Role model, String functionIds) {

		roleDao.save(model);
		if (StringUtils.isNotBlank(functionIds)) {
			String[] fids = functionIds.split(",");
			for (String functionId : fids) {
				Function function = new Function(functionId);
				//角色关联权限
				model.getFunctions().add(function);
			}
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}
	@Override
	public List<Role> findAll() {
		List<Role> list = roleDao.findAll();
		return list;
	}

	
}
