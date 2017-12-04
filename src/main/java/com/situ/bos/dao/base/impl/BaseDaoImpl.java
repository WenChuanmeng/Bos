package com.situ.bos.dao.base.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.situ.bos.dao.base.IBaseDao;
import com.situ.bos.vo.PageBean;



public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	
	//注入Spring容器中的回话工厂
	//注解可以用在属性也可以在方法，
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public BaseDaoImpl() {
		//this:当前运行的类(AdminDao)
		//this.getClass():当前运行类的字节码(AdminDao.class)
		//this.getClass().getGenericSuperclass()：当前运行类的父类BaseDaoImpl<Admin>
		Type type = this.getClass().getGenericSuperclass();
		//强制转化为参数化类型BaseDaoImpl<Admin.class>
		ParameterizedType superClass = (ParameterizedType) type;
		//BaseDaoImpl<Admin,Student>
		Type[] actualTypeArguments = superClass.getActualTypeArguments();//[Admin.class]
		//获得数组的第一个参数
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String sql = "FROM " + entityClass.getSimpleName();
		return (List<T>) getHibernateTemplate().find(sql);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		DetachedCriteria deCriteria = pageBean.getDetachedCriteria();
		
		//查询总记录数：total
		deCriteria.setProjection(Projections.rowCount());
		List<?> countList = getHibernateTemplate().findByCriteria(deCriteria);
		long total = (long) countList.get(0);
		pageBean.setTotal((int) total);
		
		//rows 当前页数据
		deCriteria.setProjection(null);
		deCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult = (currentPage - 1) * pageSize;
		List<?> rows = getHibernateTemplate().findByCriteria(deCriteria, firstResult, pageSize);
		pageBean.setRows(rows);
		
	}

	@Override
	public void excuteUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i = 0;
		for (Object object : objects) {
			query.setParameter(i++, object);
		}
		//执行更新
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
