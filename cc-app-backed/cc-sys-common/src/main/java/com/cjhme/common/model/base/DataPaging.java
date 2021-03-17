package com.cjhme.common.model.base;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * Title: DataPaging.java
 * </p>
 * Description: 分页bean
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public class DataPaging<E> {

	// 记录
	private List<E> rows = null;

	// 总条数
	private int total = 0;

	// 起始位置
	private int offset = 0;

	// 结束位置
	private int limit = 0;

	// 请求参数
	private Map<String, Object> parameter;

	/**
	 * 分页计算
	 * 
	 * @param page
	 * @param pageRows
	 * @return
	 */
	public static DataPaging<Object> pageUtil(String page, String pageRows) {
		// 当前页
		int intPage = Integer.parseInt((page == null || page.equals("0")) ? "1" : page);

		// 每页显示条数
		int rows = Integer.parseInt((pageRows == null || pageRows.equals("0")) ? "10" : pageRows);

		// 计算起始与结束位置
		DataPaging<Object> dataPaging = new DataPaging<Object>();

		dataPaging.setOffset((intPage - 1) * rows);
		dataPaging.setLimit(rows);

		return dataPaging;
	}

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public String toString() {
		return "{offset:" + offset + ",limit:" + limit + ",parameter:" + parameter.toString() + "}";
	}

}
