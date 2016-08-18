package com.news.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.news.content.bean.NewsContentBean;
import com.news.expandMenu.bean.FileBean;
import com.news.login.bean.User;

public class JsonToObject {
	public static List<Map<String, Object>> GetNewsByColumn(String jsonString)
			throws JSONException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		JSONObject jsonObject = new JSONObject(jsonString);
		JSONArray newsArray = jsonObject.getJSONArray("news");
		for (int i = 0; i < newsArray.length(); i++) {
			JSONObject jsonObject2 = newsArray.getJSONObject(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", (jsonObject2.getInt("id")));
			map.put("author", (jsonObject2.getString("author")));
			map.put("content", (jsonObject2.getString("content")));
			map.put("cColumn", (jsonObject2.getString("cColumn")));
			map.put("pColumn", (jsonObject2.getString("pColumn")));
			map.put("status", (jsonObject2.getInt("status")));
			map.put("time", (jsonObject2.getString("time")));
			map.put("title", (jsonObject2.getString("title")));

			list.add(map);
		}
		return list;
	}

	// 对登陆传过来的复杂json字符串进行剥离
	public static List<List<Object>> GetInitData(String jsonString) {
		List<List<Object>> list = new ArrayList<List<Object>>();
		List<Object> columnList = new ArrayList<Object>();
		List<Object> userList = new ArrayList<Object>();
		List<Object> newsList = new ArrayList<Object>();

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray columnArray = jsonObject.getJSONArray("column");
			JSONObject userObject = jsonObject.getJSONObject("user");
			JSONArray newsArray = jsonObject.getJSONArray("news");

			// 封装新闻栏目
			for (int i = 0; i < columnArray.length(); i++) {
				JSONObject jsonObject2 = columnArray.getJSONObject(i);
				FileBean fileBean = new FileBean();
				fileBean.setId(jsonObject2.getInt("id"));
				fileBean.setLabel(jsonObject2.getString("label"));
				fileBean.setpId(jsonObject2.getInt("pId"));
				columnList.add(fileBean);
			}
			list.add(columnList);

			// 封装新闻
			for (int i = 0; i < newsArray.length(); i++) {
				JSONObject jsonObject2 = newsArray.getJSONObject(i);
				NewsContentBean news = new NewsContentBean();
				news.setId(jsonObject2.getInt("id"));
				news.setAuthor(jsonObject2.getString("author"));
				news.setContent(jsonObject2.getString("content"));
				news.setcColumn(jsonObject2.getString("cColumn"));
				news.setpColumn(jsonObject2.getString("pColumn"));
				news.setStatus(Integer.parseInt(jsonObject2.getString("status")));
				news.setTime(jsonObject2.getString("time"));
				news.setTitle(jsonObject2.getString("title"));
				newsList.add(news);
			}
			list.add(newsList);

			// 封装用户
			User user = new User();
			user.setId(userObject.getInt("id"));
			user.setName(userObject.getString("name"));
			user.setStatus(userObject.getInt("status"));
			userList.add(user);
			list.add(userList);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
