package com.bankcomm.framework.utils;

import android.widget.EditText;

import com.bankcomm.framework.log.AriesLog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 通用方法封装
 * 
 * @Description
 * @Author lewis(lgs@yitong.com.cn) 2014-3-3 下午1:25:28
 * @Class CommonUtil Copyright (c) 2014 Shanghai P&C Information Technology
 *        Co.,Ltd. All rights reserved.
 */
public class CommonUtil {
	static DecimalFormat fmt = new DecimalFormat("##,###,###,##0.00");

	/**
	 * 对数字字符串进行四舍五入处理
	 * 
	 * @param str
	 *            处理参数
	 * @param scale
	 *            保留小数位数
	 * @return 返回值
	 */
	public static String RoundOf(String str, int scale) {
		// 输入精度小于0则抛出异常
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		// 取得数值
		BigDecimal b = new BigDecimal(str);
		// 取得数值1
		BigDecimal one = new BigDecimal("1");
		// 原始值除以1，保留scale位小数，进行四舍五入
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
	}



	/**
	 * 对数字进行四舍五入处理
	 *
	 *            处理参数
	 * @param num
	 *            保留小数位数
	 *
	 * @param isMakeup
	 *            如果小数点后面的位数小于要保留小数点位数，是否补0
	 * @return 返回值
	 */
	public static String RoundOf(String value, int num, boolean isMakeup) {
		Float a= Float.parseFloat(value.trim())/100;
		value=a+"";
//		return fmt.format(value);
		String str = value;
		str = RoundOf(str, num);
		// 取得小数点后面的数字字符串
		String str1 = str.substring(str.indexOf(".") + 1,
				str.length()); // 如果小数点后面的位数小于要保留小数点位数
		if (str1.length() < num) { if
				(isMakeup) { for (int n = 0; n < (num - str1.length()); n++) { str =
				str + "0"; } } }

		return str;
	}

	/**
	 * 对数字进行四舍五入处理
	 * 
	 * 
	 * @param num
	 *            保留小数位数
	 * 
	 * @param isMakeup
	 *            如果小数点后面的位数小于要保留小数点位数，是否补0
	 * @return 返回值
	 */
	public static String RoundOf(double value, int num, boolean isMakeup) {
		return fmt.format(value);
		/*
		 * String str = value + ""; // 对数字字符串进行四舍五入处理 str = RoundOf(str, num);
		 * 
		 * // 取得小数点后面的数字字符串 String str1 = str.substring(str.indexOf(".") + 1,
		 * str.length()); // 如果小数点后面的位数小于要保留小数点位数 if (str1.length() < num) { if
		 * (isMakeup) { for (int n = 0; n < (num - str1.length()); n++) { str =
		 * str + "0"; } } }
		 * 
		 * return str;
		 */
	}

	public static void formatMoneyEditText(EditText txtSaveAmt, int intLength) {
		String strOriValue = txtSaveAmt.getText().toString().replace(",", "");
		if (strOriValue.indexOf(".") == 0) {
			strOriValue = "0" + strOriValue;
		}

		String strBeforeInteger = "";
		String strBeforeTail = "";

		if (strOriValue.indexOf(".") > 0) {
			strBeforeInteger = strOriValue.substring(0, strOriValue.indexOf("."));
			strBeforeTail = strOriValue.substring(strOriValue.indexOf("."), strOriValue.length());
		} else {
			strBeforeInteger = strOriValue;
		}

		if (strBeforeInteger.length() > intLength) {
			strBeforeInteger = strBeforeInteger.substring(0, intLength);
		} else if (strBeforeInteger.length() > 1) {
			strBeforeInteger = Long.parseLong(strBeforeInteger) + "";
		}

		if (strBeforeTail.length() > 3) {
			strBeforeTail = strBeforeTail.substring(0, 3);
		}

		// 将整数位和小数位进行拼接
		if (strOriValue.indexOf(".") > 0) {
			strOriValue = strBeforeInteger + strBeforeTail;
		} else {
			strOriValue = strBeforeInteger;
		}

		String afterMoney = moneyCommaEdit(strOriValue);
		AriesLog.i("txtSaveAmt", afterMoney + " " + txtSaveAmt.getText().toString().trim());
		if (!afterMoney.equals(txtSaveAmt.getText().toString().trim())) {
			txtSaveAmt.setText(afterMoney);
			txtSaveAmt.requestFocus();
			txtSaveAmt.setSelection(txtSaveAmt.getText().toString().length());
		}
	}

	/**
	 * 金额格式化，加逗号分割
	 * 
	 * @param strBefore
	 * @return
	 */
	public static String moneyCommaEdit(String strBefore) {
		strBefore = strBefore.replaceAll(",", "");

		// 将小数位和整数位进行分割
		String strBeforeInteger = "";
		String strBeforeTail = "";
		if (strBefore.indexOf(".") > 0) {
			strBeforeInteger = strBefore.substring(0, strBefore.indexOf("."));
			strBeforeTail = strBefore.substring(strBefore.indexOf("."), strBefore.length());
		} else {
			strBeforeInteger = strBefore;
		}

		String strAfterNum = "";
		int iSize = 0;
		int iCount = 0;

		iSize = strBeforeInteger.length();
		// 整数位大于3位
		if (iSize > 3) {
			for (int i = 1; i <= ((iSize - 1) / 3); i++) {
				strAfterNum = "," + strBeforeInteger.substring((iSize - 3 * i), (iSize - 3 * i) + 3) + strAfterNum;
				iCount++;
			}

			strAfterNum = strBeforeInteger.substring(0, iSize - 3 * (iCount)) + strAfterNum;
			// 小雨3位
		} else {
			strAfterNum = strBeforeInteger;
		}

		// 将整数位和小数位进行拼接
		if (strBefore.indexOf(".") > 0) {
			strAfterNum = strAfterNum + strBeforeTail;
		}

		return strAfterNum;
	}

	/**
	 * 取得金额输入框的数值
	 * 
	 * @param txtSaveAmt
	 * @return
	 */
	public static String getMoneyByEditText(EditText txtSaveAmt) {
		String amt = txtSaveAmt.getText().toString().replace(",", "").trim();
		if (amt.length() == 0) {
			amt = "0";
		}
		return amt;
	}


	/**
	 * 对给定数目的自0开始步长为1的数字序列进行乱序
	 * 
	 * @param no
	 *            给定数目
	 * @return 乱序后的数组
	 */
	public static String[] getSequence(String[] no) {
		String[] sequence = no;
		Random random = new Random();
		
		for (int i = 0; i < sequence.length; i++) {
			int p = random.nextInt(sequence.length);
			String tmp = sequence[i];
			sequence[i] = sequence[p];
			sequence[p] = tmp;
		}
		random = null;
		return sequence;
	}

	public static Boolean getAmount(String a1,String a2) {
		double d1=Double.parseDouble(a1);
		double d2=Double.parseDouble(a2);
		if (d1>d2) {
			return false;
		}
		return true;
	}
}
