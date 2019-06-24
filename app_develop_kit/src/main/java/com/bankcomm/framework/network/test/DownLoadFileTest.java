package com.bankcomm.framework.network.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownLoadFileTest {
	public static String fileName = "testJson.json";
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		downloadByOkHttp();
		downloadByUrlConnection();
	}

	public static void downloadByOkHttp() throws IOException {
		final OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(
				"http://127.0.0.1:8080/"+fileName).build();
		client.newBuilder().readTimeout(3, TimeUnit.SECONDS);
		Call call = client.newCall(request);
//		Response execute = call.execute();//同步阻塞执行。
		call.enqueue(new Callback() {
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful())
					throw new IOException("Unexpected code " + response);

				Headers responseHeaders = response.headers();
				for (int i = 0; i < responseHeaders.size(); i++) {
					System.out.println(responseHeaders.name(i) + ": "
							+ responseHeaders.value(i));
				}

				InputStream byteStream = response.body().byteStream();
				System.out.println(byteStream);
				copyDataFromStream(byteStream);
			}
			
			@Override
			public void onFailure(Call call, IOException arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg1.getMessage());
			}
		});

	}
	public static void downloadByUrlConnection() throws IOException {
		URL url = new URL("http://127.0.0.1:8080/"+fileName);
		HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
		int responseCode = openConnection.getResponseCode();
		System.out.println(responseCode);
//		System.out.println(openConnection.getHeaderField("Date"));
		if (responseCode == 200) {
			InputStream inputStream = openConnection.getInputStream();
			copyDataFromStream(inputStream);
		} else {
			System.out.printf("error--%d--",responseCode);
		}
		
	}
	public static void copyDataFromStream(InputStream in){
		try {
			FileOutputStream fos = new FileOutputStream("D:\\nodejs\\webservice\\"+fileName);
			byte[] buf = new byte[8*1024]; 
			int len;
			int totalLen = 0;
			while((len =in.read(buf))!=-1){
				fos.write(buf,0,len);
                System.out.println(len);
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
}
