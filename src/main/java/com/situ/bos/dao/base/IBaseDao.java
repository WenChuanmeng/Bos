package com.situ.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.situ.bos.vo.PageBean;


public interface IBaseDao<T> {

	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public void saveOrUpdate(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public void pageQuery(PageBean pageBean);
	public void excuteUpdate(String queryName, Object... objects);
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
