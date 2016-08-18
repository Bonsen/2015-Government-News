package com.news.http;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import com.news.login.bean.User;

/**
 * @author ZYY 这里面的方法是用来处理与服务器相关的业务逻辑的
 */

public class HttpUtils {

	// 登陆初始化数据
	public static String Login(String url_path, User user) {
		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			StringBuffer sb = new StringBuffer();
			String content = String.valueOf(CreateJson.CreateUserJson(user));
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(content.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	// 通过点击栏目获得相应的新闻列表
	public static String GetNewsbyColumn(String url_path, String jsonString) {
		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(jsonString.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";

	}

	// 添加新闻
	public static String AddNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(jsonString.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	// 删除新闻
	public static String DeleteNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(jsonString.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	// 删除新闻
	public static String UpdateNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(jsonString.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	// 审核新闻
	public static String CheckNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(jsonString.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	// 搜索新闻
	public static String SearchNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// 统一资源定位符
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// 将数据转化成为字节数组
			os.write(jsonString.getBytes());
			os.close();
			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "UTF-8");
				int len = 0;
				char[] buf = new char[1024];
				while ((len = isr.read(buf)) != -1) {
					sb.append(new String(buf, 0, len));
				}
				is.close();
				isr.close();
				return URLDecoder.decode(sb.toString(), "utf-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
