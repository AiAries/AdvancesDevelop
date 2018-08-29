package com.bankcomm.framework.utils.android;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.bankcomm.framework.log.AriesLog;
import com.bankcomm.framework.utils.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * android客户端工具类
 *
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2013年7月23日
 * @Description
 * @class com.yitong.zjrc.mbank.utils.yitong.AndroidUtil
 */
public class AndroidUtil {

    public static String getDeviceUUID(Context context) {
        byte[] b;
        TelephonyManager TelephonyMgr = null;
        try {
            TelephonyMgr = (TelephonyManager) context
                    .getApplicationContext().getSystemService(
                            Context.TELEPHONY_SERVICE);
            String m_szImei = TelephonyMgr.getDeviceId(); // Requires
            // READ_PHONE_STATE
            // 2 compute DEVICE ID
            String m_szDevIDShort = "35"
                    + // we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10
                    + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
                    + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
                    + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
                    + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
                    + Build.TAGS.length() % 10 + Build.TYPE.length() % 10
                    + Build.USER.length() % 10; // 13 digits
            // 3 android ID - unreliable
            String m_szAndroidID = Secure.getString(
                    context.getContentResolver(), Secure.ANDROID_ID);
            // 4 wifi manager, read MAC address - requires
            // android.permission.ACCESS_WIFI_STATE or comes as null
            WifiManager wm = (WifiManager) context.getApplicationContext()
                    .getSystemService(Context.WIFI_SERVICE);
            String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
            // 5 Bluetooth MAC address android.permission.BLUETOOTH required
            BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            String m_szBTMAC = m_BluetoothAdapter.getAddress();
            //System.out.println("m_szBTMAC " + m_szBTMAC);
            // 6 SUM THE IDs
            String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID
                    + m_szWLANMAC + m_szBTMAC;
            //System.out.println("m_szLongID " + m_szLongID);
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
            byte p_md5Data[] = m.digest();
            String m_szUniqueID = new String();
            for (int i = 0; i < p_md5Data.length; i++) {
                int b1 = (0xFF & p_md5Data[i]);
                // if it is a single digit, make sure it have 0 in front (proper
                // padding)
                if (b1 <= 0xF)
                    m_szUniqueID += "0";
                // add number to string
                m_szUniqueID += Integer.toHexString(b1);
            }
            m_szUniqueID = m_szUniqueID.toUpperCase();
            AriesLog.i("--DeviceID----", m_szUniqueID);
            AriesLog.d("DeviceIdCheck",
                    "DeviceId that generated MPreferenceActivity:"
                            + m_szUniqueID);
            byte[] a = null;
            a = m_szUniqueID.getBytes("utf-8");
            b = new byte[16];
            System.arraycopy(a, 0, b, 0, 16);
            return new String(b, Charset.forName("utf-8"));
        } catch (Exception e) {
            return TelephonyMgr.getDeviceId(); // Requires
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        if (null != context) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } else {
            return 0;
        }
    }

    /**
     * 获取手机宽度
     */
    public static int getWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取应用版本号
     *
     * @param ctx
     * @return
     */
    public static String getAppVersion(Context ctx) {
        PackageInfo pi = null;
        try {
            pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // Log.e(TAG, e.getMessage(), e);
        }
        if (null != pi) {
            return pi.versionName;
        }
        return null;
    }

    /**
     * 获取应用版本号
     *
     * @param ctx
     * @return
     */
    public static int getAppVersionCode(Context ctx) {
        PackageInfo pi = null;
        try {
            pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            // Log.e(TAG, e.getMessage(), e);
        }
        if (null != pi) {
            return pi.versionCode;
        }
        return -1;
    }


    /**
     * @param
     * @return Date
     * @author mysterylove caiyu.happy@163.com
     * @description data时间，format为格式如yyyyMMddHHmmss或者yyyy-MM-dd等
     */

