package com.cjhme.system.impl.base.mybatis.pagination.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;

import com.cjhme.system.impl.base.mybatis.pagination.dialect.Dialect;
/**
 * 
 * <p>
 * Title: SqlHelper.java
 * </p>
 * Description: sql帮助
 * <p>
 * Modify histoty:
 * 
 * @author
 * @version 1.0
 */
public abstract class SqlHelper {

	/**
	 * 获得总条数
	 * @param ms
	 * @param connection
	 * @param parameterObject
	 * @param dialect
	 * @return
	 * @throws SQLException
	 */
	public static int getCount(final String sql,final MetaObject metaObject, final Connection connection, Dialect dialect) throws SQLException {
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		Object parameterObject = metaObject.getValue("delegate.boundSql.parameterObject");
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		String countSql = dialect.getCountString(sql);

		PreparedStatement stmt = null;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(countSql);
			DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
			handler.setParameters(stmt);
			rs = stmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			return count;
		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * 关闭closeStatement
	 * @param statement
	 */
	private static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}