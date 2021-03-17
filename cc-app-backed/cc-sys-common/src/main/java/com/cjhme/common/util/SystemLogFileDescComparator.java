package com.cjhme.common.util;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.cjhme.common.model.log.SystemLogFileBean;

/**
 * 
 * <p>
 * Title: SystemLogFileDescComparator.java
 * </p>
 * Description: 日志文件排序规则
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class SystemLogFileDescComparator implements Comparator<SystemLogFileBean> {

	/**
	 * 比较器规则
	 */
	public int compare(SystemLogFileBean o1, SystemLogFileBean o2) {
		boolean result;
		try {
			result = DateUtil.compareDate(o1.getLastModifyDate(), o2.getLastModifyDate(), "yyyy-MM-dd HH:mm:ss");
			if (result) {
				return 1;
			} else {
				return -1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 将File数组转换成List
	 * 
	 * @param files
	 * @return
	 */
	public static List<SystemLogFileBean> asList(File[] files) {
		List<SystemLogFileBean> list = new ArrayList<SystemLogFileBean>();
		for (File f : files) {
			SystemLogFileBean logFile = new SystemLogFileBean();
			logFile.setFileName(f.getName());
			logFile.setFilePath(f.getPath());
			logFile.setFileSize(f.length() + "B");
			logFile.setLastModifyDate(DateUtil.formatDate(new Date(f.lastModified()), "yyyy-MM-dd HH:mm:ss"));
			list.add(logFile);
		}
		// 排序
		Collections.sort(list, new SystemLogFileDescComparator());
		return list;
	}

}