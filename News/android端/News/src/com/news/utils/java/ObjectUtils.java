package com.news.utils.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.news.content.bean.NewsContentBean;

public class ObjectUtils {

	//将map中的数据封装到person中去
	public static List<NewsContentBean> MapsToNewsContentDetails(List<Map<Object,Object>> listMap)
	{
		List<NewsContentBean> newsContentDetails = new ArrayList<NewsContentBean>();
		for(int i=0;i<listMap.size();i++)
		{
			NewsContentBean newsContentDetail=new NewsContentBean();
			newsContentDetail.setStatus(Integer.parseInt(listMap.get(i).get("status").toString()));
			newsContentDetail.setTitle((String)listMap.get(i).get("title"));
			newsContentDetail.setContent((String)listMap.get(i).get("content"));
			newsContentDetail.setcColumn((String)listMap.get(i).get("cColumn"));
			newsContentDetail.setpColumn((String)listMap.get(i).get("pColumn"));
			newsContentDetail.setAuthor((String)listMap.get(i).get("author"));
			newsContentDetail.setId((Integer.parseInt((String)listMap.get(i).get("id"))));
			newsContentDetails.add(newsContentDetail);
		}
		return newsContentDetails;
	}
}
