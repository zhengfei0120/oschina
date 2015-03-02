package com.goopai.app.api;

import java.net.URI;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.CookiePolicy;

import com.goopai.app.bean.URLs;
import com.goopai.app.manager.AppContext;

public class ApiClient {

	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";
    
	private static final int TIMEOUT_CONNECTION = 20000;
	private static final int TIMEOUT_SOCKET = 20000;
	private static final int RETRY_TIME = 3;

	private static String appCookie;
	private static String appUserAgent;

	public static void clearCookie() {
		appCookie = "";
	}

	private static String getCookie(AppContext appContext) {
		if (appCookie == null || appCookie == "") {

		}
		return appCookie;
	}

	private static HttpClient getHttpClient() {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT_CONNECTION);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT_SOCKET);
		httpClient.getParams().setContentCharset(UTF_8);
		return httpClient;
	}

	private static GetMethod getHttpGet(String url, String cookie, String userAgent) {
		GetMethod httpGet = new GetMethod(url);
		httpGet.getParams().setSoTimeout(TIMEOUT_SOCKET);
		httpGet.setRequestHeader("Host", URLs.HOST);
		httpGet.setRequestHeader("Connection", "Keep-Alive");
		httpGet.setRequestHeader("Cookie", cookie);
		httpGet.setRequestHeader("User-Agent", userAgent);
		return httpGet;
	}

	private static PostMethod getHttpPost(String url, String cookie, String userAgent) {
		PostMethod httpPost = new PostMethod();
		httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
		httpPost.setRequestHeader("Host", URLs.HOST);
		httpPost.setRequestHeader("Connection", "Keep-Alive");
		httpPost.setRequestHeader("Cookie", cookie);
		httpPost.setRequestHeader("User-Agent", userAgent);
		return httpPost;
	}

	private static String _MakeUrl(String pUrl, Map<String, Object> params) {
		StringBuilder url = new StringBuilder(pUrl);
		for (String name : params.keySet()) {
			url.append('&');
			url.append(name);
			url.append('=');
			url.append(String.valueOf(params.get(name)));
		}
		return url.toString().replace("?&", "?");
	}
}
