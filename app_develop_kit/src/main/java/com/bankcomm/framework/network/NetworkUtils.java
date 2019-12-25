package com.bankcomm.framework.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.bankcomm.framework.utils.StringUtil;
import com.bankcomm.ui.base.BaseApplication;

import java.util.List;

/**
 * Created by  on 2018/6/21.
 */

public class NetworkUtils {

    // 手机网络类型
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public static boolean isAvailable(Context context) {
        boolean result = false;

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = connMgr.getActiveNetworkInfo();
        if (ni != null && ni.isAvailable()) {
            result = true;
        }

        return result;
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivity.getActiveNetworkInfo();
        if (activeInfo == null)
            return false;
        int networkType = activeInfo.getType();
        if (networkType == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWifiOpen(Context context) {
        boolean isWifiConnect = false;
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // check the networkInfos numbers
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for (int i = 0; i < networkInfos.length; i++) {
            if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                if (networkInfos[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                    isWifiConnect = false;
                }
                if (networkInfos[i].getType() == ConnectivityManager.TYPE_WIFI) {
                    isWifiConnect = true;
                }
            }
        }
        return isWifiConnect;
    }

    /**
     * @return
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtil.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    /**
     * 利用WifiConfiguration.KeyMgmt的管理机制，来判断当前wifi是否需要连接密码
     *
     * @return true：需要密码连接，false：不需要密码连接
     */
    public static boolean isCurrentWifiHasPassword() {
        try {
            WifiManager wifiManager = (WifiManager) BaseApplication.mGlobalApp.getSystemService(Context.WIFI_SERVICE);

            // 得到当前连接的wifi热点的信息
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();

            // 得到当前WifiConfiguration列表，此列表包含所有已经连过的wifi的热点信息，未连过的热点不包含在此表中
            List<WifiConfiguration> wifiConfiguration = wifiManager.getConfiguredNetworks();

            String currentSSID = wifiInfo.getSSID();
            if (currentSSID != null && currentSSID.length() > 2) {
                if (currentSSID.startsWith("\"") && currentSSID.endsWith("\"")) {
                    currentSSID = currentSSID.substring(1, currentSSID.length() - 1);
                }

                if (wifiConfiguration != null && wifiConfiguration.size() > 0) {
                    for (WifiConfiguration configuration : wifiConfiguration) {
                        if (configuration != null && configuration.status == WifiConfiguration.Status.CURRENT) {
                            String ssid = null;
                            if (!TextUtils.isEmpty(configuration.SSID)) {
                                ssid = configuration.SSID;
                                if (configuration.SSID.startsWith("\"") && configuration.SSID.endsWith("\"")) {
                                    ssid = configuration.SSID.substring(1, configuration.SSID.length() - 1);
                                }
                            }
                            if (TextUtils.isEmpty(currentSSID) || currentSSID.equalsIgnoreCase(ssid)) {
                                //KeyMgmt.NONE表示无需密码
                                return (!configuration.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.NONE));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认为需要连接密码
        return true;
    }

}
