package com.news.data.login;

import java.util.List;

import com.news.code.NewsCode;
import com.news.http.HttpUtils;
import com.news.json.JsonToObject;
import com.news.login.bean.User;

public class DataProvider {

	//返回用户数据，新闻栏目数据，新闻数据
	public static List<List<Object>> GetAllData(User user)
	{
		//返回的是三个对象的json数组，在这里进行剥离和封装
		return JsonToObject.GetInitData(HttpUtils.Login(NewsCode.URL,user));
	}
}
