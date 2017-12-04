package com.situ.bos.controller;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Region;
import com.situ.bos.pojo.Subarea;
import com.situ.bos.service.ISubareaService;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

	@Autowired
	private ISubareaService subareaService;
	
	public String pageQuery() {
		
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		if (region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)) {
				//添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if (StringUtils.isNotBlank(city)) {
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if (StringUtils.isNotBlank(district)) {
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		
		subareaService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize",
				"decidedzone","subareas"});
		return NONE;
	}
	
	public String add() {
		subareaService.add(model);
		return LIST;
	}
}
