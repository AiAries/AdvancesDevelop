package techown.login.network.test;


import com.bankcomm.framework.network.RetrofitApi;
import com.bankcomm.framework.network.bean.base.BaseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Call;
import techown.login.network.api.LoginApi;
import techown.login.network.bean.User;



public class TestRxJava {
	public static void main(String[] args) {
		try {
			getUser();
//			getUserByRxjava();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getUser() throws IOException{

		LoginApi commonApi = RetrofitApi.getRetrofit().create(LoginApi.class);
		Call<BaseEntity<User>> call = commonApi.getUserByCall();
		retrofit2.Response<BaseEntity<User>> resp = call.execute();
		String string = resp.body().getData().get(0).getUsername()+"::"+
				resp.body().getData().get(0).getPassword();
		System.out.println(string);

	}
	public static void getUserByRxjava() throws IOException{

		LoginApi commonApi = RetrofitApi.getRetrofit().create(LoginApi.class);
//		Call<String> call = commonApi.login("lili", "1234");
		Observable<BaseEntity<User>> user = commonApi.getUser();
		user.map(new Function<BaseEntity<User>, List<User>>() {
			@Override
			public List<User> apply(@NonNull BaseEntity<User> userBaseEntity) throws Exception {
				return userBaseEntity.getData();
			}
		}).map(new Function<List<User>, List<String>>() {
			@Override
			public List<String> apply(@NonNull List<User> users) throws Exception {
				List<String> usernames = new ArrayList<String>();
				for (User user1 : users) {
					usernames.add(user1.getUsername());
				}
				return usernames;
			}
		}).subscribe(new Consumer<List<String>>() {
			@Override
			public void accept(List<String> strings) throws Exception {
				for (String string : strings) {
					System.out.println(string);
				}
			}
		});
	}
}
