package com.news.http;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import com.news.login.bean.User;

/**
 * @author ZYY ������ķ����������������������ص�ҵ���߼���
 */

public class HttpUtils {

	// ��½��ʼ������
	public static String Login(String url_path, User user) {
		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			StringBuffer sb = new StringBuffer();
			String content = String.valueOf(CreateJson.CreateUserJson(user));
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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

	// ͨ�������Ŀ�����Ӧ�������б�
	public static String GetNewsbyColumn(String url_path, String jsonString) {
		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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

	// �������
	public static String AddNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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

	// ɾ������
	public static String DeleteNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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

	// ɾ������
	public static String UpdateNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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

	// �������
	public static String CheckNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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

	// ��������
	public static String SearchNews(String url_path, String jsonString) {

		try {
			URL url = new URL(url_path);// ͳһ��Դ��λ��
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			StringBuffer sb = new StringBuffer();
			OutputStream os = connection.getOutputStream();
			// ������ת����Ϊ�ֽ�����
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
