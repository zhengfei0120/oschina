package com.goopai.app.exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

import com.goopai.app.common.UIHelper;
import com.goopai.app.manager.AppContext;
import com.goopai.app.manager.AppManager;
import com.goopai.oschina.R;

import java.io.*;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.os.Environment;
import android.os.Looper;

public class AppException extends Exception implements UncaughtExceptionHandler {

	private final static boolean Debug = false;

	/** Exception Type */
	public final static byte TYPE_NETWORK = 0x01;
	public final static byte TYPE_SOCKET = 0x02;
	public final static byte TYPE_HTTP_CODE = 0x03;
	public final static byte TYPE_HTTP_ERROR = 0x04;
	public final static byte TYPE_XML = 0x05;
	public final static byte TYPE_IO = 0x06;
	public final static byte TYPE_RUN = 0x07;

	private byte type;
	private int code;

	private Thread.UncaughtExceptionHandler mDefaultHandler;

	public byte getType() {
		return this.type;
	}

	public int getCode() {
		return this.code;
	}

	private AppException() {
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	private AppException(byte type, int code, Exception excp) {
		super(excp);
		this.type = type;
		this.code = code;
		if (Debug) {

		}
	}

	/**
	 * make error message
	 * 
	 * @param context
	 */
	public void makeToast(Context context) {
		switch (this.getType()) {
		case TYPE_NETWORK:
			UIHelper.toastMessage(context, R.string.network_not_connected);
			break;
		case TYPE_SOCKET:
			UIHelper.toastMessage(context, R.string.socket_exception_error);
			break;
		case TYPE_HTTP_CODE:
			UIHelper.toastMessage(context, R.string.http_status_code_error);
			break;
		case TYPE_HTTP_ERROR:
			UIHelper.toastMessage(context, R.string.http_exception_error);
			break;
		case TYPE_XML:
			UIHelper.toastMessage(context, R.string.xml_parser_failed);
			break;
		case TYPE_IO:
			UIHelper.toastMessage(context, R.string.io_exception_error);
			break;
		case TYPE_RUN:
			UIHelper.toastMessage(context, R.string.app_run_code_error);
			break;
		default:
			break;
		}
	}

	/**
	 * save error log
	 * 
	 * @param excp
	 */
	public void saveErrorLog(Exception excp) {
		String errorlog = "errorlog.txt";
		String savePath = "";
		String logfilePath = "";
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			String storageState = Environment.getExternalStorageState();
			if (storageState.equals(Environment.MEDIA_MOUNTED)) {
				savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/oschina/log/";
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				logfilePath = savePath + errorlog;
				if (logfilePath == "") {
					return;
				}
				File logFile = new File(logfilePath);
				if (!logFile.exists()) {
					logFile.createNewFile();
				}
				fw = new FileWriter(logFile, true);
				pw = new PrintWriter(fw);
				pw.println("----------------" + new Date().toLocaleString() + "----------------");
				excp.printStackTrace(pw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String getCrashReport(Context context, Throwable ex) {
		PackageInfo pInfo = ((AppContext) context.getApplicationContext()).getPackageInfo();
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version:" + pInfo.versionName + "(" + pInfo.versionCode + ")\n");
		exceptionStr.append("Android:" + android.os.Build.VERSION.RELEASE + "(" + android.os.Build.MODEL + ")\n");
		exceptionStr.append("Exception:" + ex.getMessage());
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString() + "\n");
		}
		return exceptionStr.toString();
	}

	/**
	 * 自定义处理异常
	 * 
	 * @param ex
	 * @return
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		final Context context = AppManager.getAppManager().currentActivity();
		if (context == null) {
			return false;
		}
		final String crashReport = getCrashReport(context, ex);
		new Thread() {
			public void run() {
				Looper.prepare();
				UIHelper.sendAppCrashReport(context, crashReport);
				Looper.loop();
			}
		}.start();
		return false;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

	}
}
