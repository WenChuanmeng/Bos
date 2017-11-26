package com.situ.bos.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.situ.bos.controller.base.BaseAction;
import com.situ.bos.pojo.Region;
import com.situ.bos.pojo.Subarea;
import com.situ.bos.service.IRegionService;
import com.situ.bos.util.PinYin4jUtils;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private File regionFile;
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	
	@Autowired
	private IRegionService regionService;

	public String pageQuery() {
		
		regionService.pageQuery(pageBean);
		object2json(pageBean, new String[]{"currentPage","deCriteria","currentSize","subareas"});
		return NONE;
	}
	
	public String impXls() {
		List<Region> reList = new ArrayList<Region>();
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			if (rowNum == 0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			
			String[] provinceStr = PinYin4jUtils.getHeadByString(province);
			String[] cityStr = PinYin4jUtils.getHeadByString(city);
			String[] districtStr = PinYin4jUtils.getHeadByString(district);
			String proStr = StringUtils.join(provinceStr);
			String cStr = StringUtils.join(cityStr);
			String disStr = StringUtils.join(districtStr);
			String shortcode = proStr + cStr + disStr;
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			reList.add(region);
		}
		regionService.saveBatch(reList);
		return NONE;
	}
	
	public String add() {
		
		String province = model.getProvince();
		String city = model.getCity();
		String district = model.getDistrict();
		String[] provinceStr = PinYin4jUtils.getHeadByString(province);
		String[] cityStr = PinYin4jUtils.getHeadByString(city);
		String[] districtStr = PinYin4jUtils.getHeadByString(district);
		String proStr = StringUtils.join(provinceStr);
		String cStr = StringUtils.join(cityStr);
		String disStr = StringUtils.join(districtStr);
		String shortcode = proStr + cStr + disStr;
		String citycode = PinYin4jUtils.hanziToPinyin(city, "");
		model.setShortcode(shortcode);
		model.setCitycode(citycode);
		regionService.add(model);
		return NONE;
	}
	
	public String listAjax() {
		
		List<Region> list = null;
		if (StringUtils.isNotBlank(q)) {
			list = regionService.findListByQ(q);
		} else {
			list = regionService.findAll();
		}
		object2jsonList(list, new String[]{"subareas"});
		return NONE;
	}
}
