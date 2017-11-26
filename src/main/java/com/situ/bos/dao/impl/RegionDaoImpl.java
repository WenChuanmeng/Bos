package com.situ.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.bos.dao.IRegionDao;
import com.situ.bos.dao.base.impl.BaseDaoImpl;
import com.situ.bos.pojo.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ? OR r.citycode LIKE ? OR r.province LIKE ? OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q +"%", "%" + q +"%", "%" + q +"%", "%" + q +"%", "%" + q +"%");
		return list;
	}

}
