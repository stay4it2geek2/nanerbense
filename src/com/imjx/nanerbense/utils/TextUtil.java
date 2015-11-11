package com.imjx.nanerbense.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.net.ParseException;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 文字处理判断工具类
 * 
 * @author linwm
 */
public class TextUtil {
	/**
	 * 判断字符串是否是空值
	 * 
	 * @param source
	 * @return 字符串是否是空值?true:false
	 */
	public static boolean isEmptyString(String source) {
		if (source == null) {
			return true;
		} else if (source.length() == 0) {
			return true;
		} else if ("".equals(source)) {
			return true;
		}
		return false;
	}

	public static boolean close(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 得到InputMethodManager的实例
		if (imm.isActive()) {
			// 如果开启
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
		return false;
	}

	/**
	 * 判断电话号码是否符合规范或合法
	 * 
	 * @param numberString
	 * @return
	 */
	public static boolean isPhoneNumber(String numberString) {
		boolean isNumber = false;
		if (!numberString.equals("")) {
			if (numberString.length() == 11
					&& (isNumber(numberString))
					&& (numberString.startsWith("13")
							|| numberString.startsWith("18")
							|| numberString.startsWith("15") || numberString
								.startsWith("14"))) {
				isNumber = true;
			}
		}

		return isNumber;
	}

	/**
	 * 判断是否是0-9的阿拉伯数字
	 * 
	 * @param numberString
	 * @return
	 */
	public static boolean isNumber(String numberString) {
		return numberString.matches("^[0-9]*$");
	}

	/**
	 * 取得双精度浮点型的数值
	 * 
	 * @param floatString
	 * @return
	 */
	public static float getFloat(String floatString) {
		float number = 0;
		try {
			number = Float.parseFloat(floatString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return number;
	}

	/**
	 * 取得整型的数值
	 * 
	 * @param intString
	 * @return
	 */
	public static int getInt(String intString) {
		int number = 0;
		try {
			number = Integer.parseInt(intString.trim());
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return number;
	}

	/**
	 * 取得单精度浮点型数值
	 * 
	 * @param doubleString
	 * @return
	 */
	public static Double getDouble(String doubleString) {
		Double number = 0d;
		try {
			number = Double.parseDouble(doubleString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return number;
	}

	public static String getTime(String user_time) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d;
		try {
			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return re_time;
	}
}
