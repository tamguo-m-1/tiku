package com.tamguo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	/**
	 * 正常日期格式化模板.
	 */
	private static final SimpleDateFormat NORMAL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat NORMAL_DATE_FORMAT_YY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat NORMAL_DATE_FORMAT_YY_MM_DD_ = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat NORMAL_DATE_FORMAT_YYMMDDHHMISS = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(2011-11-24)
	 */
	public static String dateFormatYYMMDD(long date) {
		return NORMAL_DATE_FORMAT_YY_MM_DD.format(new Date(date * 1000));
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(20111124)
	 */

	public static String getCurrentDateStr() {
		return NORMAL_DATE_FORMAT_YY_MM_DD_.format(new Date());
	}

	public static String getCurrentDateYYYYMMDDStr() {
		return NORMAL_DATE_FORMAT_YY_MM_DD.format(new Date());
	}

	public static String getNextDayYYYYMMDDStr() {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(NORMAL_DATE_FORMAT_YY_MM_DD.parse(getCurrentTime()));
			cal.add(Calendar.DATE, 1);
			return NORMAL_DATE_FORMAT_YY_MM_DD.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long getNextDayYYYYMMDDLong() {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(NORMAL_DATE_FORMAT_YY_MM_DD.parse(getCurrentTime()));
			cal.add(Calendar.DATE, 1);
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(2011-11-24 16:46:38)
	 */
	public static String dateFormat(long date) {
		return NORMAL_DATE_FORMAT.format(new Date(date * 1000));
	}

	/**
	 * 正常日期格式化.
	 *
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(2011-11-24)
	 */
	public static String dateFormatYYYYMMDD(long date) {
		return NORMAL_DATE_FORMAT_YY_MM_DD.format(new Date(date * 1000));
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(2011-11-24 16:46:38)
	 */

	public static String dateFormat(Date date) {
		return NORMAL_DATE_FORMAT.format(date);
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(20111124164638)
	 */

	public static String dateFormathhmmss(Date date) {
		return NORMAL_DATE_FORMAT_YYMMDDHHMISS.format(date);
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(20111124164638)
	 */

	public static String dateFormatYYYYMMDD(Date date) {
		return NORMAL_DATE_FORMAT_YY_MM_DD_.format(date);
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期(2011-11-24 16:46:38)
	 */

	public static String getCurrentTime() {
		return NORMAL_DATE_FORMAT_YY_MM_DD.format(new Date());
	}

	/**
	 * 获取当前距1970年1月1日秒数.
	 * 
	 * @return 当前距1970年1月1日秒数.
	 */
	public static Long getTime() {
		return new Date().getTime() / 1000;
	}

	/**
	 * 获取当前距1970年1月1日秒数.
	 * 
	 * @return 获取最近一周的秒数.
	 */
	public static long getLastWeekTime() {
		return new Date().getTime() / 1000 - 7 * 24 * 60 * 60;
	}

	/**
	 * 获取当前距1970年1月1日秒数.
	 * 
	 * @return 获取最近一个月的秒数.
	 */
	public static long getLastMonthTime() {
		return new Date().getTime() / 1000 - 30 * 24 * 60 * 60;
	}

	/**
	 * 获取某年某月第一天.
	 * 
	 * @return 日期格式如：2011-12-31 00:00:00 .
	 */
	public static long getFirstDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(year));
		cal.set(Calendar.MONTH, Integer.valueOf(month) - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return parseDate(NORMAL_DATE_FORMAT_YY_MM_DD.format(cal.getTime()) + " 00:00:00").getTime() / 1000;
	}

	/**
	 * 获取某年某月最后一天.
	 * 
	 * @return 日期格式如：2011-12-31 23:59:59 .
	 */
	public static long getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(year));
		cal.set(Calendar.MONTH, Integer.valueOf(month) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return parseDate(NORMAL_DATE_FORMAT_YY_MM_DD.format(cal.getTime()) + " 23:59:59").getTime() / 1000;
	}

	/**
	 * 解析字符串为Date.
	 * 
	 * @param dateString
	 *            日期字符串 例如 2011-12-17 17:41:18.843 CST.
	 * @return 解析后的日期类型.若解析出错则返回null
	 */
	public static Date parseDate(String dateStr) {
		try {
			return NORMAL_DATE_FORMAT.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 正常日期格式化.
	 * 
	 * @param date
	 *            日期距离1970年1月1日秒数
	 * @return 格式化后的日期()
	 */

	public static long parseLong(String date) {
		return parseDate(date).getTime() / 1000;
	}

	/**
	 * 日期格式转换为字符串格式
	 * 
	 * @param date
	 * @return 格式化后的日期字符串
	 */
	public static String parseString(Date date) {

		return NORMAL_DATE_FORMAT_YY_MM_DD.format(date);
	}

	public static long getLastDayStartTime(long daytime) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTimeInMillis(daytime * 1000);
		todayStart.add(Calendar.DAY_OF_YEAR, -1);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTimeInMillis() / 1000;
	}

	public static long getLastDayEndTime(long daytime) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTimeInMillis(daytime * 1000);
		todayEnd.add(Calendar.DAY_OF_YEAR, -1);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTimeInMillis() / 1000;
	}

	public static long getTime(long time) {
		Calendar timeCalendar = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		timeCalendar.setTimeInMillis(time * 1000);
		timeCalendar.set(Calendar.HOUR_OF_DAY, nowCalendar.get(Calendar.HOUR_OF_DAY));
		timeCalendar.set(Calendar.MINUTE, nowCalendar.get(Calendar.MINUTE));
		timeCalendar.set(Calendar.SECOND, nowCalendar.get(Calendar.SECOND));
		timeCalendar.set(Calendar.MILLISECOND, nowCalendar.get(Calendar.MILLISECOND));
		return timeCalendar.getTimeInMillis() / 1000;
	}

	public static long getStartTime(long daytime) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTimeInMillis(daytime * 1000);
		// todayStart.add(Calendar.DAY_OF_YEAR, -1);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime() / 1000;
	}

	public static long getEndTime(long daytime) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTimeInMillis(daytime * 1000);
		// todayEnd.add(Calendar.DAY_OF_YEAR, -1);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTimeInMillis() / 1000;
	}

	/**
	 * 比较俩日期是否是同一天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean compareSameDate(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		boolean isSameYear = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
		return isSameDate;
	}

	/**
	 * 当月的第一天距1970年1月1日秒数.
	 * 
	 * @param date
	 * @return long
	 * @author mengxm
	 */
	public static final long firstMonthDayTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime().getTime() / 1000;
	}

	/**
	 * 根据指定格式解析时间
	 * 
	 * @param dateString
	 * @param fmtString
	 * @return Date
	 * @author mengxm
	 */
	public static final Date parse(String dateString, String fmtString) {
		Date date = null;
		try {
			DateFormat format = new SimpleDateFormat(fmtString);
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/** 比较提现前后时间间隔秒数 */
	public static boolean getDatePoor(Date endDate, Date nowDate) {

		// 获得两个时间的毫秒时间差异
		System.out.println(endDate.getTime());
		System.out.println(nowDate.getTime());
		long diff = (endDate.getTime() - nowDate.getTime()) / 1000;
		if (diff < 30) {
			return true;
		}
		return false;
	}

	/**
	 * 当前时间加几天
	 * 
	 * @param day
	 *            天数
	 * @return
	 */
	public static long currentDateAddDay(int day) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(NORMAL_DATE_FORMAT_YY_MM_DD.parse(getCurrentTime()));
			cal.add(Calendar.DATE, day); // add N days
			return cal.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	public static long dateAddDay(long dateLong, int day) {
		Calendar cal = Calendar.getInstance();
		Date d = new Date(dateLong * 1000);
		cal.setTime(d);
		cal.add(Calendar.DATE, day);
		return cal.getTimeInMillis() / 1000;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long between_days = 0L;
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			between_days = (time2 - time1) / (1000 * 3600 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	/** 日期转换为自定义格式输出 */
	public static String DateToString(Date date, String formatType) {
		if (date == null) {
			return null;
		}
		if (formatType == null || "".equals(formatType)) {
			return null;
		}
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatType);
			dateStr = sdf.format(date);
			return dateStr;
		} catch (Exception e) {
			return null;
		}
	}

	public static long getStartTimeCurrentDay() {
		Calendar todayStart = Calendar.getInstance();
		// todayStart.add(Calendar.DAY_OF_YEAR, -1);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime() / 1000;
	}

	public static long getEndTimeCurrentDay() {
		Calendar todayStart = Calendar.getInstance();
		// todayStart.add(Calendar.DAY_OF_YEAR, -1);
		todayStart.set(Calendar.HOUR_OF_DAY, 23);
		todayStart.set(Calendar.MINUTE, 59);
		todayStart.set(Calendar.SECOND, 59);
		todayStart.set(Calendar.MILLISECOND, 59);
		return todayStart.getTime().getTime() / 1000;
	}

	/** 日期转换为自定义格式输出 */
	public static String fomatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (format == null || "".equals(format)) {
			return "";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";

		}
	}

	public static String getLastDayFmtYYYYMMDD() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.add(Calendar.DAY_OF_YEAR, -1);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return NORMAL_DATE_FORMAT_YY_MM_DD_.format(todayStart.getTime());
	}

	/**
	 * @Title: getDateFormat
	 * @Description: 日期格式化 yyyy-MM-dd
	 * @param str
	 * @return String
	 */
	public static String getDateFormat(String str) {
		return dateFormatYYMMDD(Long.parseLong(str));
	}

	/**
	 * @Title: getTimeFormat
	 * @Description: 时间格式化 yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return String
	 */
	public static String getTimeFormat(String str) {
		return dateFormat(Long.parseLong(str));
	}

	public static void main(String[] args) {

		System.out.println(dateFormat(1441036802));
		System.out.println(getFirstDayOfMonth("2015", "9"));
		System.out.println(getLastDayOfMonth("2015", "8"));
		System.out.println(dateFormat(getLastMonthTime()));
		System.out.println(dateFormat(getLastWeekTime()));
		System.out.println(parseLong("2017-01-01 00:00:00"));
		System.out.println(getTime());
		System.out.println(dateFormat(1451624155));
		System.out.println(parse("20151222", "yyyyMMdd"));
		Calendar c = Calendar.getInstance();
		Date nowDate = c.getTime();
		c.set(Calendar.MINUTE, -1);
		Date endDate = c.getTime();
		System.out.println("nowDate--" + nowDate + ";endDate--" + endDate);
		System.out.println(getDatePoor(nowDate, endDate));
		System.out.println(dateFormatYYYYMMDD(new Date()));

		System.out.println("args = [" + DateUtil.currentDateAddDay(0) + "]");
		System.out.println("args = [" + DateUtil.parse("2016-01-19", "yyyy-MM-dd").getTime() + "]");
		
		System.out.println(getTime());
		System.out.println(getEndTimeCurrentDay());
		
		System.out.println(getEndTimeCurrentDay() - getTime());

	}

}
