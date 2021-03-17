package com.cjhme.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * <p>
 * Title: DateUtil.java
 * </p>
 * Description: 日期工具类
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 */
public class DateUtil {
	/**
	 * Function: getNowDate 静态方讲学 Description: 获得当前日期,以yyyyMMdd形式表示 Calls: Called
	 * By: Table Accessed: Table Updated:
	 * 
	 * @return String 当前日期 *
	 */
	public static String getNowDate() {
		/* 创建一个Calendar对象 */
		Calendar now = Calendar.getInstance();

		/* 设置日期格式为yyyyMMdd */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		/* 格式化为已设置的日期格式 */
		String date = formatter.format(now.getTime());

		return date;
	}

	/**
	 * String to Date yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datestring
	 * @return
	 */
	public static java.util.Date stringToDate_yyyy_MM_dd_HH_mm_ss(String datestring) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatter.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * String to Date yyyy-MM-dd
	 * 
	 * @param datestring
	 *            "yyyy-MM-dd"
	 * @return
	 */
	public static java.util.Date stringToDate_yyyy_MM_dd(String datestring) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * String to Date yyyy/MM/dd
	 * 
	 * @param datestring
	 *            "yyyy/MM/dd"
	 * @return
	 */
	public static java.util.Date stringToDate_yyyy_MM_dd_2(String datestring) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return formatter.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * String to Date By format
	 * 
	 * @param datestring
	 * @return
	 */
	public static java.util.Date stringToDate(String datestring, String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		try {
			return formatter.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Function: getNowTime 静�?�方�? Description: 取当前时�?,以HHmmss形式表示 Calls: Called
	 * By: Table Accessed: Table Updated:
	 * 
	 * @return String 当前时间
	 */
	public static String getNowTime() {
		/* 创建�?个Calendar对象 */
		Calendar now = Calendar.getInstance();

		/* 设置时间格式为HH:mm:ss */
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");

		/* 格式化为已设置的时间格式 */
		String time = formatter.format(now.getTime());

		return time;
	}

	/**
	 * 根据格式格式化日�?
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String formatyyyyMMddHHmmss(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		return formatter.format(date);
	}

	public static String formatyyyyMMdd(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	public static String formatyyyy_MM_dd_HH_mms_s(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static String formatyyyy_MM_dd(Date date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(date);
		}
		return "";
	}

	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static final String parseDateToStr(final String format, final Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String formatyyyy(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(date);
	}

	public static String formatMM(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(date);
	}

	/**
	 * Function: getDateAfter Description: 得到给出日期之后n个月的日�? , 日期以yyyyMMdd形式表示 Calls:
	 * Called By: Table Accessed: Table Updated:
	 * 
	 * @param String
	 *            当前日期
	 * @param int
	 *            前进月数
	 * @return String 前进后日�?
	 */
	public static String getDateAfter(String curDate, int month) throws Exception

	{
		String ret = null;
		/* 日期格式不符合规�? */
		if (curDate.length() != 8) {
			throw new Exception("日期输入不正�?, 正确格式应为yyyyMMdd, �?: 20021220");
		}

		/* 从日期字符串中分别提取年, �?, �? */
		int curYear = Integer.parseInt(curDate.substring(0, 4));
		int curMonth = Integer.parseInt(curDate.substring(4, 6));
		int curDay = Integer.parseInt(curDate.substring(6, 8));

		/* 换算成增加的年数 */
		int addYear = month / 12;

		/* 增加的月�? */
		int addMonth = month % 12;

		/* 计算增加之后的年�? */
		curYear = curYear + addYear;

		int monthTmp = curMonth + addMonth;

		/* 有可能出现大�?12的情�? */
		if (monthTmp > 12) {
			curYear++; // 年数�?1
			curMonth = monthTmp - 12;
		} else {
			curMonth = monthTmp;
		}

		/* 当月数小�?10,前面�?0 */
		if (curMonth < 10) {
			ret = "" + curYear + "0" + curMonth;
		} else {
			ret = "" + curYear + curMonth;
		}
		if (curDay < 10) {
			ret = ret + "0" + curDay;
		} else {
			ret = ret + curDay;
		}
		return ret;
	}

	/**
	 * Function: minusDate 静�?�方�? Description: 计算两个日期之间的天�?
	 * (�?,按一�?360�?,�?�?30天计�?) Calls: Called By: Table Accessed: Table Updated:
	 * 
	 * @return int 天数
	 */
	public static int minusDate(String startDate, String endDate) {
		/* 从日期字符串中分别提取年, �?, �? */
		int startYear = Integer.parseInt(startDate.substring(0, 4));
		int startMonth = Integer.parseInt(startDate.substring(4, 6));
		int startDay = Integer.parseInt(startDate.substring(6, 8));

		int endYear = Integer.parseInt(endDate.substring(0, 4));
		int endMonth = Integer.parseInt(endDate.substring(4, 6));
		int endDay = Integer.parseInt(endDate.substring(6, 8));

		int rtnValue = (endYear - startYear) * 360 + (endMonth - startMonth) * 30 + (endDay - startDay);
		return rtnValue;
	}

	/**
	 * 取当前月的下�?个月的第�?日的日期
	 * 
	 * @return String 下月第一日日�?
	 */
	public static String getFirstDayOfNextMonth() {
		Calendar now = Calendar.getInstance();

		int curYear = now.get(Calendar.YEAR);
		int nextMonth = now.get(Calendar.MONTH) + 2;

		if (nextMonth == 13) {
			return "" + (curYear + 1) + "0101";
		} else if (nextMonth < 10) {
			return "" + curYear + "0" + nextMonth + "01";
		} else {
			return "" + curYear + nextMonth + "01";
		}
	}

	/**
	 * 取当前月第一日的日期
	 * 
	 * @return String 当前月第�?日日�?
	 */
	public static String getFirstDayOfThisMonth() {
		Calendar now = Calendar.getInstance();

		int curYear = now.get(Calendar.YEAR);
		int curMonth = now.get(Calendar.MONTH) + 1;

		if (curMonth < 10) {
			return "" + curYear + "0" + curMonth + "01";
		} else {
			return "" + curYear + curMonth + "01";
		}
	}

	// 取当前日期的上一天日�?
	// 例如 20030519 上一天日期为 20030518
	public static String getLastDayDate(String curDate) {
		String lastDay;
		if (curDate.substring(4, 8).equals("0101")) {
			int year = Integer.parseInt(curDate.substring(0, 4)); // 取年�?
			lastDay = "" + (year - 1) + "1231"; // 年份�?1, 月为12�?31�?
		} else {
			try {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat from_sdf = new SimpleDateFormat("yyyyMMdd");
				Date thisDate = from_sdf.parse(curDate);
				cal.setTime(thisDate);

				// 上滚�?�?
				// 当日期为yyyy0101�?,不能调用该方�?
				cal.roll(Calendar.DAY_OF_YEAR, -1);

				lastDay = from_sdf.format(cal.getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return lastDay;
	}

	// 取当前日期的上一月日�?
	// 例如 20030512 上一月日期为20030412
	public static String getLastMonthDate(String curDate) {
		String lastMonth;
		if (curDate.substring(4, 6).equals("01")) {
			int year = Integer.parseInt(curDate.substring(0, 4)); // 取年�?
			lastMonth = "" + (year - 1) + "12" + curDate.substring(6, 8); // 年份�?1,
			// 月为12�?,日同当前�?
		} else {
			try {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat from_sdf = new SimpleDateFormat("yyyyMMdd");
				Date thisDate = from_sdf.parse(curDate);
				cal.setTime(thisDate);

				// 上滚�?�?
				// 当日期为yyyy01dd�?,不能调用该方�?
				cal.roll(Calendar.MONTH, -1);

				lastMonth = from_sdf.format(cal.getTime());
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return lastMonth;
	}

	/**
	 * 取得当前日期n天之后的日期
	 * 
	 * @param String
	 *            当前日期
	 * @param int
	 *            �?后的天数
	 * @return String
	 */
	public static String getDateAfterDay(String curDate, int days) {
		String resultDate;
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat from_sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date thisDate = from_sdf.parse(curDate);
			cal.setTime(thisDate);
			cal.add(Calendar.DAY_OF_YEAR, days);

			resultDate = from_sdf.format(cal.getTime());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null;
		}
		return resultDate;
	}

	public static String dateToYYYYMM(Date date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		return sd.format(date);
	}

	/**
	 * 日期转换成日历
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar DateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 添加分钟
	 * 
	 * @param cal
	 * @param minute
	 * @return
	 */
	public static Calendar CalendarAddMinute(Calendar cal, int minute) {
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute);
		return cal;
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param cal1
	 * @param cal2
	 * @return
	 */
	public static int compareCalendar(Calendar cal1, Calendar cal2) {
		return cal1.compareTo(cal2);
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param data0
	 * @param data1
	 * @return
	 * @throws ParseException
	 */
	public static boolean compareDate(String str0, String str1, String format) throws ParseException {
		SimpleDateFormat sdf0 = new SimpleDateFormat(format);
		SimpleDateFormat sdf1 = new SimpleDateFormat(format);
		Date date0 = sdf0.parse(str0);
		Date date1 = sdf1.parse(str1);

		return date0.before(date1);
	}

	public static void main(String[] args) {
		try {
			Date now = new Date();
			Calendar cal = DateToCalendar(now);
			Calendar cal2 = DateToCalendar(now);
			cal = CalendarAddMinute(cal, 20);
			int i = compareCalendar(cal, cal2);
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算两个时间差
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}
}