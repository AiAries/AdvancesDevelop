package com.azp.phttp;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class PDAHttpClient {
    private static PDAHttpClient httpClient;

    private PDAHttpClient() {
    }

    public static PDAHttpClient getInstance() {
        synchronized (PDAHttpClient.class) {
            if (httpClient == null) {
                httpClient = new PDAHttpClient();
            }
        }
        return httpClient;
    }
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @param con          最好是应用上下文
     * @param param        请求参数
     * @param messageModel 调用的接口服务名
     * @param callback     回调响应的结果
     * @param <T>          响应报文的数据泛型
     */
    public <T> void httpRequest(final Context con, final HashMap<String, String> param, final MessageModel messageModel,
                                final Callback<T> callback) {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                executeRequest(con, param, messageModel, callback);
            }
        });

    }

    private <T> void executeRequest(Context con, HashMap<String, String> param, MessageModel messageModel,Callback<T> callback) {
        final CallbackWrap<T> callbackWrap = new CallbackWrap<>(callback);
        try {
            URL url = new URL(baseUrl);
//            SSLClient.initializeSSLContext();
            final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(25 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("wms-encrypt", "3des");
            OutputStream os = conn.getOutputStream();
            //todo 当请求主体没有参数的时候，需处理
            String params = mapToJsonString(param);
            byte[] bs = params.getBytes("UTF-8");
//            bs = Compress.compress(bs);
//            bs = Des.EncryptMode(SecretkeyUtil.getKey(username, imei), bs);
            os.write(bs);
            os.flush();
            os.close();
            final int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println(responseCode);
                // System.out.println(conn.getHeaderField("wms-error"));
                System.out.println(conn.getResponseMessage());
                callbackWrap.onError(new F200Exception(
                        "code:" + responseCode +
                                "ResponseMessage:" + conn.getResponseMessage()
                ));
            } else {
                InputStream in = conn.getInputStream();
//                if ("3des".equals(conn.getHeaderField("wms-encrypt"))) {
//                    in = DESUtil.decrypt(in,
//                            SecretkeyUtil.getKey(username, imei));// 员工号和手机imei号
//                }
//                if ("gzip".equals(conn.getHeaderField("wms-compress"))) {
//                    in = new GZIPInputStream(in);
//                }
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int i = 0;
                while ((i = in.read()) != -1) {
                    bos.write(i);
                }
                byte[] re = bos.toByteArray();
                String jsontext = new String(re, "UTF-8").trim();
                if (TextUtils.isEmpty(jsontext)) {
                    callbackWrap.onError(new F200Exception(
                            "code:" + responseCode +
                                    "ResponseMessage:" + conn.getResponseMessage()
                                    + "returnData:" + jsontext
                    ));
                    return;
                }
                Gson gson = new Gson();
                T t = gson.fromJson(jsontext, new TypeToken<T>() {
                }.getType());
                bos.close();
                in.close();
                callbackWrap.onSuccess(t);
            }

        } catch (Exception e) {
            callbackWrap.onError(e);
        }
    }

    private String mapToJsonString(HashMap<String, String> param) {
        JsonObject object = new JsonObject();
        Set<Map.Entry<String, String>> entries = param.entrySet();

        for (Map.Entry<String, String> next : entries) {
            object.addProperty(next.getKey(), next.getValue());
        }

        return object.toString();
    }


    public enum MessageModel {
        /**
         * 根据身份证查询信用卡信息
         */
        QRY_CRD("gtpQryMainAppendCrdByCrtNo", "qryMainAppendCrdByCrtNo"),
        /**
         * 根据卡号查询是否面签及激活
         */
        ACTIVE_SIGN("", "");

        private final String message;
        private final String model;

        MessageModel(String message, String model) {
            this.message = message;
            this.model = model;
        }

        public String getMessage() {
            return message;
        }

        public String getModel() {
            return model;
        }
    }

    public static class F200Exception extends Exception {
        public F200Exception(String message) {
            super(message);
        }
    }
}
