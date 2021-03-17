package com.cjhme.common.model.sysinfo;

import com.cjhme.common.util.ArithUtil;

/**
 * 
 * <p>
 * Title: MemoryInfoBean.java
 * </p>
 * Description: 内存信息
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class MemoryInfoBean {

	/**
	 * 内存总量
	 */
	private double total;

	/**
	 * 已用内存
	 */
	private double used;

	/**
	 * 剩余内存
	 */
	private double free;

	public double getTotal() {
		return ArithUtil.div(total, (1024 * 1024 * 1024), 2);
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public double getUsed() {
		return ArithUtil.div(used, (1024 * 1024 * 1024), 2);
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public double getFree() {
		return ArithUtil.div(free, (1024 * 1024 * 1024), 2);
	}

	public void setFree(long free) {
		this.free = free;
	}

	public double getUsage() {
		return ArithUtil.mul(ArithUtil.div(used, total, 4), 100);
	}
}
