package com.situ.bos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Region;
import com.situ.bos.pojo.Subarea;
import com.situ.bos.service.ISubareaService;
import com.situ.bos.util.FileUtils;

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
	
	public String exportXls() {
		
		//第一步  获取list集合
		List<Subarea> list = subareaService.findAll();
		
		//第二步  将list集合写入xls文件
		//创建Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//穿件工作簿
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("分区编号");
		row.createCell(1).setCellValue("开始编号");
		row.createCell(2).setCellValue("结束编号");
		row.createCell(3).setCellValue("位置信息");
		row.createCell(4).setCellValue("省市区（县）");
		
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		
		//第三步   使用输出流进行文件下载
		String fileName = "分区数据.xls";
		String type = ServletActionContext.getServletContext().getMimeType(fileName);
		ServletOutputStream outputStream = null;
		try {
			outputStream = ServletActionContext.getResponse().getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType(type);
		
		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		try {
			fileName = FileUtils.encodeDownloadFilename(fileName, agent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 查询所有未关联到定去的分区
	 */
	public String listajax() {
		
		List<Subarea> list = subareaService.findListNotAssociation();
		object2jsonList(list, new String[]{"decidedzone", "region"});
		return NONE;
	}
}
