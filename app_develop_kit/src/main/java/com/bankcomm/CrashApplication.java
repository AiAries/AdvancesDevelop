package com.bankcomm;

import android.app.Application;

public class CrashApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		
		//CrashApplication.context = getApplicationContext();
	}
	
	/*private static Context context;
	
	public static Context getAppContext(){
		return context;
	}*/
	
}
