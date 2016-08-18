package com.news.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.news.code.NewsCode;
import com.news.login.bean.User;

public class CreateJson {

	public static String CreateUserJson(User user) throws JSONException
	{
		JSONObject jObject=new JSONObject();
		jObject.put("name", user.getName());
		jObject.put("password", user.getPassword());
		jObject.put("NewsCode", NewsCode.LOGIN);
		return jObject.toString();
	}
}
