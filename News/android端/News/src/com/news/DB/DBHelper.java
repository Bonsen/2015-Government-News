package com.news.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
	private static String name="News.db";//���ݿ������
	private static int version=1;//���ݿ�İ汾��
	public DBHelper(Context context) {
		super(context, name, null, version);
	}

	//�����ݿⴴ����ʱ���ǵ�һ�α�ִ�У���ɶ����ݿ�Ĵ���
	//������ڵ�һ�ΰ�װ���ֻ��ϵ�ʱ������һ�Σ��Ժ󶼲���������
	@Override
	public void onCreate(SQLiteDatabase db) {
		//�����ﴴ�����������ݿ��
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
