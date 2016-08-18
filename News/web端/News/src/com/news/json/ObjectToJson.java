package com.news.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.news.code.NewsCode;
import com.news.content.bean.NewsContentBean;

public class ObjectToJson {
	// ����Ŀ���û�������ȥ��Ϊ�˻����Ӧ��Ŀ����Ϣ
	public static String GetNewsByColumn(String cColumn, String pColumn,
			String name) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.NEWS_BY_COLUMN);
		jsonObject.put("cColumn", cColumn);
		jsonObject.put("pColumn", pColumn);
		jsonObject.put("name", name);
		return jsonObject.toString();
	}

	// �������
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

	// ɾ������
	public static String DeleteNews(int id) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.DELETE_NEWS);
		jsonObject.put("id", id);
		return jsonObject.toString();
	}

	// ��������
	public static String UpdateNews(NewsContentBean newsContentBean)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.UPDATE_NEWS);
		jsonObject.put("id", newsContentBean.getId());
		jsonObject.put("title", newsContentBean.getTitle());
		jsonObject.put("content", newsContentBean.getContent());
		return jsonObject.toString();
	}

	// ��������
	public static String CheckNews(int id)
			throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NewsCode", NewsCode.CHECK_NEWS);
		jsonObject.put("id", id);
		return jsonObject.toString();
	}

}
