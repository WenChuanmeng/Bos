package com.situ.bos.vo;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {

	private Integer currentPage;
	private Integer pageSize;
	private DetachedCriteria detachedCriteria;
	private Integer total;
	private List<?> rows;
	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(Integer currentPage, Integer pageSize, DetachedCriteria detachedCriteria, Integer total,
			List<?> rows) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.detachedCriteria = detachedCriteria;
		this.total = total;
		this.rows = rows;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the detachedCriteria
	 */
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	/**
	 * @param detachedCriteria the detachedCriteria to set
	 */
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public List<?> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", detachedCriteria="
				+ detachedCriteria + ", total=" + total + ", rows=" + rows + "]";
	}
	
	
}
