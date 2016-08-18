package com.news.content.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.news.DB.NewsDetailDB;
import com.news.content.bean.NewsContentBean;

public class TestContent {
	
	
	private SQLiteDatabase db;
	private NewsDetailDB dop;
	
	public TestContent() {
		// TODO Auto-generated constructor stub
	}
	public NewsContentBean []GetNewsContents(Context context){
		
//		
//		dop = new NewsDetailDB(context, db);
//		dop.OpenOrCreateNewsDetailTable();
//		Cursor cursor=dop.QueryDb();
//		
		NewsContentBean []newsContentBean=new NewsContentBean[10];
//		newsContentBean[0]=new NewsContentBean("2014年全国海关科技工作会议材料","14-07-08","过生日托管人色更让他更让他...","待审", "c1", "c2", "a1");
//		newsContentBean[1]=new NewsContentBean("全国海关科技创新大会会议材料","12-10-15","过湿热通过人听说过团队","待审", "c1", "c2", "a1");
//		newsContentBean[2]=new NewsContentBean("2013年宣传协作员会议材料","12-08-05","过湿热个v润色过谁让他","驳回", "c1", "c2", "a1");
//		newsContentBean[3]=new NewsContentBean("2012海关科技工作研讨会会议材料","12-05-06","访问爱如风阿尔哈","待审", "c1", "c2", "a1");
//		newsContentBean[4]=new NewsContentBean("全国海关服务保障工作会议文件专辑","11-03-08","该法人是天如果","已审", "c1", "c2", "a1");
//		newsContentBean[5]=new NewsContentBean("工程阶段工作会议材料","11-02-02","阿尔官方人社厅过","已审", "c1", "c2", "a1");
//		newsContentBean[6]=new NewsContentBean("2013全国海关科技工作会议材料","12-08-05","个团上仍是铁人光荣头饰","已审", "c1", "c2", "a1");
//		newsContentBean[7]=new NewsContentBean("2015全国海关服务保障工作会议文件专辑","14-07-08","根特格瑞特根特根特...","已审", "c1", "c2", "a1");
//		newsContentBean[8]=new NewsContentBean("2015海关科技工作研讨会会议材料","12-10-15","热啊隔热隔热额","已审", "c1", "c2", "a1");
//		newsContentBean[9]=new NewsContentBean("2014全国海关科技创新大会会议材料","11-02-02","reagentreagent人额1...","已审", "c1", "c2", "a1");
		return newsContentBean;
	}
}
