package com.cjhme.system.impl.base.mybatis.pagination.interactive;

/**
*
* <p>
* Title: PaginationInteractive.java
* </p>
* Description: 分页交互
* <p>
* Modify histoty:
*
* @author cjh
* @version 1.0
*/
public class PaginationInteractive {

	// 总条数
	private static final ThreadLocal<Integer> PAGINATION_TOTAL = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};

	/**
	 * 设置分页条数
	 * 
	 * @param value
	 */
	public static void setPaginationTotal(Integer value) {
		PAGINATION_TOTAL.set(value);
	}
	

	/**
	 * 获得分页条数
	 * 
	 * @return
	 */
	public static int getPaginationTotal() {
		return PAGINATION_TOTAL.get();
	}

	/**
	 * 清除分页条数
	 */
	public static void cleanPaginationTotal() {
		PAGINATION_TOTAL.remove();
	}
}
