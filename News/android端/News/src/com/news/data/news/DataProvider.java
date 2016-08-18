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

	// 根据点击不同栏目获取不同新闻列表
	public static List<Map<String, Object>> GetNewsListByColumn(
			String jsonStirng) throws JSONException {
		return JsonToObject.GetNewsByColumn(jsonStirng);
	}

	// 管理员根据点击不同栏目获取不同新闻列表
	public static List<Map<String, Object>> GetNewsListByColumnAmin(
			String jsonStirng) throws JSONException {
		return JsonToObject.GetNewsByColumn(jsonStirng);
	}

	// 添加新闻
	public static String AddNews(NewsContentBean newsContentBean)
			throws JSONException {
		return HttpUtils.AddNews(NewsCode.URL,
				ObjectToJson.AddNews(newsContentBean));
	}

	// 删除新闻
	public static String DeleteNews(int id) throws JSONException {
		return HttpUtils.DeleteNews(NewsCode.URL, ObjectToJson.DeleteNews(id));
	}

	// 更新新闻
	public static String UpdateNews(NewsContentBean newsContentBean)
			throws JSONException {
		return HttpUtils.AddNews(NewsCode.URL,
				ObjectToJson.UpdateNews(newsContentBean));
	}

	// 搜索新闻
	public static List<Map<String, Object>> SearchNews(String jsonString)
			throws JSONException {
		return JsonToObject.GetNewsByColumn(jsonString);
	}
}
