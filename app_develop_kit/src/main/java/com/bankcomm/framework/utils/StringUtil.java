package com.bankcomm.framework.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Patterns;
import android.webkit.URLUtil;

import com.bankcomm.framework.log.AriesLog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * String操作工具类
 *
 * @Description
 * @Author lewis(lgs@yitong.com.cn) 2014-1-17 下午3:51:53
 * @Class StringTools Copyright (c) 2014 Shanghai P&C Information Technology
 * Co.,Ltd. All rights reserved.
 */
public class StringUtil {

    private static DecimalFormat fmt = new DecimalFormat("##,###,###,##0.00");

    private StringUtil() {
        throw new InstantiationError("工具类无法实例化");
    }

    /**
     * 统计字符串长度,一个双字节字符长度计2，ASCII字符计1
     *
     * @param str 字符串
     * @author 刘国山
     */
    public static int getLength(String str) {
        return str.replaceAll("[^\\x00-\\xff]", "aa").length();
    }

    public static String getStringByBytes(byte[] bs) {
        return new String(bs);
    }

    /**
     * 判断字符串是否为空，即为null或""
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 判断字符串是否不为空，即不为null且不为""
     */
    public static boolean isNotEmpty(String str) {
        return (!(isEmpty(str)));
    }

    /**
     * 判断字符串是否为空白，即为null或为" "
     */
    public static boolean isBlank(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * 判断字符串是否不为空白，即不为null且不为" "
     */
    public static boolean isNotBlank(String str) {
        return (!(isBlank(str)));
    }

    /**
     * 生成长度为5到10的随机字符串. 随机字符串的内容包含[1-9 A-Z a-z]的字符.
     *
     * @return 随机字符串.
     */
    public static String buildRandomString() {
        return buildRandomString(5, 10);
    }

    /**
     * 生成随机字符串. 随机字符串的内容包含[1-9 A-Z a-z]的字符.
     *
     * @param length 必须为正整数 随机字符串的长度
     * @return 随机字符串.
     */
    public static synchronized String buildRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length只能是正整数!");
        }
        Random random = new Random();
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < length; i++) {
            ret.append(CHAR_RANDOMS[random.nextInt(CHAR_RANDOMS.length)]);
        }
        random = null;
        return ret.toString();
    }

    /**
     * /** 生成随机字符串. 随机字符串的内容包含[1-9 A-Z a-z]的字符.
     *
     * @param min 必须为正整数 随机字符串的最小长度
     * @param max 必须为正整数 随机字符串的最大长度
     * @return 随机字符串.
     */
    public static synchronized String buildRandomString(int min, int max) {
        if (min <= 0) {
            throw new IllegalArgumentException("Min 只能是正整数!");
        } else if (max <= 0) {
            throw new IllegalArgumentException("Max 只能是正整数!");
        } else if (min > max) {
            throw new IllegalArgumentException("Min 必须小于或等于 Max!");
        }
        Random random = new Random();
        int length = random.nextInt(max - min + 1) + min;
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < length; i++) {
            ret.append(CHAR_RANDOMS[random.nextInt(CHAR_RANDOMS.length)]);
        }
        random = null;
        return ret.toString();
    }

    /**
     * 截取固定超出长度str 结尾...
     *
     * @param str    需要截图的string
     * @param length 长度
     * @return
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2013-7-3
     */
    public static String subStringLength(String str, int length) {
        if (isNotEmpty(str) && str.length() > length) {
            str = str.substring(0, length) + "...";
        }
        return str;
    }

    private static final char[] CHAR_RANDOMS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z'};

    /**
     * 字符串转为int
     *
     * @param str
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-7-24
     */
    public static int StringToInt(String str) {
        int result = 0;
        if (null == str) {
            return 0;
        }
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按默认格式格式化字符串
     *
     * @param str
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-7-24
     */
    public static String parseFormatStr(String str) {
        String result = "";
        if (isNotEmpty(str)) {
            try {
                result = "" + fmt.parse(str);
            } catch (ParseException e) {
                AriesLog.e("TAG", "parse the double error!", e);
            }
        }
        return result;
    }

    /**
     * 按指定格式格式化字符串
     *
     * @param str
     * @param df
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-7-24
     */
    public static String parseFormatStr(String str, DecimalFormat df) {
        String result = "";
        try {
            result = "" + df.parse(str);
        } catch (ParseException e) {
        }
        return result;
    }

    /**
     * 将double按默认格式格式化为字符串
     *
     * @param str
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-7-24
     */
    public static String formateStr(Double str) {
        return fmt.format(str);
    }

    /**
     * 登录检测输入的手机号
     *
     * @param phoneNum
     * @return
     */
    public static boolean checkPhoneNum(String phoneNum) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }

    /**
     * 将html占位符转化为正常字符
     *
     * @param str
     * @return
     */
    public static String Repex(String str) {
        String inputstr = str;
        String temptext = "";
        if (null != inputstr && !"".equals(inputstr)) {
            if (inputstr.indexOf("&amp;") > -1) {
                inputstr = inputstr.replaceAll("&amp;", "&");
            }
            if (inputstr.indexOf("&lt;") > -1) {
                inputstr = inputstr.replaceAll("&lt;", "<");
            }
            if (inputstr.indexOf("&gt;") > -1) {
                inputstr = inputstr.replaceAll("&gt;", ">");
            }
            if (inputstr.indexOf("&apos;") > -1) {
                inputstr = inputstr.replaceAll("&apos;", "'");
            }
            if (inputstr.indexOf("&quot;") > -1) {
                inputstr = inputstr.replaceAll("&quot;", "\"");
            }
            if (inputstr.indexOf("&#034;") > -1) {
                inputstr = inputstr.replaceAll("&#034;", "\"");
            }
            temptext = inputstr;
        }
        return temptext;
    }

    /**
     * 检查链接是否有效
     *
     * @param input
     * @return true:有效，false:无效
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-9-12
     */
    public static boolean checkURL(CharSequence input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        Pattern URL_PATTERN = Patterns.WEB_URL;
        boolean isURL = URL_PATTERN.matcher(input).matches();
        if (!isURL) {
            String urlString = input + "";
            if (URLUtil.isNetworkUrl(urlString)) {
                try {
                    new URL(urlString);
                    isURL = true;
                } catch (Exception e) {
                }
            }
        }
        return isURL;
    }

    /**
     * 输入流转换为String对象
     *
     * @param is
     * @return
     * @throws IOException
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-6-5
     */
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    /**
     * 检查文件是否为图片 png、jpg
     *
     * @param path
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-6-29
     */
    public static boolean checkUrlIsImgFile(String path) {
        boolean isImgFile = false;
        // 获取扩展名
        String fileEnd = path.substring(path.lastIndexOf(".") + 1, path.length()).toLowerCase();
        if (fileEnd.equals("png") || fileEnd.equals("jpg")) {
            isImgFile = true;
        } else {
            isImgFile = false;
        }
        return isImgFile;
    }

    /**
     * 检查文件是否为pdf
     *
     * @param path
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-6-29
     */
    public static boolean checkUrlIsPdfFile(String path) {
        boolean isPdfFile = false;
        // 获取扩展名
        String fileEnd = path.substring(path.lastIndexOf(".") + 1, path.length()).toLowerCase();
        if (fileEnd.equals("pdf")) {
            isPdfFile = true;
        } else {
            isPdfFile = false;
        }
        return isPdfFile;
    }

    /**
     * 检查文件是否为视屏 mp4、3gp
     *
     * @param path
     * @return
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-6-29
     */
    public static boolean checkUrlIsVideoFile(String path) {
        boolean isVideoFile = false;
        // 获取扩展名
        String fileEnd = path.substring(path.lastIndexOf(".") + 1, path.length()).toLowerCase();
        if (fileEnd.equals("mp4") || fileEnd.equals("3gp")) {
            isVideoFile = true;
        } else {
            isVideoFile = false;
        }
        return isVideoFile;
    }


    /**
     * 获取16个字节时间戳
     *
     * @return
     * @throws UnsupportedEncodingException
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-11
     */
    public static String getTimestamp16() throws UnsupportedEncodingException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
        String formateDate = sdf.format(d);
        byte[] a = formateDate.getBytes("utf-8");
        byte[] b = new byte[16];
        System.arraycopy(a, 0, b, 0, 16);
        return new String(b);
    }

    /**
     * 获取16个字节时间戳
     *
     * @return
     * @throws UnsupportedEncodingException
     * @Description
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-11
     */
    public static String getEmpty16() throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 15; i++) {
            buffer.append(" ");
        }
        return buffer.toString();
    }

    //判断字符串是否仅为数字
    public static boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");

        return !pattern.matcher(str).matches();

    }

    public static byte[] decode(String src) {
        byte[] dst = new byte[src.length() / 2];
        for (int i = 0; i < dst.length; i++)
            dst[i] = (byte) Integer.parseInt(src.substring(i * 2, i * 2 + 2),
                    16);
        return dst;
    }


    /*******
     * 判断字符串是否包含字母
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        boolean isLetter = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {//用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }

        return isLetter;
    }

    public static String money(String str) {
        if (str.equals("")) {
            str = "0";
        }
        double b = Double.valueOf(str);
        b = b / 100;
        DecimalFormat df = new DecimalFormat("#.00");
        return "" + df.format(b);
    }

    public static String moneyput(String str) {
        if (null == str || "".equals(str)) {
            return "0";
        }
        int a = (int) returnThNumber(str);
        return "" + a;
    }

    /* public static String cardsPut(String str,String cardNo) {
         String a=str+"  "+"尾号"+"  "+cardNo.subSequence(cardNo.length()-4, cardNo.length());
           return a;
       }*/
    //返回收款人和付款人信息格式：银行卡信息、尾号、姓,
    public static String cradsGet(String str, String argx, String cardNo, String name) {
        String a = "";
        if (str != null && argx != null && cardNo != null && name != null) {
            String cardType;
            if (argx.equals("0") || argx.equals("2")) {
                cardType = "信用卡";
            } else if (argx.equals("1") || argx.equals("3")) {
                cardType = "借记卡";
            } else {
                cardType = "   ";
            }
            String name_bank = name.subSequence(0, 1).toString();
            if (!isEmpty(name)) {
                int n = name.length() - 1;
                if (n < 4) {
                    for (int k = name.length() - 1; k > 0; k--) {
                        name_bank += "*";
                    }
                } else {
                    for (int k = 3; k > 0; k--) {
                        name_bank += "*";
                    }
                }
            } else {
                name_bank = "";
            }
            if (str.length() > 4) {
                str = str.substring(0, 4) + "...";
            }
            a = str + cardType + " " + "尾号" + "  " + cardNo.subSequence(cardNo.length() - 4, cardNo.length()) + "  " + name_bank;
        }
        return a;
    }

    public static String cradsGet(String str, String argx, String cardNo) {
        String cardType;
//		  int i=Integer.parseInt(argx);
        if (str == null) {
            str = "";
        }
        if (argx == null) {
            argx = "";
        }
        if (cardNo == null) {
            cardNo = "0000000000";
        }
        if (argx.equals("0") || argx.equals("2")) {
            cardType = "信用卡";
        } else if (argx.equals("1") || argx.equals("3")) {
            cardType = "借记卡";
        } else {
            cardType = "   ";
        }

        if (str.length() > 4) {
            str = str.substring(0, 4) + "...";
        }
        String a = str + cardType + " " + "尾号" + "  " + cardNo.subSequence(cardNo.length() - 4, cardNo.length());
        return a;
    }

    public static String cradsGets(String str, String argx, String cardNo) {
        String cardType;
//		  int i=Integer.parseInt(argx);
        if (str == null) {
            str = "";
        }
        if (argx == null) {
            argx = "";
        }
        if (cardNo == null) {
            cardNo = "0000000000";
        }
        if (argx.equals("0") || argx.equals("2")) {
            cardType = "信用卡";
        } else if (argx.equals("1") || argx.equals("3")) {
            cardType = "借记卡";
        } else {
            cardType = "   ";
        }

        if (str.length() > 4) {
            str = str.substring(0, 4) + "...";
        }
        String a = str + cardType + " " + " (尾号" + cardNo.subSequence(cardNo.length() - 4, cardNo.length()) + ")";
        return a;
    }

    //银行卡名省略
    public static String cradsShow(String str, String argx, String cardNo) {
        String a = "";
        if (StringUtil.isEmpty(str)) {
            str = "";
        }
        if (!StringUtil.isEmpty(cardNo) && cardNo.length() > 4) {
            String cardType;
            if (argx.equals("01")) {
                cardType = "借记卡";
            } else if (argx.equals("02")) {
                cardType = "信用卡";
            } else {
                cardType = "   ";
            }
            if (str.length() > 4) {
                str = str.substring(0, 4) + "...";
            }
            a = str + cardType + " " + "尾号" + " " + cardNo.subSequence(cardNo.length() - 4, cardNo.length());
        }
        return a;
    }

    //银行卡名不省略
    public static String cradsShowNoOmit(String str, String argx, String cardNo) {
        String a = "";
        if (StringUtil.isEmpty(str)) {
            str = "";
        }
        if (!StringUtil.isEmpty(cardNo) && cardNo.length() > 4) {
            String cardType;
            if (argx.equals("01")) {
                cardType = "借记卡";
            } else if (argx.equals("02")) {
                cardType = "信用卡";
            } else {
                cardType = "   ";
            }
            a = str + cardType + " " + "尾号" + " " + cardNo.subSequence(cardNo.length() - 4, cardNo.length());
        }
        return a;
    }

    /**
     * 返回首字符后面替补*号
     * String
     *
     * @return String 首字符后加*
     */

    // 返回首字符后面替补*号
    public static String cradsGetname(String s) {
        String a;
        if (!isEmpty(s)) {
            a = s.substring(0, 1);
            int n = s.length() - 1;
            if (n < 4) {
                for (int k = s.length() - 1; k > 0; k--) {
                    a += "*";
                }
            } else {
                for (int k = 3; k > 0; k--) {
                    a += "*";
                }
            }
        } else {
            a = "";
        }
        return a;
    }

    public static String getStatus(String s) {
        if (isEmpty(s)) {
            s = "";
        }
        if (s.equals("00")) {
            return "交易成功";
        } else if (s.equals("01")) {
            return "交易失败";
        } else if (s.equals("02")) {
            return "交易已撤销";
        } else {
            return "处理中";
        }
    }

    //收款方信息
    public static String cradsget(String str, String argx, String cardNo, String name) {
        String cardType;
        String name_bank;
        if (StringUtil.isEmpty(str)) {
            str = "";
        }
        if (argx.equals("00") || argx.equals("02")) {
            cardType = "信用卡";
        } else if (argx.equals("01") || argx.equals("03")) {
            cardType = "借记卡";
        } else {
            cardType = "   ";
        }
        if (!isEmpty(name)) {
            name_bank = name.subSequence(0, 1).toString();
            int n = name.length() - 1;
            if (n < 4) {
                for (int k = name.length() - 1; k > 0; k--) {
                    name_bank += "*";
                }
            } else {
                for (int k = 3; k > 0; k--) {
                    name_bank += "*";
                }
            }
        } else {
            name_bank = "";
        }
        if (str.length() > 4) {
            str = str.substring(0, 4) + "...";
        }
        String a = str + cardType + " " + "尾号" + "  " + cardNo.subSequence(cardNo.length() - 4, cardNo.length()) + "  " + name_bank;
        return a;
    }

    /**
     * @author mysterylove
     * Description
     * 进位加一，精确到0.01
     */
    public static float returnThNumber(String arg) {
        if (arg != null && arg.length() != 0) {//判断不为空
            float i = (float) (int) (Float.parseFloat(arg) * 1000) / 1000 - (float) (int) (Float.parseFloat(arg) * 100) / 100;
            if (i > 0) {//千分位后面一位还有数字，则千分位加一舍弃后面部分
                return (float) (int) (Float.parseFloat(arg) * 100 + 1);
            } else {//千分位后面一位没有数字，则千分位后面舍弃
                return (float) (int) (Float.parseFloat(arg) * 100);
            }
        } else {
            return 0;
        }

    }

    /**
     * @author mysterylove
     * Description
     * 精确到0.01
     * 舍位数
     */
    public static String setBigjd(float ft) {//保留两位有效数字（丢弃后面）
        String s = "";
        if (ft < 1000000) {
            s = ft + "";
        } else {
            s = NumberFormat.getInstance().format(ft);
        }
        if (s.contains(".")) {
            if (s.length() - s.indexOf(".") - 1 > 2) {
                s = s.substring(0, s.indexOf(".") + 3);
            } else if (s.length() - s.indexOf(".") - 1 < 2) {
                s += "0";
            }
            return s;
        } else {
            s += ".00";
            return s;
        }
    }

    public static String setBigjd(double ft) {//重载
        String s = "";
        if (ft < 1000000) {
            s = ft + "";
        } else {
            s = NumberFormat.getInstance().format(ft);
        }
        if (s.contains(".")) {
            if (s.length() - s.indexOf(".") - 1 > 2) {
                s = s.substring(0, s.indexOf(".") + 3);
            } else if (s.length() - s.indexOf(".") - 1 < 2) {
                s += "0";
            }
            return s;
        } else {
            s += ".00";
            return s;
        }
    }

    /**
     * @param source String 母字符串
     * @return String 替换后的字符串
     * @author mysterylove Description 字符串解码网页版Decoder
     * <p>
     * * @param from String 原始字符串
     * to
     * String 目标字符串
     */

    public static String setUrlBase64(String source) {
        try {
            source = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return source.toString();
    }

    /**
     * @param source String 母字符串
     * @return String 替换后的字符串
     * @author mysterylove Description 字符串解码网页版Decoder
     * <p>
     * * @param from String 原始字符串
     * to
     * String 目标字符串
     */

    public static String setTextBase64(String source) {
        if (isEmpty(source)) {
            return "";
        }
        try {
            source = URLDecoder.decode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
        }
        return source;
    }

    /**
     * 文字中间两部分标红，第一部分start1->end1;第二部分start2->end2
     */
    public static SpannableStringBuilder getRedStringBuilder(int start1, int end1, int start2, int end2, String text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (end2 <= text.length()) {
            builder.setSpan(new ForegroundColorSpan(Color.rgb(153, 153, 153)), 0, start1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.RED), start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.rgb(153, 153, 153)), end1, start2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.RED), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(Color.rgb(153, 153, 153)), end2, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    /********
     * 通过GZip解压
     * @param str
     * @return
     */
    public static String uncompressByGZIPString(String str) {
        if (null == str || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] strByte = Base64.decode(str.getBytes("utf-8"), Base64.DEFAULT);

            ByteArrayInputStream bis = new ByteArrayInputStream(strByte);

            GZIPInputStream gs = new GZIPInputStream(bis);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gs.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * 数据通过Gzip压缩传base64
     *
     * @param
     * @param
     * @throws Exception
     */
    public static String compassByGzipString(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes("utf-8"));
            gzip.close();
        } catch (IOException e) {

        }
        return Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
    }

    /******
     * 通过Gzip压缩
     * @param bytes
     * @return
     */
    public static byte[] compassByGzipByte(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(bytes);
            gzip.close();
        } catch (IOException e) {
            AriesLog.i("IDCardImg", "compassByGzipByte异常");
        }
        return out.toByteArray();
    }

    /*******
     * 通过Gzip解压缩
     * @param bytes
     * @return
     */
    public static byte[] uncompressByGZIPByte(byte[] bytes) {
        if (null == bytes || bytes.length == 0) {
            return bytes;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

            GZIPInputStream gs = new GZIPInputStream(bis);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gs.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 字节转换成字符串
     *
     * @param byteArray
     * @return
     */
    public static String byteArrayToStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        } else {
            String str = null;

            try {
                str = new String(byteArray, "gb2312");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }

            return str;
        }
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * byte[]转为bitmap
     *
     * @param data
     * @return
     */
    public static Bitmap byteToBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
//		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 判断手机相机权限是否打开
     *
     * @return true:打开，false:关闭
     */
    public static boolean cameraIsCanUse(Context context) {
        boolean hasPermission = true;
        //6.0以上和6.0以下分开判断
        if (Build.VERSION.SDK_INT < 23) {
            Camera mCamera = null;
            try {
                mCamera = Camera.open();
                if (null != mCamera) {
                    Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
                    if (null != mParameters) {
                        mCamera.setParameters(mParameters);
                    } else {
                        hasPermission = false;
                    }

                } else {
                    hasPermission = false;
                }

            } catch (Exception e) {
                hasPermission = false;
            }

            if (mCamera != null) {
                try {
                    mCamera.release();
                } catch (Exception e) {
                    return hasPermission;
                }
            }
        } else {
            PackageManager packageManager = context.getPackageManager();
            int permission = packageManager.checkPermission("android.permission.CAMERA", "com.bankcomm.maidanba");
            if (PackageManager.PERMISSION_GRANTED == permission) {
                //有这个权限
                hasPermission = true;
            } else {
                //没有这个权限
                hasPermission = false;
            }
        }
        return hasPermission;
    }

    /**
     * 判断姓名是否规范（可含有, . ·）
     *
     * @return true:规范，false:不规范
     */
    public static boolean isConSpeCharacters(String string) {
        if (string.indexOf(" ")==0||string.lastIndexOf(" ")==(string.length()-1)){
            return false;
        }
        String str = string.replaceAll(" ","");// 姓名
        while (str.contains(".")||str.contains("·")){
            str = str.replace(".","");
            str = str.replace("·","");
        }
        String namez = "(^[\u4E00-\u9FA5A-Za-z]+$)";
        return Pattern.compile(namez).matcher(str).matches();
    }

    public static boolean isIDCard(String string) {
        //定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
        Pattern idNumPattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"); //15位
        Pattern idNum18 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");//18位
        Matcher idNumMatcher = idNumPattern.matcher(string);
        Matcher idNumMatcher_18 = idNum18.matcher(string);
        if (!idNumMatcher.matches() && !idNumMatcher_18.matches()) {
            return false;
        }
        return true;
    }
}
