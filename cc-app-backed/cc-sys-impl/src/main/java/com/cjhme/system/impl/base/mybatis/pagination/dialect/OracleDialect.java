package com.cjhme.system.impl.base.mybatis.pagination.dialect;

/**
 * 
 * <p>
 * Title: MySQLDialect.java
 * </p>
 * Description: oracle方言实现
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public class OracleDialect extends Dialect {

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
	public String getLimitString(String sql, int offset, int limit) {
		return getLimiString(sql, offset, Integer.toString(offset), Integer.toString(limit));
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
	private String getLimiString(final String sql, final int offset, final String offsetPlaceholder, final String limitPlaceholder) {

		String _sql = getLineSql(sql);
		StringBuilder pagingSelect = new StringBuilder(_sql.length() + 100);
		if (offset >= 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(_sql);
		if (offset >= 0) {
			String endString = offsetPlaceholder + "+" + limitPlaceholder;
			pagingSelect.append(" ) row_ ) where rownum_ <= ").append(endString).append(" and rownum_ > ").append(offsetPlaceholder);
		} else {
			pagingSelect.append(" ) where rownum <= ").append(limitPlaceholder);
		}

		return pagingSelect.toString();
	}
}
