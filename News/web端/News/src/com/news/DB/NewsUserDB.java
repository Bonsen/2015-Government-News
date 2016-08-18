package com.news.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class NewsUserDB {
	private Context context;
	SQLiteDatabase database = null;
	private DBHelper dbHelper = null;;

	public NewsUserDB(Context context) {
		dbHelper = new DBHelper(context);
		this.context = context;
	}

	// �������
	@SuppressLint("NewApi")
	public void InsertDb(String name, int status,String password) {
		database = dbHelper.getWritableDatabase();
		String sql = "insert into user(name,password,status) values" + "('" + name
				+ "','" + password +"','" + status + "');";
		try {
			database.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
	}

	// ����id��ѯ����
	public Cursor QueryByName(String name) {
		Cursor cursor = database.rawQuery("select * from user where name='"
				+ name + "';", null);
		return cursor;
	}

	// �ر����ݿ�
	public void CloseDb() {
		database.close();
	}
}
