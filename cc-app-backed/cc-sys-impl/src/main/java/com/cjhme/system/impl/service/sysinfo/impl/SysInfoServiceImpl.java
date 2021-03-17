package com.cjhme.system.impl.service.sysinfo.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.cjhme.common.model.sysinfo.ComputerInfoBean;
import com.cjhme.common.model.sysinfo.CpuInfoBean;
import com.cjhme.common.model.sysinfo.DiskInfoBean;
import com.cjhme.common.model.sysinfo.JvmInfoBean;
import com.cjhme.common.model.sysinfo.MemoryInfoBean;
import com.cjhme.common.util.ArithUtil;
import com.cjhme.common.util.ByteUtil;
import com.cjhme.common.util.IPUtil;
import com.cjhme.system.impl.service.sysinfo.SysInfoService;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

/**
 * 
 * <p>
 * Title: SysInfoServiceImpl.java
 * </p>
 * Description: 系统信息
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
@Service("system.sysInfoService")
public class SysInfoServiceImpl implements SysInfoService {

	private static final int OSHI_WAIT_SECOND = 1000;

	public Map<String, Object> querySysInfo() throws Exception {
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();

		Map<String, Object> map = new HashMap<>();
		map.put("cpuInfo", this.setCpuInfo(hal.getProcessor()));
		map.put("memoryInfo", this.setMemoryInfo(hal.getMemory()));
		map.put("computerInfo", this.setComputerInfo());
		map.put("jvmInfo", this.setJvmInfo());
		map.put("diskInfo", this.setDiskInfo(si.getOperatingSystem()));
		return map;
	}

	/**
	 * 设置cpu信息
	 * 
	 * @param processor
	 * @return
	 */
	private CpuInfoBean setCpuInfo(CentralProcessor processor) {
		// CPU信息
		long[] prevTicks = processor.getSystemCpuLoadTicks();
		Util.sleep(OSHI_WAIT_SECOND);
		long[] ticks = processor.getSystemCpuLoadTicks();
		long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
		long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
		long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
		long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
		long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
		long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
		long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
		long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
		long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

		CpuInfoBean cpuInfo = new CpuInfoBean();
		cpuInfo.setCpuNum(processor.getLogicalProcessorCount());
		cpuInfo.setTotal(totalCpu);
		cpuInfo.setSys(cSys);
		cpuInfo.setUsed(user);
		cpuInfo.setWait(iowait);
		cpuInfo.setFree(idle);
		return cpuInfo;
	}

	/**
	 * 设置内存信息
	 */
	private MemoryInfoBean setMemoryInfo(GlobalMemory memory) {
		MemoryInfoBean memoryInfo = new MemoryInfoBean();
		memoryInfo.setTotal(memory.getTotal());
		memoryInfo.setUsed(memory.getTotal() - memory.getAvailable());
		memoryInfo.setFree(memory.getAvailable());
		return memoryInfo;
	}

	/**
	 * 设置服务器信息
	 */
	private ComputerInfoBean setComputerInfo() {
		ComputerInfoBean computerInfo = new ComputerInfoBean();
		Properties props = System.getProperties();
		computerInfo.setComputerName(IPUtil.getHostName());
		computerInfo.setComputerIp(IPUtil.getHostIp());
		computerInfo.setOsName(props.getProperty("os.name"));
		computerInfo.setOsArch(props.getProperty("os.arch"));
		computerInfo.setUserDir(props.getProperty("user.dir"));
		return computerInfo;
	}

	/**
	 * 设置Java虚拟机信息
	 */
	private JvmInfoBean setJvmInfo() throws UnknownHostException {
		JvmInfoBean jvmInfo = new JvmInfoBean();
		Properties props = System.getProperties();
		jvmInfo.setTotal(Runtime.getRuntime().totalMemory());
		jvmInfo.setMax(Runtime.getRuntime().maxMemory());
		jvmInfo.setFree(Runtime.getRuntime().freeMemory());
		jvmInfo.setVersion(props.getProperty("java.version"));
		jvmInfo.setHome(props.getProperty("java.home"));
		return jvmInfo;
	}

	/**
	 * 设置磁盘信息
	 */
	private List<DiskInfoBean> setDiskInfo(OperatingSystem os) {
		List<DiskInfoBean> diskInfoList = new ArrayList<>();
		FileSystem fileSystem = os.getFileSystem();
		OSFileStore[] fsArray = fileSystem.getFileStores();
		for (OSFileStore fs : fsArray) {
			long free = fs.getUsableSpace();
			long total = fs.getTotalSpace();
			long used = total - free;
			DiskInfoBean diskInfo = new DiskInfoBean();
			diskInfo.setDirName(fs.getMount());
			diskInfo.setSysTypeName(fs.getType());
			diskInfo.setTypeName(fs.getName());
			diskInfo.setTotal(ByteUtil.convertSizeToStr(total));
			diskInfo.setFree(ByteUtil.convertSizeToStr(free));
			diskInfo.setUsed(ByteUtil.convertSizeToStr(used));
			diskInfo.setUsage(ArithUtil.mul(ArithUtil.div(used, total, 4), 100));
			diskInfoList.add(diskInfo);
		}
		
		return diskInfoList;
	}

}
