package com.cjhme.system.impl.base.mybatis.datapermissions.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.cjhme.common.model.datapermissions.DataPermissionsBean;
import com.cjhme.common.model.user.UserBean;

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
public class SqlHelper {

	/**
	 * 获取数据权限配置
	 * 
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	public static DataPermissionsBean getDataPermissions(final Connection connection, Map<String, Object> params)
			throws SQLException {
		String querySql = " SELECT T.PRIMARY_PROCESSOR,T.SECONDARY_PROCESSOR,T.MATCH_COLUMN,T.PARAM_KEY FROM SYS_DB.T_DATA_PERMISSIONS T LEFT JOIN SYS_DB.T_ROLE_DATA_PERMIT_MAPPING T2 ON (T.DATA_PERMISSIONS_ID=T2.DATA_PERMISSIONS_ID) LEFT JOIN SYS_DB.T_USER_ROLE_MAPPING T3 ON (T3.ROLE_ID=T2.ROLE_ID) WHERE T.ACCESS_URL=? AND T.SQL_ID=? AND T3.USER_ID=? ORDER BY T.PROCESSOR_PRIORITY";

		PreparedStatement stmt = null;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(querySql);
			stmt.setString(1, String.valueOf(params.get("accessUrl")));
			stmt.setString(2, String.valueOf(params.get("sqlId")));
			stmt.setString(3, ((UserBean) params.get("userInfo")).getUserId());
			rs = stmt.executeQuery();

			DataPermissionsBean result = null;
			while (rs.next()) {
				result = new DataPermissionsBean();
				result.setPrimaryProcessor(rs.getString("PRIMARY_PROCESSOR"));
				result.setSecondaryProcessor(rs.getString("SECONDARY_PROCESSOR"));
				result.setMatchColumn(rs.getString("MATCH_COLUMN"));
				result.setParamKey(rs.getString("PARAM_KEY"));
				break;
			}

			return result;

		} finally {
			closeStatement(stmt);
		}
	}

	/**
	 * 关闭closeStatement
	 * 
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
