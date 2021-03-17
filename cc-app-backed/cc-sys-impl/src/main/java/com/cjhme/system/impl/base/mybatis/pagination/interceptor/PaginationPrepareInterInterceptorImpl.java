package com.cjhme.system.impl.base.mybatis.pagination.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.StringUtils;

import com.cjhme.common.util.PatternMatchUtils;
import com.cjhme.system.impl.base.mybatis.interceptor.PrepareInterceptorInterface;
import com.cjhme.system.impl.base.mybatis.pagination.dialect.Dialect;
import com.cjhme.system.impl.base.mybatis.pagination.helper.DialectHelper;
import com.cjhme.system.impl.base.mybatis.pagination.helper.SqlHelper;
import com.cjhme.system.impl.base.mybatis.pagination.interactive.PaginationInteractive;
import com.cjhme.system.impl.base.mybatis.util.PluginUtil;

/**
*
* <p>
* Title: PaginationPrepareInterInterceptorImpl.java
* </p>
* Description: 分页拦截器实现
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
public class PaginationPrepareInterInterceptorImpl implements PrepareInterceptorInterface {

	
	// 方言
	private Dialect dialect;

	// 分页sql正则
	private String pagingSqlIdRegex;

	/**
	 * 拦截器实现
	 */
	public void intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = PluginUtil.realTarget(invocation.getTarget());
		MetaObject metaObject = MetaObject.forObject(statementHandler);
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		Object sqlCommandType =  metaObject.getValue("delegate.mappedStatement.sqlCommandType");
        String sql =  (String) metaObject.getValue("delegate.boundSql.sql");
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");

		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();

		boolean intercept = PatternMatchUtils.simpleMatch(pagingSqlIdRegex, mappedStatement.getId());
		if (SqlCommandType.SELECT.equals(sqlCommandType) && intercept && dialect.supportsLimit()
				&& (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
			
			Connection connection = (Connection) invocation.getArgs()[0];
			int count = SqlHelper.getCount(sql,metaObject, connection, dialect);
			PaginationInteractive.setPaginationTotal(count);

			metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
			metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
			metaObject.setValue("delegate.boundSql.sql", dialect.getLimitString(sql, offset, limit));
		}
		
	}

	/**
	 * 设置属性
	 */
	public void setProperties(Properties properties) {
		String dialectClass = properties.getProperty("dialectClass");
		if (StringUtils.isEmpty(dialectClass)) {
			Dialect.Type databaseType = null;
			try {
				databaseType = Dialect.Type.valueOf(properties.getProperty("dialect").toUpperCase());
			} catch (Exception e) {
			}

			if (null == databaseType) {
				throw new RuntimeException(
						"Plug-in [PaginationInterceptorImpl] the dialect of the attribute value is invalid! Valid values for:"
								+ getDialectTypeValidValues());
			}
			dialect = DialectHelper.getDialect(databaseType);
		} else {
			try {
				dialect = (Dialect) Class.forName(dialectClass).newInstance();
			} catch (Exception e) {
				throw new RuntimeException(
						"Plug-in [PaginationInterceptorImpl] cannot create dialect instance by dialectClass: "
								+ dialectClass);
			}
		}

		pagingSqlIdRegex = properties.getProperty("stmtIdRegex", "*.selectPaging");
	}

	private String getDialectTypeValidValues() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Dialect.Type.values().length; i++) {
			sb.append(Dialect.Type.values()[i].name()).append(",");
		}
		return sb.toString();
	}

	
}
