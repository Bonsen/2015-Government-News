package bo;


import org.json.JSONException;
import org.json.JSONObject;

import bean.News;
import bean.User;


public class JsonTools {
	public static News getNews(String jsonString) {
		News news = new News();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			news.setAuthor(jsonObject.getString("author"));
			news.setcColumn(jsonObject.getString("cColumn"));
			news.setContent(jsonObject.getString("content"));
			news.setStatus(jsonObject.getInt("status"));
			news.setTime(jsonObject.getString("time"));
			news.setTitle(jsonObject.getString("title"));
			news.setpColumn(jsonObject.getString("pColumn"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return news;
	}
	
	//返回user对象
	public static User GetUser(String jsonString) {
		User user = new User();
		System.out.println("test");
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			user.setName(jsonObject.getString("name"));
			user.setPassword(jsonObject.getString("password"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
	
	
	public static News UpdateNews(String jsonString) {
		News news = new News();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			news.setContent(jsonObject.getString("content"));
			news.setTitle(jsonObject.getString("title"));
			news.setId(jsonObject.getInt("id"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return news;
	}
	
	
	
	//获得NewsCode
	public static int GetNewsCode(String jsonString) throws JSONException {
			JSONObject jsonObject = new JSONObject(jsonString);
			jsonObject.getString("NewsCode");
		return Integer.parseInt(jsonObject.getString("NewsCode"));
	}
}
