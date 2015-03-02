package com.goopai.app.common;

import com.goopai.app.manager.AppManager;
import com.goopai.oschina.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.widget.Toast;

public class UIHelper {

	/**
	 * Toast msg
	 * 
	 * @param context
	 * @param message
	 */
	public static void toastMessage(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public static void toastMessage(Context context, int message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public static void toastMessage(Context context, String message, int time) {
		Toast.makeText(context, message, time).show();
	}

	/**
	 * 发送系统崩溃报告
	 */
	public static void sendAppCrashReport(final Context context, final String crashReport) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_dialog_menu);
		builder.setTitle(R.string.app_error);
		builder.setMessage(R.string.app_error_message);
		builder.setPositiveButton(R.string.submit_report, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_SEND);// send email
				intent.setType("message/rfc822");
				intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "691777460@qq.com" });
				intent.putExtra(Intent.EXTRA_SUBJECT, "error report");
				intent.putExtra(Intent.EXTRA_TEXT, crashReport);
				context.startActivity(Intent.createChooser(intent, "send error report"));
				AppManager.getAppManager().appExit(context);
			}
		});
		builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				AppManager.getAppManager().appExit(context);
			}
		});
		builder.show();
	}

	public static void exit(final Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.app_menu_surelogout);
		builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				AppManager.getAppManager().appExit(context);
			}
		});
		builder.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();
	}
}
