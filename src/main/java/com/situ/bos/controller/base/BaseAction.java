package com.situ.bos.controller.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.situ.bos.vo.PageBean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected static final String HOME = "home";
	protected static final String LIST = "list";
	
	protected PageBean pageBean = new PageBean();
	
	protected  T model;
	//创建离线查询对象
	protected DetachedCriteria detachedCriteria = null;
	
	@Override
	public T getModel() {
		return model;
	}

	public BaseAction() {
		// this:当前运行的类(AdminDao/StudentDao)
		// this.getClass:当前运行类的字节码:AdminDao.class/StudentDao.class
		// this.getClass().getGenericSuperclass():当前运行类的父类即为：BaseDaoImpl<Admin>
		Type type = this.getClass().getGenericSuperclass();
		// 强制转化为参数化类型BaseDaoImpl<Admin.clsss>
		ParameterizedType superClass = (ParameterizedType) type;
		// BaseDaoImpl<Admin,Student>参数可以有多个
		Type[] types = superClass.getActualTypeArguments();
		//获取第一个元素
		Class<T> entryClass = (Class<T>) types[0];
		detachedCriteria = DetachedCriteria.forClass(entryClass);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			model = entryClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将任意对象转成json
	 * @param object
	 */
	public void object2json(Object object, String... excludes) {
		JsonConfig jsonConfig = new JsonConfig();
		//指定那些属性不进行json转换
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(object, jsonConfig).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void object2jsonByEasyUI(Object object) {
		object2json(object, new String[]{"currentPage","deCriteria","currentSize"});
	}
	
	
	private Integer page;//第几页
	private Integer rows;//每页有多少数据

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
		pageBean.setCurrentPage(page);
	}

	/**
	 * @return the rows
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
		pageBean.setPageSize(rows);
	}
	
	

}
