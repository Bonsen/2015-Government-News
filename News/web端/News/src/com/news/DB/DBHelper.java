package com.news.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
	private static String name="News.db";//数据库的名称
	private static int version=1;//数据库的版本号
	public DBHelper(Context context) {
		super(context, name, null, version);
	}

	//当数据库创建的时候，是第一次被执行，完成对数据库的创建
	//当程序第第一次安装在手机上的时候运行一次，以后都不会运行了
	@Override
	public void onCreate(SQLiteDatabase db) {
		//在这里创建各样的数据库表
		db.execSQL("CREATE TABLE IF NOT EXISTS newsdetail(id integer primary key autoincrement,title text,context text,time varchar(32),author varchar(32),pColumn varchar(32),cColumn varchar(32),status varchar(16))");
		db.execSQL("create table if not exists user(id integer primary key autoincrement , name varchar(16),password varchar(32),status integer)");//status 0 is normal user ,1 is administrator
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS newsdetail");
		db.execSQL("DROP TABLE IF EXISTS user");
		onCreate(db);
	}
}
