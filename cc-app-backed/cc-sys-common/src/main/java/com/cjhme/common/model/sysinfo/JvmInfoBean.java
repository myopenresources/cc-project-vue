package com.cjhme.common.model.sysinfo;

import java.lang.management.ManagementFactory;
import java.util.Date;


import com.cjhme.common.util.ArithUtil;
import com.cjhme.common.util.DateUtil;

/**
 * 
 * <p>
 * Title: JvmInfoBean.java
 * </p>
 * Description: 虚拟机信息
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class JvmInfoBean {

	/**
	 * 当前JVM占用的内存(M)
	 */
	private double total;

	/**
	 * JVM最大可用内存(M)
	 */
	private double max;

	/**
	 * JVM空闲内存(M)
	 */
	private double free;

	/**
	 * JDK版本
	 */
	private String version;

	/**
	 * JDK路径
	 */
	private String home;

	public double getTotal() {
		return ArithUtil.div(total, (1024 * 1024), 2);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getMax() {
		return ArithUtil.div(max, (1024 * 1024), 2);
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getFree() {
		return ArithUtil.div(free, (1024 * 1024), 2);
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getUsed() {
		return ArithUtil.div(total - free, (1024 * 1024), 2);
	}

	public double getUsage() {
		return ArithUtil.mul(ArithUtil.div(total - free, total, 4), 100);
	}

	/**
	 * 获取JDK名称
	 */
	public String getName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * JDK启动时间
	 */
	public String getStartTime() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date(time));
	}

	/**
	 * JDK运行时间
	 */
	public String getRunTime() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return DateUtil.getDatePoor(new Date(), new Date(time));
	}
}
