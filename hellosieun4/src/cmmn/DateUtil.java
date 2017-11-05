package cmmn;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Strings�쓣 Dates�� Timestamps濡� 蹂��솚
 * 
 */
public class DateUtil {
	protected final static Log log = LogFactory.getLog(DateUtil.class);

	private static final String TIME_PATTERN = "HH:mm";

	/**
	 * �쑀�떥由ы떚 �겢�옒�뒪�뒗 湲곕낯 �깮�꽦�옄 �궗�슜 �븞�븿.
	 */
	private DateUtil() {
	}

	/**
	 * 湲곕낯 �궇吏� �뙣�꽩 (yyyy/MM/dd) 諛섑솚
	 * 
	 * @return UI�뿉 �궗�슜�븯湲� �쐞�븳 date pattern 諛섑솚
	 */
	public static String getDatePattern() {
		return "yyyy/MM/dd";
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * �뤌�뿉�꽌 dd-MMM-yyyy �삎�깭瑜� mm/dd/yyyy �삎�깭濡� �삤�씪�겢 �궇吏� �룷留룹쑝濡� 蹂��솚.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return UI瑜� �쐞�빐 �룷留룻솕�맂 臾몄옄�뿴 諛섑솚
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat("yyyy/MM/dd");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * �엯�젰�븯�뒗 �룷留룹뿉�꽌 date/time�쓽 臾몄옄�뿴 �깮�꽦
	 * 
	 * @param aMask
	 *            �엯�젰�릺�뒗 �궇吏� �뙣�꽩
	 * @param strDate
	 *            �궇吏� 臾몄옄�뿴 �몴�쁽
	 * @return 蹂��솚�맂 �궇吏� 媛앹껜 諛섑솚
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static String getDate(String aMask, String strDate) {
		SimpleDateFormat df;
		Date date;
		String returnValue = "";

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			df = new SimpleDateFormat(aMask);
			date = df.parse(strDate);

			returnValue = df.format(date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return returnValue;
	}

	/**
	 * �쁽�옱 �궇吏� �떆媛꾩쓣 �떎�쓬 �룷留룹쑝濡� 諛섑솚: MM/dd/yyyy HH:MM a
	 * 
	 * @param �쁽�옱
	 *            �떆媛�
	 * @return �쁽�옱 date/time 諛섑솚
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * �쁽�옱 �궇吏쒕�� �떎�쓬 �룷留룹쓣 諛섑솚: MM/dd/yyyy
	 * 
	 * @return �쁽�옱 �궇吏� 諛섑솚
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Calendar getToday() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * �쁽�옱�떆媛�
	 * 
	 * @return �쁽�옱 �궇吏� 諛섑솚
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssms");

		return sdf.format(cal.getTime());
	}

	/**
	 * �씪�떆
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		return sdf.format(cal.getTime());
	}

	/**
	 * �엯�젰�븯�뒗 �룷留룹뿉�꽌 �궇吏쒖쓽 date/time�쓽 臾몄옄�뿴 �깮�꽦
	 * 
	 * @param aMask
	 *            �엯�젰�릺�뒗 �궇吏� �뙣�꽩
	 * @param aDate
	 *            �궇吏� 媛앹껜
	 * @return �룷留룻똿�맂 臾몄옄�뿴 �궇吏� �몴�쁽
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * �엯�젰�븯�뒗 �룷留룹뿉�꽌 �떆�뒪�뀥 �봽濡쒗띁�땲�쓽 'dateFormat'瑜� 湲곗큹濡� �븯�뿬 �궇吏쒖쓽 date/time�쓽 臾몄옄�뿴 �깮�꽦
	 * 
	 * @param aDate
	 *            蹂��솚�븷 �궇吏�
	 * @return �궇吏쒖쓽 臾몄옄�뿴 �몴�쁽 諛섑솚
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * �궇吏� �뙣�꽩�쓣 �씠�슜�븯�뿬 String --> date濡� 蹂��솚
	 * 
	 * @param strDate
	 *            蹂��솚�븷 �궇吏� (�떎�쓬 �룷留룹쑝濡�: MM/dd/yyyy)
	 * @return �궇吏� 媛앹껜 諛섑솚
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate) {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
		}

		return aDate;
	}

	public static String getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR); // YEAR �뒗 紐⑤몢 ��臾몄옄濡� �뜥�빞�븳�떎.
		return String.valueOf(year);
	}

	public static String getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1; // MONTH �뒗 紐⑤몢 ��臾몄옄濡� �뜥�빞�븳�떎.(�썡�뿉�뒗 1�쓣 �뜑�빐以섏빞 �븳�떎.)

		return StringUtil.getZeroMask(String.valueOf(month), 2);
	}

	public static String getDay() {
		Calendar cal = Calendar.getInstance();
		int dat = cal.get(Calendar.DATE); // DATE �뒗 紐⑤몢 ��臾몄옄濡� �뜥�빞�븳�떎.

		return StringUtil.getZeroMask(String.valueOf(dat), 2);
	}
	

	public static String getWeek() {
		Calendar cal = Calendar.getInstance();

		int weeknum = cal.get(Calendar.DAY_OF_WEEK);
		String weekstr = "00";
		switch (weeknum) {
		case 1:
			weekstr = "�씪";
			break;
		case 2:
			weekstr = "�썡";
			break;
		case 3:
			weekstr = "�솕";
			break;
		case 4:
			weekstr = "�닔";
			break;
		case 5:
			weekstr = "紐�";
			break;
		case 6:
			weekstr = "湲�";
			break;
		case 7:
			weekstr = "�넗";
			break;
		}

		return String.valueOf(weekstr);
	}

	public static String getHour() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR);
		return String.valueOf(hour);
	}

	public static String getHourHH() {
		Calendar cal = Calendar.getInstance();
		int dat =  cal.get ( Calendar.HOUR_OF_DAY ); // DATE �뒗 紐⑤몢 ��臾몄옄濡� �뜥�빞�븳�떎.
		
		return StringUtil.getZeroMask(String.valueOf(dat), 2);
	}

	public static String getMinute() {
		Calendar cal = Calendar.getInstance();
		int min = cal.get(Calendar.MINUTE);
		return String.valueOf(min);
	}

	public static String getSecond() {
		Calendar cal = Calendar.getInstance();
		int sec = cal.get(Calendar.SECOND);
		return String.valueOf(sec);
	}

	public static boolean getLeapYear() {
		boolean tf = false;
		int year = Integer.parseInt(getYear());
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			tf = true;
			// System.out.println("�삱�빐�뒗 �쑄�뀈�엯�땲�떎");
		}

		return tf;
	}

	public static String getDate() {
		return getYear() + getMonth() + getDay();
	}
	
	public static String getDateYM() {
		return getYear() + getMonth();
	}

	/**
	 * �씪�떆
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getSysDate() {
		String dateString = "";

		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String textDate = format.format(cal.getTime());

			java.util.Date date = format.parse(textDate);
			java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			dateString = format1.format(date);

		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}

		return dateString;
	}

	/**
	 * �씪�떆
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getConvertDate(String str, String pattern) {
		String dateString = "";

		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			String textDate = format.format(str);

			java.util.Date date = format.parse(textDate);
			java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyyMMdd");

			dateString = format1.format(date);

		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}

		return dateString;
	}

	/**
	 * check date string validation with the default format "yyyyMMdd".
	 * 
	 * @param s
	 *            date string you want to check with default format "yyyyMMdd".
	 * @return date java.util.Date
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 format �뿉 留욎� �븡�뒗 寃쎌슦.
	 **/
	public static java.util.Date check(String s) throws java.text.ParseException {
		return check(s, "yyyyMMdd");
	}

	/**
	 *�궇吏쒕�� �몴�쁽�븯�뒗 �삎�떇�쓣 蹂�寃쏀븯�뿬 蹂�寃쎈맂 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * 
	 * @param s
	 *            �궇吏쒕�� �굹���궡�뒗 臾몄옄�뿴
	 * @param format
	 *            �냼�뒪(s) �궇吏쒖쓽 �삎�떇�쓣 �꽕紐낇븯�뒗 臾몄옄�뿴 ,�삁) "yyyy-MM-dd"
	 * @param toformat
	 *            蹂�寃쎈맆 �궇吏쒖쓽 �삎�떇�쓣 �꽕紐낇븯�뒗 臾몄옄�뿴 ,�삁) "yyyy-MM-dd"
	 * @return toformat�삎�깭濡� 蹂�寃쎈맂 �궇吏쒕�� �몴�떆�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 format �뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String changeFormat(String s, String format, String toformat) throws java.text.ParseException {
		java.util.Date date = check(s, format);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(toformat, java.util.Locale.KOREA);
		String dateString = formatter.format(date);
		return dateString;

	}

	/**
	 *�궇吏쒕�� �몴�쁽�븯�뒗 �삎�떇�쓣 蹂�寃쏀븯�뿬 蹂�寃쎈맂 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * 
	 * @param date
	 *            �궇吏쒕�� �굹���궡�뒗 Date媛앹껜
	 * @param toformat
	 *            蹂�寃쎈맆 �궇吏쒖쓽 �삎�떇�쓣 �꽕紐낇븯�뒗 臾몄옄�뿴 ,�삁) "yyyy-MM-dd"
	 * @return toformat�삎�깭濡� 蹂�寃쎈맂 �궇吏쒕�� �몴�떆�븯�뒗 臾몄옄�뿴
	 */
	public static String changeFormat(Date date, String toformat) {

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(toformat, java.util.Locale.KOREA);
		String dateString = formatter.format(date);
		return dateString;

	}

	/**
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *            date string you want to check.
	 * @param format
	 *            string representation of the date format. For example, "yyyy-MM-dd".
	 * @return date java.util.Date
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 format �뿉 留욎� �븡�뒗 寃쎌슦 諛쒖깮
	 */
	public static java.util.Date check(String s, String format) throws java.text.ParseException {
		if (s == null)
			throw new java.text.ParseException("date string to check is null", 0);
		if (format == null)
			throw new java.text.ParseException("format string to check date is null", 0);

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;
		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			/*
			 * throw new java.text.ParseException( e.getMessage() + " with format \"" + format + "\"", e.getErrorOffset() );
			 */
			throw new java.text.ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
		}

		if (!formatter.format(date).equals(s))
			throw new java.text.ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
		return date;
	}

	/**
	 * check date string validation with the default format "HH:mm:ss".
	 * 
	 * @param s
	 *            date string you want to check with default format "HH:mm:ss"
	 * @return <tt>true</tt> �궇吏� �삎�떇�씠 留욊퀬, 議댁옱�븯�뒗 �궇吏쒖씪 �븣. <tt>false</tt> �궇吏� �삎�떇�씠 留욎� �븡嫄곕굹, 議댁옱�븯吏� �븡�뒗 �궇吏쒖씪 �븣
	 */
	public static boolean isValid(String s) {
		return isValid(s, "HH:mm:ss");
	}

	/**
	 * "HH:mm" �삉�뒗 "HH/mm" �삎�깭�쓽 <code>java.util.Date</code> 媛앹껜瑜� 由ы꽩�븳�떎.
	 * 
	 * @param s
	 *            "HH:mm" �삉�뒗 "HH/mm" �삎�깭�쓽 �쁽�옱 �떆媛�(紐뉗떆 紐뉖텇)�쓣 �굹���궡�뒗 臾몄옄�뿴
	 * @return �씤�옄濡� �쟾�떖�맂 �떆媛곸뿉 �빐�떦�븯�뒗 <code>java.util.Date</code> 媛앹껜
	 * @throws java.text.ParseException
	 *             �씤�옄濡� �쟾�떖�맂 �떆媛곸씠 吏��젙�맂 �룷硫�("HH:mm" or "HH/mm" �뿉) 留욎� �븡嫄곕굹 �삱諛붾Ⅸ �떆媛꾩씠 �븘�땺寃쎌슦 諛쒖깮.
	 */
	public static Date getDateInstance(String s) throws java.text.ParseException {
		String format = "HH:mm";

		if (!isValid(s, "HH:mm")) {
			if (isValid(s, "HH/mm")) {
				format = "HH/mm";
			} else {
				throw new java.text.ParseException("wrong data or format", 0);
			}
		}
		return check(s, format);
	}

	/**
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *            date string you want to check.
	 * @param format
	 *            string representation of the date format. For example, "yyyy-MM-dd".
	 * @return <tt>true</tt> �궇吏� �삎�떇�씠 留욊퀬, 議댁옱�븯�뒗 �궇吏쒖씪 �븣. <tt>false</tt> �궇吏� �삎�떇�씠 留욎� �븡嫄곕굹, 議댁옱�븯吏� �븡�뒗 �궇吏쒖씪 �븣
	 */
	public static boolean isValid(String s, String format) {
		/*
		 * if ( s == null ) throw new NullPointerException("date string to check is null"); if ( format == null ) throw new NullPointerException("format string to check date is null");
		 */
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;
		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			return false;
		}

		if (!formatter.format(date).equals(s))
			return false;

		return true;
	}

