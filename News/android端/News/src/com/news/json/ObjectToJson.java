package com.news.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.news.code.NewsCode;
import com.news.content.bean.NewsContentBean;

public class ObjectToJson {
	// 将栏目和用户名发过去，为了获得相应栏目的信息
	public static String GetNewsByColumn(String cColumn, String pColumn,
			String name) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.NEWS_BY_COLUMN);
		jsonObject.put("cColumn", cColumn);
		jsonObject.put("pColumn", pColumn);
		jsonObject.put("name", name);
		return jsonObject.toString();
	}
	
	// 管理员将栏目和用户名发过去，为了获得相应栏目的信息
	public static String GetNewsByColumnAdmin(String cColumn, String pColumn
				) throws JSONException {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("NewsCode", NewsCode.NEWS_BY_COLUMN_ADMIN);
			jsonObject.put("cColumn", cColumn);
			jsonObject.put("pColumn", pColumn);
			return jsonObject.toString();
		}

	// 添加新闻
	public static String AddNews(NewsContentBean newsContentBean)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.ADD_NEWS);
		jsonObject.put("cColumn", newsContentBean.getcColumn());
		jsonObject.put("pColumn", newsContentBean.getpColumn());
		jsonObject.put("author", newsContentBean.getAuthor());
		jsonObject.put("time", newsContentBean.getTime());
		jsonObject.put("title", newsContentBean.getTitle());
		jsonObject.put("status", newsContentBean.getStatus());
		jsonObject.put("content", newsContentBean.getContent());
		return jsonObject.toString();
	}

	// 删除新闻
	public static String DeleteNews(int id) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.DELETE_NEWS);
		jsonObject.put("id", id);
		return jsonObject.toString();
	}

	// 更新新闻
	public static String UpdateNews(NewsContentBean newsContentBean)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.UPDATE_NEWS);
		jsonObject.put("id", newsContentBean.getId());
		jsonObject.put("title", newsContentBean.getTitle());
		jsonObject.put("content", newsContentBean.getContent());
		return jsonObject.toString();
	}

	// 更新新闻
	public static String CheckNews(int id)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.CHECK_NEWS);
		jsonObject.put("id", id);
		return jsonObject.toString();
	}
	
	//搜索新闻
	public static String SearchNews(String name,String startTime,String endTime,String keyWord)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.SEARCH_NEWS);
		jsonObject.put("name", name);
		jsonObject.put("startTime", startTime);
		jsonObject.put("endTime", endTime);
		jsonObject.put("keyWord", keyWord);
		
		return jsonObject.toString();
	}

}
