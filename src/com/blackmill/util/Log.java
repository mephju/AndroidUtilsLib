package com.blackmill.util;

public class Log {

	



	public static void e(String TAG, String msg) {

		if(!Build.IN_PRODUCTION) android.util.Log.e(TAG, msg);

	}
	public static void d(String TAG, String msg) {

		if(!Build.IN_PRODUCTION) android.util.Log.d(TAG, msg);

	}
	public static void v(String TAG, String msg) {

		if(!Build.IN_PRODUCTION) android.util.Log.v(TAG, msg);

	}
	public static void i(String TAG, String msg) {

		if(!Build.IN_PRODUCTION) android.util.Log.i(TAG, msg);

	}
	public static void w(String TAG, String msg) {

		if(!Build.IN_PRODUCTION) android.util.Log.w(TAG, msg);

	}




}