	/**
	 * �쁽�옱 �궇吏쒕�� "yyyy-MM-dd" �삎�깭�쓽 �룷硫㏃쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * 
	 * @return formatted string representation of current day with "yyyy-MM-dd".
	 */
	public static String getDateString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * 
	 * �삤�뒛 �궇吏쒕�� �닽�옄濡� 由ы꽩�븳�떎. <code>getNumberByPattern("dd");</code>
	 * 
	 * @return �삤�뒛 �궇吏�.(1~31)
	 * @see #getNumberByPattern(String)
	 */
	/*
	 * public static int getDay() { return getNumberByPattern("dd"); }
	 */
	/**
	 * 
	 * �삱�빐瑜� �닽�옄濡� 由ы꽩�븳�떎. <code> getNumberByPattern("yyyy");</code>
	 * 
	 * @return �삱�빐瑜� �몴�쁽�븯�뒗 4�옄由� �닽�옄(�삁:2005)
	 * @see #getNumberByPattern(String)
	 */
	/*
	 * public static int getYear() { return getNumberByPattern("yyyy"); }
	 */
	/**
	 * 
	 * �씠踰덈떖�쓣 �닽�옄濡� 由ы꽩�븳�떎. <code>getNumberByPattern("MM");</code>
	 * 
	 * @return �씠踰덈떖�쓣 �몴�쁽�븯�뒗 �닽�옄 (1~12)
	 * @see #getNumberByPattern(String)
	 */
	/*
	 * public static int getMonth() { return getNumberByPattern("MM"); }
	 */
	/**
	 * 
	 * �쁽�옱 �떆媛꾩쓣 由ы꽩�븳�떎. <code>getNumberByPattern("HH");</code>
	 * 
	 * @return �쁽�옱 �떆媛꾩쓣 �몴�쁽�븯�뒗 �닽�옄(1~24)
	 * @see #getNumberByPattern(String)
	 */
	/*
	 * public static int getHour() { return getNumberByPattern("HH"); }
	 */

