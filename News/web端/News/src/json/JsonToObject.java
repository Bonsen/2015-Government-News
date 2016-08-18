package json;

import org.json.JSONObject;

import bean.News;
import bean.Search;

public class JsonToObject {

	public static News GetNews(String jsonString) {
		News news = new News();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			news.setAuthor(jsonObject.getString("name"));
			news.setcColumn(jsonObject.getString("cColumn"));
			news.setpColumn(jsonObject.getString("pColumn"));
			news.setContent(jsonObject.getString("content"));
			news.setStatus(jsonObject.getInt("status"));
			news.setTime(jsonObject.getString("time"));
			news.setTitle(jsonObject.getString("title"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return news;
	}
	
	
	public static News GetNewsAdmin(String jsonString) {
		News news = new News();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			news.setcColumn(jsonObject.getString("cColumn"));
			news.setpColumn(jsonObject.getString("pColumn"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return news;
	}
	
	public static Search SearchNews(String jsonString) {
		Search search = new Search();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			search.setName(jsonObject.getString("name"));
			search.setKeyWord(jsonObject.getString("keyWord"));
			search.setStartTime(jsonObject.getString("startTime"));
			search.setEndTime(jsonObject.getString("endTime"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return search;
	}
	
	public static int GetId(String jsonString) {
		int id =0;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			id =jsonObject.getInt("id");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}
}
