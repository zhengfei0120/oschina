package com.goopai.app.manager;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppContext extends Application {

	private static AppContext mInstance = null;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public AppContext() {
	}

	public static AppContext getInstance() {
		if (mInstance == null) {
			mInstance = new AppContext();
		}
		return mInstance;
	}

	public PackageInfo getPackageInfo() {
		PackageInfo packinfo = null;
		try {
			packinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (packinfo == null) {
			packinfo = new PackageInfo();
		}
		return packinfo;
	}
}
