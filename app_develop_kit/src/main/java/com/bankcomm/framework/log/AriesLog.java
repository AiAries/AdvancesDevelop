package com.bankcomm.framework.log;


import static com.bankcomm.framework.network.FrameworkConstant.ENABLE_LOG;

/**
 * 
 * @author dcc
 * 
 */
public class AriesLog {
	
	private static boolean FLAG = ENABLE_LOG;

	public static void d(String tag, String msg) {
		if (FLAG) {
			android.util.Log.d(tag, msg == null? "" : msg);
		}
	}
	public static void d(String tag, String ...msgs) {
		if (FLAG) {
			StringBuilder sb = new StringBuilder();
			for(String ss : msgs)
				sb.append(ss);
			android.util.Log.d(tag, sb.toString());
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (FLAG) {
			android.util.Log.d(tag, msg == null? "" : msg, tr);
		}
	}

	public static void i(String tag, String msg) {
		if (FLAG) {
			android.util.Log.i(tag, msg == null? "" : msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (FLAG) {
			android.util.Log.i(tag, msg == null? "" : msg, tr);
		}
	}


	public static void e(String tag, String msg) {
		if (FLAG) {
			android.util.Log.d(tag, msg == null? "" : msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (FLAG) {
			android.util.Log.e(tag, msg == null? "" : msg, tr);
		}
	}

}
