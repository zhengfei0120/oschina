package com.goopai.app.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConfig {

	private final static String APP_CONFIG = "config";

	private static Context mContext;
	private static AppConfig appConfig;

	public static AppConfig getAppConfig(Context context) {
		if (appConfig == null) {
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}

	public static SharedPreferences getSharedPreferences(Context context) {
         return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	
}
