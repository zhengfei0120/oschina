package com.goopai.app.uitil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * NetworkHelper Class
 * @author Administrator
 *
 */
public class NetworkHelper {

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (mConnectivityManager != null) {
			NetworkInfo ni = mConnectivityManager.getActiveNetworkInfo();
			if (ni != null && ni.isConnectedOrConnecting()) {
				return true;
			}
		}
		return false;
	}
}