    public static Date strToDateLongx(String strDate, String format) {//时间格式化
        SimpleDateFormat formatter = new SimpleDateFormat(format);//yyyy-MM-ddHHmmss
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * @param
     * @return long
     * @author mysterylove caiyu.happy@163.com
     * @description data1, date2时间, d2-d1，mode为模式值为day,minute
     */
    public static long CalculateTime(Date d1, Date d2, String mode) {
        //计算差值，分钟数
        long minutes = (d2.getTime() - d1.getTime()) / (1000 * 60);
        //计算差值，天数
        long days = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
        if (mode.equals("day")) {
            return days;
        } else if (mode.equals("minute")) {
            return minutes;
        } else {
            return 0;
        }
    }


    /**
     * 手机号正则判断
     *
     * @param phoneNum
     * @return boolean
     */
    public static boolean matchesPhoneNum(final String phoneNum) {
        //定义判别用户手机号的正则表达式
        Pattern idNumPattern = Pattern.compile("1\\d{10}");
        //通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(phoneNum);
        return idNumMatcher.matches();
    }


    /**
     * @param str
     * @return 判断字符串是否可以强转为int类型
     */
    public static boolean stringOfInteger(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static Double getResultJ(double a, String b) {
        BigDecimal bshouldpay = new BigDecimal(a);
        bshouldpay = bshouldpay.setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal bs = new BigDecimal(b);
        bs = bs.setScale(2, BigDecimal.ROUND_HALF_UP);
        bshouldpay = bshouldpay.subtract(bs);
        double result = Double.parseDouble(bshouldpay.toString());
        return result;
    }

    //判断同名
    public static boolean IsEquals(String receive, String pay) {
        String rType = "";
        String pType = "";
        Pattern p = Pattern.compile("[0-9]*");
        for (int i = receive.length(); i > 0; i--) {
            Matcher m = p.matcher(receive.substring(i - 1, i));
            if (m.matches()) {
                rType = receive.substring(i - 1, i).toString() + rType.toString();
            }
        }
        rType = "000000" + rType.toString();
        rType = rType.substring(rType.length() - 6, rType.length());
        for (int i = pay.length(); i > 0; i--) {
            Matcher m = p.matcher(pay.substring(i - 1, i));
            if (m.matches()) {
                pType = pay.substring(i - 1, i).toString() + pType.toString();
            }
        }
        pType = "000000" + pType.toString();
        pType = pType.substring(pType.length() - 6, pType.length());
        if (rType.equals(pType)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param editText 卡号显示方式，中间用空格隔开
     * @param num      总位数
     * @param each     每组位数
     */
    public static void initEdit(final EditText editText, final int num, final int each) {
        // TODO Auto-generated method stub
        /**
         * 每隔四位用空格隔开
         */
        editText.addTextChangedListener(new TextWatcher() {

            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= each || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = editText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if (index % (each + 1) == each) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    editText.setText(str);
                    Editable etable = editText.getText();

                    int lenght;//Edit总共占用的位数
                    if (num % each == 0) {
                        lenght = num + num / each - 1;
                    } else {
                        lenght = num + num / each;
                    }
                    if (location >= lenght) {
                        location = lenght;
                    }
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }

            }
        });

    }

    /**
     * 防止用户连续点击导致的重复事件
     */
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * param yyyy-MM-dd
     * 根据日期计算星期几
     */
    public static String getWeekDay(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        StringBuffer stringBuffer = null;
        try {
            calendar.setTime(simpleDateFormat.parse(time));
            stringBuffer = new StringBuffer();
        } catch (Exception e) {
            AriesLog.e("System_Error", e.getMessage());
            return null;
        }
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                stringBuffer.append("周日");
                break;
            case 2:
                stringBuffer.append("周一");
                break;
            case 3:
                stringBuffer.append("周二");
                break;
            case 4:
                stringBuffer.append("周三");
                break;
            case 5:
                stringBuffer.append("周四");
                break;
            case 6:
                stringBuffer.append("周五");
                break;
            case 7:
                stringBuffer.append("周六");
                break;
            default:
                break;
        }
        return stringBuffer.toString();
    }

    /**
     * 设备唯一号存储
     */
    public static final String MOBILE_SETTING = "ZJRC_MOBILE_SETTING";

    /**
     * 设备唯一表示
     */
    public static final String MOBILE_UUID = "ZJRC_MOBILE_UUID";


    /**
     * @param context
     * @return获取手机ip地址
     */
    public static String getLocalIpAddress(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            StringBuilder sb = new StringBuilder();
            sb.append(i & 0xFF).append(".");
            sb.append((i >> 8) & 0xFF).append(".");
            sb.append((i >> 16) & 0xFF).append(".");
            sb.append((i >> 24) & 0xFF);
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    // 获取手机型号
    public static String getModel(Context context) {
        return Build.MODEL != null ? Build.MODEL.replace(" ", "") : "unknown";
    }

    /**
     * 取得操作系统版本号
     */
    public static String getOSVersion(Context context) {
        return Build.VERSION.RELEASE != null ? Build.VERSION.RELEASE : "";
    }

    /**
     * 手机卡 IMSI
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSubscriberId() != null ? telephonyManager.getSubscriberId() : "";
    }


    public static String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            AriesLog.i("IMEI", telephonyManager.getSimSerialNumber());
            return telephonyManager.getSimSerialNumber();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 手机号码
     *
     * @param context
     * @return
     */
    public static String getMobile(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number() != null ? telephonyManager.getLine1Number() : "";
    }

    public static String getVersionName(Context context) {
        try {
            String appVersion = null;
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            appVersion = pinfo.versionName;
            pinfo = null;
            return appVersion;

        } catch (NameNotFoundException e) {
            return "";
        }
    }


    public static String getMac(Context context) {
        // wifi mac地址
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (null != wifi) {
                WifiInfo info = wifi.getConnectionInfo();
                if (null != info) {
                    String wifiMac = info.getMacAddress();
                    return wifiMac != null ? wifiMac : "";
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static String getSN(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (null != tm) {
                String sn = tm.getSimSerialNumber();
                return sn != null ? sn : "";
            } else {
                return "";
            }

        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 手机卡imei
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        try {
            String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            return imei != null ? imei : "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        SharedPreferences mShare = context.getSharedPreferences(MOBILE_SETTING, 0);
        String uuid = "";
        if (mShare != null && StringUtil.isNotEmpty(mShare.getString(MOBILE_UUID, ""))) {
            uuid = mShare.getString(MOBILE_UUID, "");
        }
        if (StringUtil.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            mShare.edit().putString(MOBILE_UUID, uuid).commit();
        }
        return uuid;
    }


    public static String encrypt(String data) {
        return data;
    }

    /**
     * 判断网络是否可用 return
     **/
    public static boolean netAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        return ni != null ? ni.isConnectedOrConnecting() : false;
    }

    public static int getIntegerConfig(Context context, String key) {
        AssetManager assetManager = context.getAssets();
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            AriesLog.e("Sys_error", e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    AriesLog.e("Sys_error", e.getMessage());
                }

            }
        }
        return Integer.parseInt(properties.getProperty(key, "-1"));
    }

    public static boolean getBooleanConfig(Context context, String key) {
        AssetManager assetManager = context.getAssets();
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            AriesLog.e("Sys_error", e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    AriesLog.e("Sys_error", e.getMessage());
                }

            }
        }
        return Boolean.parseBoolean(properties.getProperty(key, "false"));
    }

    public static String getStringConfig(Context context, String key) {
        AssetManager assetManager = context.getAssets();
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            AriesLog.e("Sys_error", e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    AriesLog.e("Sys_error", e.getMessage());
                }
            }
        }
        return properties.getProperty(key, null);
    }

    public static void setConfig(Context context, String key, String value) {
        AssetManager assetManager = context.getAssets();
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            AriesLog.e("Sys_error", e.getMessage());
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    AriesLog.e("Sys_error", e.getMessage());
                }
            }
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("file:///android_asset/config.properties");
            properties.setProperty(key, value);
            properties.store(outputStream, null);
        } catch (Exception e) {
            AriesLog.e("Sys_error", e.getMessage());
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (Exception e) {
                AriesLog.e("Sys_error", e.getMessage());
            }
        }
    }

    /**
     * caiyu.happy@163.com
     * 隐式跳转activity
     */
    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentActivities(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.activityInfo.packageName;
        String className = serviceInfo.activityInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    /**
     * caiyu.happy@163.com
     * 获取签名
     */
    public static byte[] getSignature(Context context) {
        byte[] b = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.bankcomm.maidanba", PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory
                    .generateCertificate(new ByteArrayInputStream(signatures[0].toByteArray()));
            MessageDigest md = MessageDigest.getInstance("SHA1");
            b = md.digest(cert.getEncoded());
        } catch (NameNotFoundException e) {
            AriesLog.e("Sys_error", e.getMessage());
        } catch (CertificateException e) {
            AriesLog.e("Sys_error", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            AriesLog.e("Sys_error", e.getMessage());
        }
        return b;
    }

    /**
     * 从byte[]转出签名
     */
    public static String getSignatureString(byte[] arg) {
        final String HEX = "0123456789ABCDEF";
        if (arg == null)
            return "";
        StringBuffer result = new StringBuffer(2 * arg.length);
        for (int i = 0; i < arg.length; i++) {
            result.append(HEX.charAt((arg[i] >> 4) & 0x0f)).append(HEX.charAt(arg[i] & 0x0f));
        }
        return result.toString();
    }

    /**
     * 输出设备相关信息
     */
    public static void printDevice() {
        AriesLog.d("BOARD", Build.BOARD);// 主板
        AriesLog.d("BRAND", Build.BRAND);// android系统定制商 
        AriesLog.d("CPU_ABI", Build.CPU_ABI);// cpu指令集
        AriesLog.d("DEVICE", Build.DEVICE);// 设备参数 
        AriesLog.d("DISPLAY", Build.DISPLAY);// 显示屏参数
        AriesLog.d("FINGERPRINT", Build.FINGERPRINT);// 硬件名称
        AriesLog.d("HOST", Build.HOST);
        AriesLog.d("ID", Build.ID);// 修订版本列表 
        AriesLog.d("MANUFACTURER", Build.MANUFACTURER);// 硬件制造商 
        AriesLog.d("MODEL", Build.MODEL);// 版本 
        AriesLog.d("PRODUCT", Build.PRODUCT);// 手机制造商
        AriesLog.d("TAGS", Build.TAGS);// 描述build的标签 
        AriesLog.d("TIME", Build.TIME + "");// builder类型 
        AriesLog.d("USER", Build.USER);
    }

    /**
     * 是否三星手机
     */
    public static boolean isSamsungPhone() {
        final String manufacturer = Build.MANUFACTURER;
        return manufacturer != null && "samsung".equals(manufacturer.toLowerCase());
    }

    /**
     * 是否华为手机
     */
    public static boolean isHuaweiPhone() {
        final String manufacturer = Build.MANUFACTURER;
        return manufacturer != null && "huawei".equals(manufacturer.toLowerCase());
    }


}
