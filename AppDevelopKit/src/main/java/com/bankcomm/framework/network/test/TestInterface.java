package com.bankcomm.framework.network.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestInterface {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 2; i++) {
			get();
		}
	}
	public static void get() throws Exception{
		OkHttpClient.Builder build = new OkHttpClient.Builder();
		OkHttpClient client = build.build();
//		String url = "https://www.baidu.com/";
		String url = "https://127.0.0.1:8080/java_test_interface/LoginApiServlet";
		Request request = new Request.Builder().url(url).build();

		// 创建TLS类型的SSLContext对象， that uses our TrustManager
		SSLContext sslContext = SSLContext.getInstance("TLS");
		// 用上面得到的trustManagers初始化SSLContext，这样sslContext就会信任keyStore中的证书
		// 第一个参数是授权的密钥管理器，用来授权验证，比如授权自签名的证书验证。第二个是被授权的证书管理器，用来验证服务器端的证书
		sslContext.init(null, new TrustManager[]{UnSafeTrustManager}, null);
		// 通过sslContext获取SSLSocketFactory对象
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		client.newBuilder().sslSocketFactory(sslSocketFactory, UnSafeTrustManager);
		Call newCall = client.newCall(request);
		newCall.enqueue(new Callback() {

			@Override
			public void onResponse(Call paramCall, Response paramResponse)
					throws IOException {
				String string = paramResponse.body().string();
				System.out.println("success:"+string);
			}

			@Override
			public void onFailure(Call paramCall, IOException paramIOException) {
				// TODO Auto-generated method stub
				System.out.println("failed:"+paramIOException.getMessage());
//				System.out.println(paramIOException.getCause().getMessage());
			}
		});


	}
	public static void copyDataFromStream(InputStream in){
		try {
			FileOutputStream fos = new FileOutputStream("");
			byte[] buf = new byte[8*1024];
			int len = -1;
			int totalLen = 0;
			while((len =in.read(buf))!=-1){
				fos.write(buf,0,len);
				totalLen+=len;
			}
			System.out.println(totalLen);
			fos.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 为了解决客户端不信任服务器数字证书的问题，网络上大部分的解决方案都是让客户端不对证书做任何检查，
	 * 这是一种有很大安全漏洞的办法
	 */
	public static X509TrustManager UnSafeTrustManager = new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[]{};
		}
	};
}
