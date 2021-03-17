package com.cjhme.system.impl.base.mybatis.pagination.dialect;

/**
 * 
 * <p>
 * Title: MySQLDialect.java
 * </p>
 * Description: mysql方言实现
 * <p>
 * Modify histoty:
 * 
 * @author 
 * @version 1.0
 */
public class MySQLDialect extends Dialect {

	/**
	 * 数据库本身是否支持分页查询
	 * 
	 * @return {@code true} 支持分页查询
	 */
	public boolean supportsLimit() {
		return true;
	}

	/**
	 * 将sql包装成数据库支持的特有查询语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param offset
	 *            开始位置
	 * @param limit
	 *            每页显示的记录数
	 * @return 数据库专属分页查询sql
	 */
	public String getLimitString(final String sql, final int offset, final int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
	}

	/**
	 * 将sql包装成数据库支持的特有查询语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param offset
	 *            开始位置
	 * @param limit
	 *            每页显示的记录数
	 * @return 数据库专属分页查询sql
	 */
	private String getLimitString(final String sql, final int offset, final String offsetPlaceholder, final String limitPlaceholder) {
		StringBuilder stringBuilder = new StringBuilder(getLineSql(sql));
		stringBuilder.append(" limit ");
		if (offset > 0) {
			stringBuilder.append(offsetPlaceholder).append(",").append(limitPlaceholder);
		} else {
			stringBuilder.append(limitPlaceholder);
		}

		return stringBuilder.toString();
	}
}