	/**
	 * 
	 * �쁽�옱 �떆媛곸쓽 遺꾩쓣 由ы꽩�븳�떎. <code>getNumberByPattern("mm");</code>
	 * 
	 * @return �쁽�옱 �떆媛곸쨷 遺꾩쓣 �몴�쁽�븯�뒗 �닽�옄(0~59)
	 * @see #getNumberByPattern(String)
	 */
	public static int getMin() {
		return getNumberByPattern("mm");
	}

	/**
	 * 
	 *�씤�옄濡� �쟾�떖�맂 �뙣�꽩�뿉 �빐�떦�븯�뒗 媛믪쓣 �닽�옄濡� 由ы꽩�븳�떎.
	 * 
	 * 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int currentYearValue = DateTimeUtil.getNumberByPattern(&quot;yyyy&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * 
	 * @param pattern
	 *            "yyyy, MM, dd, HH, mm, ss,SSS"
	 * @return �쁽�옱�쓽 �궇吏�,�떖,�뿰,�떆媛�,遺�,珥� �벑�쓣 �굹���궡�뒗 �닽�옄媛�
	 */
	public static int getNumberByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return Integer.parseInt(dateString);
	}

	/**
	 *�씤�옄濡� �쟾�떖�맂 �떆媛곸쓣 �몴�쁽�븯�뒗 臾몄옄�뿴�뿉�꽌 �듅�젙 遺�遺꾩쓽(�뀈�룄 or �떆 or 遺� or 珥� ...) 媛믪쓣 �닽�옄濡� 由ы꽩�븳�떎.
	 * 
	 *�떆媛곸쓣 �몴�쁽�븯�뒗 臾몄옄�뿴 2005/01/21 12:45:31 �뿉�꽌 珥� 遺�遺꾩쓣 �굹���궡�뒗 媛믪쓣 �뼸�뼱�삤�젮硫� �븘�옒�� 媛숈씠 肄붾뵫�븯硫� �맂�떎.
	 * <p>
	 * 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int seconds = DateTimeUtiil.getNumberByPattern(&quot;2005/01/21 12:45:31&quot;, &quot;yyyy/MM/dd hh:mm:ss&quot;, &quot;ss&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param dates
	 *            湲곗� �떆媛�
	 * @param spattern
	 *            <code>dates</code> �떆媛곸쓣 �몴�쁽�븯�뒗 �궇吏� �룷硫�
	 * @param pattern
	 *            "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your pattern.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 spattern format �뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int getNumberByPattern(String dates, String spattern, String pattern) throws java.text.ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(check(dates, spattern));
		return Integer.parseInt(dateString);
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 �씤�옄濡� �쟾�떖�맂 �삎�깭�쓽 �룷硫㏃쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎. 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String time = DateTime.getFormatString(&quot;yyyy-MM-dd HH:mm:ss:SSS&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param pattern
	 *            "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your pattern.
	 */
	public static String getFormatString(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, java.util.Locale.KOREA);
		String dateString = formatter.format(new java.util.Date());
		return dateString;
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 "yyyyMMdd" �삎�깭�쓽 臾몄옄�뿴濡� �몴�쁽�븯�뿬 由ы꽩�븳�떎. �삁) "20040205" <code>getFormatString("yyyyMMdd");</code>
	 * 
	 * @return formatted string representation of current day with "yyyyMMdd".
	 * @see #getFormatString(String)
	 */
	public static String getShortDateString() {
		return getFormatString("yyyyMMdd");
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 "yyyy-MM-dd" �삎�깭�쓽 臾몄옄�뿴濡� �몴�쁽�븯�뿬 由ы꽩�븳�떎. �삁) "2004-02-05" <code>getFormatString("yyyy-MM-dd");</code>
	 * 
	 * @return formatted string representation of current day with "yyyy-MM-dd".
	 * @see #getFormatString(String)
	 */
	public static String getShortDateDashString() {
		return getFormatString("yyyy-MM-dd");
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 "HHmmss" �삎�깭�쓽 臾몄옄�뿴濡� �몴�쁽�븯�뿬 由ы꽩�븳�떎. <code>getFormatString("HHmmss");</code>
	 * 
	 * @return formatted string representation of current time with "HHmmss".
	 * @see #getFormatString(String)
	 */
	public static String getShortTimeString() {

		return getFormatString("HHmmss");
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 "yyyy-MM-dd-HH:mm:ss:SSS" �삎�깭�쓽 臾몄옄�뿴濡� �몴�쁽�븯�뿬 由ы꽩�븳�떎. <code>getFormatString("yyyy-MM-dd-HH:mm:ss:SSS");</code>
	 * 
	 * @return formatted string representation of current time with "yyyy-MM-dd-HH:mm:ss".
	 * @see #getFormatString(String)
	 */
	public static String getTimeStampString() {

		return getFormatString("yyyy-MM-dd-HH:mm:ss:SSS");
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 "HH:mm:ssSSS" �삎�깭�쓽 臾몄옄�뿴濡� �몴�쁽�븯�뿬 由ы꽩�븳�떎. 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String timeString = getFormatString(&quot;HH:mm:ss&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @return formatted string representation of current time with "HH:mm:ss".
	 * @see #getFormatString(String)
	 */
	public static String getTimeString() {
		return getFormatString("HH:mm:ss");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 "yyyyMMdd" �삎�깭�쓽 �궇吏쒓� 臾댁뒯 �슂�씪 �씤吏� 由ы꽩�븳�떎. �슂�씪�뿉 �빐�떦�븯�뒗 媛믪� �닽�옄濡� 由ы꽩�릺怨� �씠 媛믪� 1~7�뿉 �빐�떦�븳�떎.
	 * 
	 * �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String s = &quot;20000529&quot;;
	 * int dayOfWeek = whichDay(s);
	 * if (dayOfWeek == java.util.Calendar.MONDAY)
	 * 	System.out.println(&quot; �썡�슂�씪: &quot; + dayOfWeek);
	 * if (dayOfWeek == java.util.Calendar.TUESDAY)
	 * 	System.out.println(&quot; �솕�슂�씪: &quot; + dayOfWeek);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * return days between two date strings with default defined format.(yyyyMMdd)
	 * 
	 * @param s
	 *            date string you want to check.
	 * @return �떎�쓬�쓽 媛믪쨷 �븯�굹瑜� 由ы꽩.
	 * 
	 *         <pre>
	 *          1: �씪�슂�씪 (java.util.Calendar.SUNDAY)
	 *          2: �썡�슂�씪 (java.util.Calendar.MONDAY)
	 *          3: �솕�슂�씪 (java.util.Calendar.TUESDAY)
	 *          4: �닔�슂�씪 (java.util.Calendar.WENDESDAY)
	 *          5: 紐⑹슂�씪 (java.util.Calendar.THURSDAY)
	 *          6: 湲덉슂�씪 (java.util.Calendar.FRIDAY)
	 *          7: �넗�슂�씪 (java.util.Calendar.SATURDAY)
	 * </pre>
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 "yyyyMMdd" �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 * 
	 */
	public static int whichDay(String s) throws java.text.ParseException {
		return whichDay(s, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>format</code>�삎�깭�쓽 �궇吏� <code>s</code>媛� 臾댁뒯 �슂�씪 �씤吏� 由ы꽩�븳�떎. �슂�씪�뿉 �빐�떦�븯�뒗 媛믪� �닽�옄濡� 由ы꽩�릺怨� �씠 媛믪� 1~7�뿉 �빐�떦�븳�떎.
	 * 
	 * �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String s = &quot;2000-05-29&quot;;
	 * int dayOfWeek = whichDay(s, &quot;yyyy-MM-dd&quot;);
	 * if (dayOfWeek == java.util.Calendar.MONDAY)
	 * 	System.out.println(&quot; �썡�슂�씪: &quot; + dayOfWeek);
	 * if (dayOfWeek == java.util.Calendar.TUESDAY)
	 * 	System.out.println(&quot; �솕�슂�씪: &quot; + dayOfWeek);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            date string you want to check.
	 * @param format
	 *            �궇吏쒕�� �몴�쁽�븯�뒗 �룷硫�.
	 * @return �떎�쓬�쓽 媛믪쨷 �븯�굹瑜� 由ы꽩.
	 * 
	 *         <pre>
	 *          1: �씪�슂�씪 (java.util.Calendar.SUNDAY)
	 *          2: �썡�슂�씪 (java.util.Calendar.MONDAY)
	 *          3: �솕�슂�씪 (java.util.Calendar.TUESDAY)
	 *          4: �닔�슂�씪 (java.util.Calendar.WENDESDAY)
	 *          5: 紐⑹슂�씪 (java.util.Calendar.THURSDAY)
	 *          6: 湲덉슂�씪 (java.util.Calendar.FRIDAY)
	 *          7: �넗�슂�씪 (java.util.Calendar.SATURDAY)
	 * </pre>
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 format �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int whichDay(String s, String format) throws java.text.ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check(s, format);

		java.util.Calendar calendar = formatter.getCalendar();
		calendar.setTime(date);
		return calendar.get(java.util.Calendar.DAY_OF_WEEK);
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �궇吏쒖� <code>to</code> �궇吏� �궗�씠�쓽 '�궇(day)'李⑥씠瑜� 由ы꽩�븳�떎. �몢 �궇吏쒖쓽 �몴�쁽�룷硫㏃� "yyyyMMdd"�씠�떎.
	 * <p>
	 * 2005�뀈 1�썡1�씪遺��꽣 2005�뀈3�썡25�씪 �궗�씠�쓽 �궇吏쒖닔瑜� 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int daysCount = DateTimeUtil.daysBetween(&quot;20050101&quot;, &quot;20050325&quot;);
	 * </pre>
	 * 
	 * </blockquote> return days between two date strings with default defined format.("yyyyMMdd")
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @return �몢 �궇吏� �궗�씠�쓽 '�궇(day)'�쓽 李⑥씠.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 "yyyyMMdd" �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int daysBetween(String from, String to) throws java.text.ParseException {
		return daysBetween(from, to, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �궇吏쒖� <code>to</code> �궇吏� �궗�씠�쓽 '�궇(day)'李⑥씠瑜� 由ы꽩�븳�떎. �씠�븣 �몢 �궇吏쒕�� �몴�쁽�븯�뒗 �룷硫㏃� <code>format</code>�쓣 �궗�슜�븳�떎.
	 * <p>
	 * 2005�뀈 1�썡1�씪遺��꽣 2005�뀈3�썡25�씪 �궗�씠�쓽 �궇吏쒖닔瑜� 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int daysCount = DateTimeUtil.daysBetween(&quot;20050101&quot;, &quot;20050325&quot;, &quot;yyyyMMdd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @param format
	 *            �몢 �떆媛곸쓣 �몴�쁽�븯�뒗 �룷硫� 臾몄옄�뿴.
	 * @return �몢 �떆媛� �궗�씠�쓽 '�궇(day)'�쓽 李⑥씠.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 format �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int daysBetween(String from, String to, String format) throws java.text.ParseException {
		java.util.Date d1 = check(from, format);
		java.util.Date d2 = check(to, format);

		long duration = d2.getTime() - d1.getTime();

		return (int) (duration / (1000 * 60 * 60 * 24));
		// seconds in 1 day
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code>�떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 '�떆媛� (time)'李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽�룷硫㏃� "yyyyMMdd"�씠�떎.
	 *<p>
	 * 2005�뀈 1�썡1�씪遺��꽣 2005�뀈3�썡25�씪 �궗�씠�쓽 �떆媛꾩쓣 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int timesCount = DateTimeUtil.timesBetween(&quot;20050101&quot;, &quot;20050325&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @return �몢 �떆媛� �궗�씠�쓽 '�떆媛�(time)'�쓽 李⑥씠.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 "yyyyMMdd" �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int timesBetween(String from, String to) throws java.text.ParseException {
		return timesBetween(from, to, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 '�떆媛�(time)'李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽�룷硫㏃� <code>format</code>�쓣 �궗�슜�븳�떎.
	 * <p>
	 * 2005�뀈 1�썡1�씪遺��꽣 11�떆�� 2005�뀈3�썡25�씪 23�떆 �궗�씠�쓽 �떆媛꾩쓣 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int timesCount = DateTimeUtil.timesBetween(&quot;2005/01/01/ 11&quot;, &quot;2005/03/25 23&quot;, &quot;yyyy/MM/dd hh&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @param format
	 *            �떆媛곷뱾�쓣 �몴�쁽�븯�뒗 �룷硫� 臾몄옄�뿴.
	 * @return �몢 �떆媛� �궗�씠�쓽 '�떆媛�(time)'�쓽 李⑥씠.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int timesBetween(String from, String to, String format) throws java.text.ParseException {

		java.util.Date d1 = check(from, format);
		java.util.Date d2 = check(to, format);

		long duration = d2.getTime() - d1.getTime();

		return (int) (duration / (1000 * 60 * 60));
		// seconds in 1 day
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 '遺�(minute)'李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛꾩쓽 �몴�쁽�룷硫㏃� <code>format</code>�쓣 �궗�슜�븳�떎. <p<2005�뀈 1�썡1�씪 11�떆 10遺� 遺��꽣 2005�뀈3�썡25�씪 23�떆 59遺� �궗�씠�쓽 '遺�'�쓣 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int minCount = DateTimeUtil.minsBetween(&quot;2005/01/01 11:10&quot;, &quot;2005/03/25 23:59&quot;, &quot;yyyy/MM/dd hh:mm&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @param format
	 *            �떆媛곷뱾�쓣 �몴�쁽�븯�뒗 �룷硫� 臾몄옄�뿴.
	 * @return �몢 �떆媛� �궗�씠�쓽 '�떆媛�(time)'�쓽 李⑥씠.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int minsBetween(String from, String to, String format) throws java.text.ParseException {

		java.util.Date d1 = check(from, format);
		java.util.Date d2 = check(to, format);

		long duration = d2.getTime() - d1.getTime();

		return (int) (duration / (1000 * 60));
		// seconds in 1 day
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 �떆媛꾩감�씠瑜� 'hh�떆媛� mm遺�' �씠�� 臾몄옄�뿴濡� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽 �룷硫㏃� <code>format</code>�쓣 �궗�슜�븳�떎. 2005�뀈 1�썡1�씪 11�떆 10遺� 遺��꽣 2005�뀈3�썡25�씪 23�떆 59遺� �궗�씠�쓽 �떆媛꾩감瑜� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String timeGap = DateTimeUtil.timesBetweenStr(&quot;2005/01/01 11:10&quot;, &quot;2005/03/25 23:59&quot;, &quot;yyyy/MM/dd hh:mm&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @param format
	 *            �떆媛곷뱾�쓣 �몴�쁽�븯�뒗 �룷硫� 臾몄옄�뿴.
	 * @return �몢 �궇吏� �궗�씠�쓽 �떆媛꾩감�씠瑜� �몴�쁽�븯�뒗 'hh�떆媛� mm遺�'�씠�� �삎�깭�쓽 臾몄옄�뿴.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String timesBetweenStr(String from, String to, String format) throws java.text.ParseException {

		int min = minsBetween(from, to, format);
		if (min < 0) {
			throw new RuntimeException("鍮꾧탳�떆媛� 寃곌낵媛� �쓬�닔 �엯�땲�떎");
		}
		int time = min / 60;
		min = min % 60;
		return new String(time + "�떆媛� " + min + "遺�");
		// seconds in 1 day
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 媛쒖썡�닔 李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽 �룷硫㏃� <code>"yyyyMMdd"</code>瑜� �궗�슜�븳�떎. 2005�뀈 1�썡1�씪遺��꽣 2005�뀈3�썡25�씪 �궗�씠�쓽 媛쒖썡�닔 李� 瑜� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int monthGap = DateTimeUtil.monthsBetween(&quot;20050101&quot;, &quot;20050325&quot;);
	 * 
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @return �몢 �궇吏� �궗�씠�쓽 媛쒖썡�닔 李⑥씠
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int monthsBetween(String from, String to) throws java.text.ParseException {
		return monthsBetween(from, to, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 媛쒖썡�닔 李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽 �룷硫㏃� <code>format</code>�쓣 �궗�슜�븳�떎. 2005�뀈 1�썡1�씪 11�떆 10遺� 遺��꽣 2005�뀈3�썡25�씪 23�떆 59遺� �궗�씠�쓽 媛쒖썡�닔 李� 瑜� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int monthGap = DateTimeUtil.monthsBetween(&quot;2005/01/01 11:10&quot;, &quot;2005/03/25 23:59&quot;, &quot;yyyy/MM/dd hh:mm&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @param format
	 *            �떆媛곷뱾�쓣 �몴�쁽�븯�뒗 �룷硫� 臾몄옄�뿴.
	 * @return �몢 �궇吏� �궗�씠�쓽 媛쒖썡�닔 李⑥씠
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int monthsBetween(String from, String to, String format) throws java.text.ParseException {
		java.util.Date fromDate = check(from, format);
		java.util.Date toDate = check(to, format);

		// if two date are same, return 0.
		if (fromDate.compareTo(toDate) == 0)
			return 0;

		java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
		java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
		java.text.SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);

		int fromYear = Integer.parseInt(yearFormat.format(fromDate));
		int toYear = Integer.parseInt(yearFormat.format(toDate));
		int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
		int toMonth = Integer.parseInt(monthFormat.format(toDate));
		int fromDay = Integer.parseInt(dayFormat.format(fromDate));
		int toDay = Integer.parseInt(dayFormat.format(toDate));

		int result = 0;
		result += ((toYear - fromYear) * 12);
		result += (toMonth - fromMonth);

		// if (((toDay - fromDay) < 0) ) result += fromDate.compareTo(toDate);
		// ceil怨� floor�쓽 �슚怨�
		if (((toDay - fromDay) > 0))
			result += toDate.compareTo(fromDate);

		return result;
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 �뀈�룄 李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽 �룷硫㏃� "yyyyMMdd"�씠�떎.
	 * 
	 * <p>
	 * 1975�뀈 2�썡 5�씪 �깭�뼱�궃 �궗�엺�쓽 �쁽�옱 留� �굹�씠瑜� 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int age = DateTimeUtil.ageBetween(&quot;19750205&quot;, getFormatString(&quot;yyyyMMdd&quot;));
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @return �몢 �궇吏� �궗�씠�쓽 �뀈�룄 李⑥씠(�굹�씠)瑜� 由ы꽩�븳�떎.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int ageBetween(String from, String to) throws java.text.ParseException {
		return ageBetween(from, to, "yyyyMMdd");
	}

	/**
	 * �쁽�옱 �떆媛곸쓣 湲곗��쑝濡� �씤�옄濡� �쟾�떖�맂 �궇吏� �궗�씠�쓽 �뿰�룄 李⑥씠瑜� 由ы꽩�븳�떎. �궇吏쒖쓽 �몴�쁽 �룷硫㏃� "yyyyMMdd"�씠�떎. return age between two date strings with default defined format.("yyyyMMdd")
	 *<p>
	 * 1975�뀈 2�썡 5�씪 �깭�뼱�궃 �궗�엺�쓽 �쁽�옱 留� �굹�씠瑜� 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int age = DateTimeUtil.age(&quot;19750205&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param birth
	 *            鍮꾧탳�븯怨좎옄 �븯�뒗 湲곗��씠 �릺�뒗 �궇吏�
	 * @return �쁽�옱 �궇吏쒖� birth �궗�씠�쓽 �뀈�룄 李⑥씠瑜� 由ы꽩�븳�떎.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd'</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 * @see #ageBetween(String, String)
	 */
	public static int age(String birth) throws java.text.ParseException {
		return ageBetween(birth, getFormatString("yyyyMMdd"), "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 <code>from</code> �떆媛곴낵 <code>to</code> �떆媛� �궗�씠�쓽 �뀈�룄 李⑥씠瑜� 由ы꽩�븳�떎. �몢 �떆媛곸쓽 �몴�쁽 �룷硫㏃�<code>format</code>�쓣 �궗�슜�븳�떎.
	 * <p>
	 * 1975�뀈 2�썡 5�씪 �깭�뼱�궃 �궗�엺�쓽 �쁽�옱 留� �굹�씠瑜� 援ы븯�뒗 肄붾뱶:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int age = DateTimeUtil.ageBetween(&quot;19750205&quot;, getFormatString(&quot;yyyyMMdd&quot;), &quot;yyyyMMdd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param from
	 *            date string
	 * @param to
	 *            date string
	 * @param format
	 *            �궇吏쒕�� �몴�쁽�븷 �븣 �궗�슜�븷 �몴�쁽 �룷硫㏃쓣 �굹���궡�뒗 臾몄옄�뿴.
	 * @return �몢 �궇吏� �궗�씠�쓽 �뀈�룄 李⑥씠瑜� 由ы꽩�븳�떎.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static int ageBetween(String from, String to, String format) throws java.text.ParseException {
		return (int) (daysBetween(from, to, format) / 365);
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>s</code>瑜� 湲곗��쑝濡� �듅�젙 �씪(day) �닔瑜� �뜑�븳 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎. �궇吏쒖쓽 �룷�쁽 �룷硫㏃� "yyyyMMdd"瑜� �궗�슜�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 25�씪�뿉�꽌 �씪二쇱씪(7�씪) �썑�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(&quot;20050225&quot;, 7);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param day
	 *            �뜑�븷 �씪�닔
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addDays(String s, int day) throws java.text.ParseException {
		return addDays(s, day, "yyyyMMdd");
	}

	/**
	 * �삤�뒛�쓣 湲곗��쑝濡� �듅�젙 �씪(day) �닔瑜� �뜑�븳 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎. �궇吏쒖쓽 �룷�쁽 �룷硫㏃� "yyyyMMdd"瑜� �궗�슜�븳�떎.
	 * <p>
	 * �삤�뒛�쓣 湲곗��쑝濡� �씪二쇱씪(7�씪) �썑�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(7);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param day
	 *            �뜑�븷 �씪�닔
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addDays(int day) throws java.text.ParseException {
		return addDays(getShortDateString(), day, "yyyyMMdd");
	}

	/**
	 * �삤�뒛湲곗��쑝濡� �듅�젙 �씪(day) �닔瑜� �뜑�븳 �궇吏쒕�� 援ы븳 �썑 �씤�옄濡� �쟾�떖�맂 <code>format</code> �삎�떇�쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * <p>
	 * �삤�뒛�쓣 湲곗��쑝濡� �씪二쇱씪(7�씪) �썑�쓽 �궇吏쒕�� "yyyy/MM/dd" �삎�떇�쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(7, &quot;yyyy/MM/dd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param day
	 *            �뜑�븷 �씪�닔
	 * @param format
	 *            �궇吏� �몴�쁽 �룷硫�
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addDays(int day, String format) throws java.text.ParseException {
		String today = getShortDateString();
		String tmp = addDays(today, day, "yyyyMMdd");
		return changeFormat(tmp, "yyyyMMdd", format);
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>s</code> �뿉�꽌 �듅�젙 �씪(day) �닔瑜� �뜑�븳 �궇吏쒕�� �씤�옄濡� �쟾�떖�맂 <code>format</code> �삎�떇�쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 25�씪�뿉�꽌 �씪二쇱씪(7�씪) �썑�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(&quot;20050225&quot;, 7, &quot;yyyyMMdd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param day
	 *            �뜑�븷 �씪�닔
	 * @param format
	 *            �궇吏� �몴�쁽 �룷硫�
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addDays(String s, int day, String format) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;

		try {
			date = check(s, format);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		date.setTime(date.getTime() + ((long) day * 1000 * 60 * 60 * 24));
		return formatter.format(date);
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �떆媛� <code>s</code> �뿉�꽌 �듅�젙 �떆媛�(time)�쓣 �뜑�븳 �떆媛곸쓣 �씤�옄濡� �쟾�떖�맂 <code>format</code> �삎�떇�쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 25�씪�뿉�꽌 �씪二쇱씪(7�씪) �썑�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(&quot;20050225&quot;, 7, &quot;yyyyMMdd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param time
	 *            �뜑�븷 �떆媛�
	 * @param format
	 *            �궇吏� �몴�쁽 �룷硫�
	 * @return �뜑�빐吏� �떆媛곸쓣 �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addTimes(String s, int time, String format) throws java.text.ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check(s, format);

		date.setTime(date.getTime() + ((long) 1000 * 60 * 60 * time));
		return formatter.format(date);
	}

	/**
	 * �쁽�옱�떆媛� �뿉�꽌 �듅�젙 �떆媛�(time)�쓣 �뜑�븳 �떆媛곸쓣 �씤�옄濡� �쟾�떖�맂 <code>format</code> �삎�떇�쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * <p>
	 * �쁽�옱�떆媛곸뿉�꽌 23�떆媛� �썑瑜� �몴�쁽�븯�뒗 臾몄옄�뿴 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(23, &quot;yyyy/MM/dd hh:mm&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param time
	 *            �뜑�븷 �떆媛�
	 * @param format
	 *            �떆媛� �몴�쁽 �룷硫�
	 * @return �뜑�빐吏� �떆媛곸쓣 �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addTimes(int time, String format) throws java.text.ParseException {
		String fomatted = getFormatString(format);
		return addTimes(fomatted, time, format);
	}

	/**
	 * �꽭怨꾪몴以��떆(Universal Time Coordinated)瑜� "yyyy-MM-ddTHH:mm:ss:SSSZ" �삎�깭�쓽 �룷硫㏃쑝濡� 由ы꽩�븳�떎.
	 * 
	 * @return UTC time
	 */
	public static String getUTCTimeString() {
		String ret = "";
		try {
			ret = addTimes(-9, "yyyy-MM-dd HH:mm:ss:SSS ");
			char rets[] = ret.toCharArray();
			rets[10] = 'T';
			rets[23] = 'Z';
			ret = new String(rets);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>s</code>瑜� 湲곗��쑝濡� �듅�젙 媛쒖썡(month) �닔瑜� �뜑�븳 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎. �궇吏쒖쓽 �룷�쁽 �룷硫㏃� "yyyyMMdd"瑜� �궗�슜�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 25�씪�뿉�꽌 7媛쒖썡 �쟾�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String monthString = DateTimeUtil.addDays(&quot;20050225&quot;, -7);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param month
	 *            �뜑�븷 媛쒖썡�닔
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addMonths(String s, int month) throws ParseException {
		return addMonths(s, month, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �떆媛� <code>s</code> �뿉�꽌 �듅�젙 媛쒖썡(month)�쓣 �뜑�븳 �떆媛곸쓣 �씤�옄濡� �쟾�떖�맂 <code>format</code> �삎�떇�쑝濡� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 25�씪�뿉�꽌 7媛쒖썡 �썑�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String dateString = DateTimeUtil.addDays(&quot;20050225&quot;, 7, &quot;yyyyMMdd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param addMonth
	 *            �뜑�븷 媛쒖썡
	 * @param format
	 *            �궇吏� �몴�쁽 �룷硫�
	 * @return �뜑�빐吏� �떆媛곸쓣 �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addMonths(String s, int addMonth, String format) throws ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check(s, format);

		java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
		java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
		java.text.SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);
		int year = Integer.parseInt(yearFormat.format(date));
		int month = Integer.parseInt(monthFormat.format(date));
		int day = Integer.parseInt(dayFormat.format(date));

		month += addMonth;
		if (addMonth > 0) {
			while (month > 12) {
				month -= 12;
				year += 1;
			}
		} else {
			while (month <= 0) {
				month += 12;
				year -= 1;
			}
		}
		java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
		java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
		String tempDate = String.valueOf(fourDf.format(year)) + String.valueOf(twoDf.format(month)) + String.valueOf(twoDf.format(day));
		java.util.Date targetDate = null;

		try {
			targetDate = check(tempDate, "yyyyMMdd");
		} catch (java.text.ParseException pe) {
			day = lastDay(year, month);
			tempDate = String.valueOf(fourDf.format(year)) + String.valueOf(twoDf.format(month)) + String.valueOf(twoDf.format(day));
			targetDate = check(tempDate, "yyyyMMdd");
		}

		return formatter.format(targetDate);
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>s</code>瑜� 湲곗��쑝濡� �듅�젙 �뿰�룄(year) �닔瑜� �뜑�븳 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎. �궇吏쒖쓽 �룷�쁽 �룷硫㏃� "yyyyMMdd"瑜� �궗�슜�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 28�씪�뿉�꽌 3�뀈 �쟾�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String yearString = DateTimeUtil.addDays(&quot;20050228&quot;, -3);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param year
	 *            �뜑�븷 �뀈�닔
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addYears(String s, int year) throws java.text.ParseException {
		return addYears(s, year, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>s</code>瑜� 湲곗��쑝濡� �듅�젙 �뿰�룄(year) �닔瑜� �뜑�븳 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 由ы꽩�븳�떎. �궇吏쒖쓽 �룷�쁽 �룷硫㏃� <code>format</code>瑜� �궗�슜�븳�떎.
	 * <p>
	 * 2005�뀈 2�썡 28�씪�뿉�꽌 3�뀈 �쟾�쓽 �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�쓣 �뼸�뼱�삤�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String yearString = DateTimeUtil.addDays(&quot;20050228&quot;, -3, &quot;yyyyMMdd&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param s
	 *            湲곗� �궇吏�
	 * @param year
	 *            �뜑�븷 �뀈�닔
	 * @param format
	 *            �궇吏쒕�� �몴�쁽�븯�뒗 �룷�쁽 �룷硫�
	 * @return �뜑�빐吏� �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String addYears(String s, int year, String format) throws java.text.ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check(s, format);
		date.setTime(date.getTime() + ((long) year * 1000 * 60 * 60 * 24 * (365 + 1)));
		return formatter.format(date);
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>src</code>�뿉 �빐�떦�븯�뒗 �떖�쓽 留덉�留� �궇�쓣 �몴�쁽�븯�뒗 �궇吏쒕�� 由ы꽩�븳�떎. �궇吏� �몴�떆 �룷硫㏃쑝濡� "yyyyMMdd"�쓣 �궗�슜�븳�떎. 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String datesStr = DateTimeUtil.lastDayOfMonth(&quot;20050203&quot;);
	 * datesStr.equals(&quot;20050228&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param src
	 *            湲곗��씠 �릺�뒗 �궇吏�
	 * @return src�뿉 �궇吏쒖쨷 洹� �떖�쓽 留덉�留� �궇�쓣 �몴�떆�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyyMMdd"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */

	public static String lastDayOfMonth(String src) throws java.text.ParseException {
		return lastDayOfMonth(src, "yyyyMMdd");
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏� <code>src</code>�뿉 �빐�떦�븯�뒗 �떖�쓽 留덉�留� �궇�쓣 �몴�쁽�븯�뒗 �궇吏쒕�� 由ы꽩�븳�떎. �궇吏� �몴�떆 �룷硫㏃쑝濡� <code>format</code>�쓣 �궗�슜�븳�떎. 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * String datesStr = DateTimeUtil.lastDayOfMonth(&quot;20050203&quot;, &quot;yyyyMMdd&quot;);
	 * datesStr.equals(&quot;20050228&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param src
	 *            湲곗��씠 �릺�뒗 �궇吏�
	 * @param format
	 *            �궇吏쒕�� �몴�떆�븯�뒗 �몴�떆�룷硫�
	 * @return src�뿉 �궇吏쒖쨷 洹� �떖�쓽 留덉�留� �궇�쓣 �몴�떆�븯�뒗 臾몄옄�뿴
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>format</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String lastDayOfMonth(String src, String format) throws java.text.ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check(src, format);

		java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
		java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);

		int year = Integer.parseInt(yearFormat.format(date));
		int month = Integer.parseInt(monthFormat.format(date));
		int day = lastDay(year, month);

		java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
		java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
		String tempDate = String.valueOf(fourDf.format(year)) + String.valueOf(twoDf.format(month)) + String.valueOf(twoDf.format(day));
		date = check(tempDate, format);

		return formatter.format(date);
	}

	/**
	 * �듅�젙 �뿰�룄 �듅�젙 �썡�쓽 �씪�닔瑜� 由ы꽩�븳�떎.
	 * <p>
	 * 2005 �뀈 2�썡�� 紐뉗씪�씠 議댁옱�븯�뒗吏� �솗�씤�븯�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * int days = DateTimeUtil.lastDay(2005, 2);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param year
	 *            �뀈�룄
	 * @param month
	 *            媛쒖썡
	 * @return 留덉�留� �씪�옄(�씪 �닔)
	 */
	private static int lastDay(int year, int month) {
		int day = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			if ((year % 4) == 0) {
				if ((year % 100) == 0 && (year % 400) != 0) {
					day = 28;
				} else {
					day = 29;
				}
			} else {
				day = 28;
			}
			break;
		default:
			day = 30;
		}
		return day;
	}

	/**
	 * �씤�옄濡� �쟾�떖�맂 �궇吏쒕�� �굹���궡�뒗 <code>s</code> 臾몄옄�뿴�씠 "yyyy/MM/dd HH:mm" �삎�떇�뿉 留욌뒗吏� �솗�씤�븳�떎.
	 * 
	 * @param s
	 *            �솗�씤�븯�젮�뒗 �궇吏쒕�� �굹���궡�뒗 臾몄옄�뿴
	 * @return �씤�옄濡� �쟾�떖�맂 �궇吏쒕�� �굹���궡�뒗 臾몄옄�뿴.
	 * @exception java.text.ParseException
	 *                �옒紐삳맂 �궇吏쒖씠嫄곕굹. �궇吏쒕�� �몴�쁽�븯�뒗 臾몄옄�뿴�씠 <code>"yyyy/MM/dd HH:mm"</code> �삎�떇�뿉 留욎� �븡�뒗 寃쎌슦.
	 */
	public static String checkDateTime(String s) throws ParseException {
		check(s, "yyyy/MM/dd HH:mm");
		return s;
	}

	/**
	 * 湲곗� �떆媛� (<code>checkTime</code>)�씠 �떆�옉�떆媛�(<code>startTime</code>) 怨� 醫낅즺�떆媛�( <code>endTime</code>) �궗�씠�뿉 �쐞移섑븯�뒗吏� �뿬遺�瑜� 由ы꽩�븳�떎. �씤�옄濡� �쟾�떖�릺�뒗 �떆媛곷뱾�� "HH:mm" �삉�뒗 "HH/mm" �삎�깭�쓽 �룷硫㏃씠�뼱�빞 �븳�떎.
	 * <p>
	 * �쁺�뾽�떆媛꾩씠 �삤�쟾 9�떆遺��꽣 18�떆 30遺꾧퉴吏� �씪 寃쎌슦 �쁽�옱 �떆媛꾩뿉 �쁺�뾽�씠 媛��뒫�븳 吏� �솗�씤�븯�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * boolean isOpen = DateTimeUtil.isMiddleTime(&quot;09:00&quot;, &quot;18:30&quot;, getFormatString(&quot;HH:mm&quot;));
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param startTime
	 *            �떆�옉 �떆媛� ("HH:mm" or "HH/mm" �룷硫�)
	 * @param endTime
	 *            醫낅즺 �떆媛� ("HH:mm" or "HH/mm" �룷硫�)
	 * @param checkTime
	 *            湲곗� �떆媛� ("HH:mm" or "HH/mm" �룷硫�)
	 * @return <tt>true</tt> 湲곗� �떆媛� (<code>checkTime</code>)�씠 �떆�옉�떆媛�( <code>startTime</code>) 怨� 醫낅즺�떆媛�(<code>endTime</code>) �궗�씠�뿉 �쐞移섑븳�떎.
	 * @throws java.text.ParseException
	 *             �씤�옄濡� �쟾�떖�맂 �떆媛곸씠 吏��젙�맂 �룷硫�("HH:mm" or "HH/mm" �뿉) 留욎� �븡嫄곕굹 �삱諛붾Ⅸ �떆媛꾩씠 �븘�땺寃쎌슦 諛쒖깮.
	 */
	public static boolean isMiddleTime(String startTime, String endTime, String checkTime) throws ParseException {
		Date a = getDateInstance(startTime);
		Date b = getDateInstance(endTime);
		Date c = getDateInstance(checkTime);
		return isMiddleTime(a, b, c);

	}

	/**
	 * 湲곗� �떆媛� (<code>checkTime</code>)�씠 �떆�옉�떆媛�(<code>startTime</code>) 怨� 醫낅즺�떆媛�( <code>endTime</code>) �궗�씠�뿉 �쐞移섑븯�뒗吏� �뿬遺�瑜� 由ы꽩�븳�떎.
	 * 
	 * @param startTime
	 *            �떆�옉 �떆媛�
	 * @param endTime
	 *            醫낅즺 �떆媛�
	 * @param checkTime
	 *            湲곗� �떆媛�
	 * @return <tt>true</tt> 湲곗� �떆媛� (<code>checkTime</code>)�씠 �떆�옉�떆媛�( <code>startTime</code>) 怨� 醫낅즺�떆媛�(<code>endTime</code>) �궗�씠�뿉 �쐞移섑븳�떎.
	 */
	public static boolean isMiddleTime(Date startTime, Date endTime, Date checkTime) {

		if (startTime.before(endTime)) {
			if (endTime.after(checkTime) && startTime.before(checkTime))
				return true;
		} else {
			if (endTime.after(checkTime) || startTime.before(checkTime)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �쁽�옱 �떆媛곸씠 �떆�옉�떆媛�(<code>startTime</code>) 怨� 醫낅즺�떆媛�(<code>endTime</code>) �궗�씠�뿉 �쐞移섑븯�뒗吏� �뿬遺�瑜� 由ы꽩�븳�떎. �씤�옄濡� �쟾�떖�릺�뒗 �떆媛곷뱾�� "HH:mm" �삉�뒗 "HH/mm" �삎�깭�쓽 �룷硫㏃씠�뼱�빞 �븳�떎.
	 * <p>
	 * �쁺�뾽�떆媛꾩씠 �삤�쟾 9�떆遺��꽣 18�떆 30遺꾧퉴吏� �씪 寃쎌슦 �쁽�옱 �떆媛꾩뿉 �쁺�뾽�씠 媛��뒫�븳 吏� �솗�씤�븯�뒗 肄붾뱶 �궗�슜�삁:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * boolean isOpen = DateTimeUtil.isMiddleTime(&quot;09:00&quot;, &quot;18:30&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param startTime
	 *            �떆�옉 �떆媛� ("HH:mm" or "HH/mm" �룷硫�)
	 * @param endTime
	 *            醫낅즺 �떆媛� ("HH:mm" or "HH/mm" �룷硫�)
	 * @return <tt>true</tt> �쁽�옱 �떆媛곸씠 �떆�옉 �떆媛�(<code>startTime</code>) 怨� 醫낅즺 �떆媛�( <code>endTime</code>) �궗�씠�뿉 �쐞移섑븳�떎.
	 * @throws java.text.ParseException
	 *             �씤�옄濡� �쟾�떖�맂 �떆媛곸씠 吏��젙�맂 �룷硫�("HH:mm" or "HH/mm" �뿉) 留욎� �븡嫄곕굹 �삱諛붾Ⅸ �떆媛꾩씠 �븘�땺寃쎌슦 諛쒖깮.
	 */
	public static boolean isMiddleTime(String startTime, String endTime) throws ParseException {

		String curTime = getFormatString("HH:mm");
		return isMiddleTime(startTime, endTime, curTime);

	}

	public static String getDateFormatStr(String sFormat) throws Exception {
		Calendar cal = Calendar.getInstance();
		Object objDate = cal.getTime();
		String sObj = objDate.toString();
		if (sObj.length() < 1) {
			return sObj;
		} else {
			SimpleDateFormat sdfDate = new SimpleDateFormat(sFormat);
			StringBuffer sbDate = new StringBuffer();
			sdfDate.format(objDate, sbDate, new FieldPosition(0));
			return sbDate.toString();
		}
	}

	public static String getDateFormatStr(String sFormat, int addDay) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(5, addDay);
		Object objDate = cal.getTime();
		String sObj = objDate.toString();
		if (sObj.length() < 1) {
			return sObj;
		} else {
			SimpleDateFormat sdfDate = new SimpleDateFormat(sFormat);
			StringBuffer sbDate = new StringBuffer();
			sdfDate.format(objDate, sbDate, new FieldPosition(0));
			return sbDate.toString();
		}
	}

	public static String getDateFormat(Date date, String format) {
		return getDateFormat(date, format, Locale.KOREA);
	}

	public static String getDateFormat(Date date, String format, Locale locale) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
		try {
			return formatter.format(date);
		} catch (Exception ex) {
			return "";
		}
	}

	public static void main(String[] args) throws ParseException {
		System.out.println("�씪:"+DateUtil.getDay());
		System.out.println("�떆:"+DateUtil.getHourHH());
		System.out.println("�떆:"+DateUtil.getDate());
		System.out.println("�쁽�옱:"+DateUtil.getSysDate());

	}
	
}
