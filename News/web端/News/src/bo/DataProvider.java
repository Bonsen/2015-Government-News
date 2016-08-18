package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import json.JsonToObject;
import json.ObjectToJson;

import Code.OperateCode;
import bean.Column;
import bean.News;
import bean.User;
import dao.DBOperate;

public class DataProvider {
	public static News getNews() {
		News news = new News("title1", "content1", "2015-1-1", "zyy1",
				"pColumn1", "cColumn1");
		return news;
	}

	public static List<News> getNewsList() {
		List<News> list = new ArrayList<News>();
		News news1 = new News("title1", "content1", "2015-1-1", "zyy1",
				"pColumn1", "cColumn1");
		News news2 = new News("title2", "content2", "2015-2-2", "zyy2",
				"pColumn2", "cColumn2");
		list.add(news1);
		list.add(news2);
		return list;
	}

	// 将新的新闻数据回传
	public static String FlushAddData(News news) throws SQLException {
		return CreateJSONString.createJsonString(
				String.valueOf(OperateCode.ADD_NEWS),
				DBOperate.GetNewsByAuthorAndColumn(news.getAuthor(),
						news.getpColumn(), news.getcColumn()));
	}

	//管理员登陆时返回的数据
	public static String ProviderInitDataAdmin(User user) throws SQLException {
		// 获得栏目
		List<Column> cList = DBOperate.GetColumn();
		Column column = cList.get(0);
		// 获得初始化栏目
		String pColumn = cList.get(0).getLabel();
		for (int i = 1; i < cList.size(); i++) {
			if (column.getId() == cList.get(i).getpId()) {
				column = cList.get(i);
				break;
			}
		}
		String cColumn = column.getLabel();
		// 获得初始化新闻
		List<News> nList = DBOperate.GetNews(
				pColumn, cColumn);
		// 将三种类型的数据打包成json
		return CreateJSONString.CreateTreeJsonString(cList, nList, user);
	}

	public static String ProviderInitDataUser(User user) throws SQLException {
		// 获得栏目
		List<Column> cList = DBOperate.GetColumnByUser(user);
		Column column = cList.get(0);
		// 获得初始化栏目
		String pColumn = cList.get(0).getLabel();
		for (int i = 1; i < cList.size(); i++) {
			if (column.getId() == cList.get(i).getpId()) {
				column = cList.get(i);
				break;
			}
		}
		String cColumn = column.getLabel();
		// 获得初始化新闻
		List<News> nList = DBOperate.GetNewsByAuthorAndColumn(user.getName(),
				pColumn, cColumn);
		// 将三种类型的数据打包成json
		return CreateJSONString.CreateTreeJsonString(cList, nList, user);
	}

	public static String GetNewsByColumn(String jsonString) throws SQLException {
		News news = new News();
		news = JsonToObject.GetNews(jsonString);
		System.out.println("name is : "+ news.getAuthor());
		System.out.println("pColumn is : "+ news.getpColumn());
		System.out.println("cColumn is : "+ news.getcColumn());
		List<News>  newsList=new ArrayList<News>();
		//获得新闻数组
		newsList=DBOperate.GetNewsByAuthorAndColumn(news.getAuthor(), news.getpColumn(),
				news.getcColumn());
		System.out.println("newsList size is : "+newsList.size());
		return ObjectToJson.createJsonString("news",newsList );
	}
	//管理员通过栏目获得所有的新闻列表
	public static String GetNewsByColumnAdmin(String jsonString) throws SQLException {
		News news = new News();
		news = JsonToObject.GetNewsAdmin(jsonString);
		List<News>  newsList=new ArrayList<News>();
		//获得新闻数组
		newsList=DBOperate.GetNews(news.getpColumn(),
				news.getcColumn());
		return ObjectToJson.createJsonString("news",newsList );
	}
}
