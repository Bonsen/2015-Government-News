package com.news.data.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.news.DB.NewsDetailDB;
import com.news.code.NewsCode;
import com.news.content.bean.NewsContentBean;
import com.news.http.HttpUtils;
import com.news.json.JsonToObject;
import com.news.json.ObjectToJson;

public class DataProvider {

	private static NewsDetailDB dop;

	// ���ݵ����ͬ��Ŀ��ȡ��ͬ�����б�
	public static List<Map<String, Object>> GetNewsListByColumn(
			String jsonStirng) throws JSONException {
		return JsonToObject.GetNewsByColumn(jsonStirng);
	}

	// ����Ա���ݵ����ͬ��Ŀ��ȡ��ͬ�����б�
	public static List<Map<String, Object>> GetNewsListByColumnAmin(
			String jsonStirng) throws JSONException {
		return JsonToObject.GetNewsByColumn(jsonStirng);
	}

	// �������
	public static String AddNews(NewsContentBean newsContentBean)
			throws JSONException {
		return HttpUtils.AddNews(NewsCode.URL,
				ObjectToJson.AddNews(newsContentBean));
	}

	// ɾ������
	public static String DeleteNews(int id) throws JSONException {
		return HttpUtils.DeleteNews(NewsCode.URL, ObjectToJson.DeleteNews(id));
	}

	// ��������
	public static String UpdateNews(NewsContentBean newsContentBean)
			throws JSONException {
		return HttpUtils.AddNews(NewsCode.URL,
				ObjectToJson.UpdateNews(newsContentBean));
	}

	// ��������
	public static List<Map<String, Object>> SearchNews(String jsonString)
			throws JSONException {
		return JsonToObject.GetNewsByColumn(jsonString);
	}
}
