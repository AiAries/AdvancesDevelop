package com.bankcomm.framework.network.test;


import com.bankcomm.framework.network.RetrofitManager;
import com.bankcomm.framework.network.api.CommonApi;
import com.bankcomm.framework.network.bean.User;
import com.bankcomm.framework.network.bean.base.BaseEntity;

import java.io.IOException;

import retrofit2.Call;

public class Test {
	public static void main(String[] args) {
		try {
			getByRetrofit();
//			loginByRetrofit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getByRetrofit() throws IOException{
	
		CommonApi commonApi = RetrofitManager.newBuilder().build().create(CommonApi.class);
		Call<BaseEntity<User>> call = commonApi.getUser();
		retrofit2.Response<BaseEntity<User>> resp = call.execute();
		String string = resp.body().getData().get(0).getUsername()+"::"+
					resp.body().getData().get(0).getPassword();
		System.out.println(string);
	}
	public static void loginByRetrofit() throws IOException{
		
		CommonApi commonApi = RetrofitManager.newBuilder().build().create(CommonApi.class);
		retrofit2.Call<String> call = commonApi.login("lili", "1234");
		retrofit2.Response<String> resp = call.execute();
		String string = resp.body().toString();
		System.out.println(string);
	}
}
