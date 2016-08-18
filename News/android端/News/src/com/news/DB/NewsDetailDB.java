package com.news.DB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

@SuppressLint("NewApi")
public class NewsDetailDB {
	private Context context;
	SQLiteDatabase database = null;
	private DBHelper dbHelper = null;;

	public NewsDetailDB(Context context) {
		dbHelper = new DBHelper(context);
		this.context = context;
	}

	// �������
	public void InsertDb(String title, String text, String time, String author,
			String pColumn, String cColumn) {
		database = dbHelper.getWritableDatabase();
		if (text.isEmpty()) {
			Toast.makeText(context, "���ֶβ���Ϊ��", Toast.LENGTH_LONG).show();
		} else {
			System.out.println("ִ�в������");
			String sql = "insert into newsdetail(title,context,time,author,pColumn,cColumn,status) values"
					+ "('"
					+ title
					+ "','"
					+ text
					+ "','"
					+ time
					+ "','"
					+ author
					+ "','"
					+ pColumn
					+ "','"
					+ cColumn
					+ "','"
					+ "δ��"
					+ "');";
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
	}

	// ��������
	@SuppressLint("NewApi")
	public void UpdateDb(String title, String text, int item_ID) {
		if (text.isEmpty()) {
			Toast.makeText(context, "���ֶβ���Ϊ��", Toast.LENGTH_LONG).show();
		} else {
			String sql = "update newsdetail set " + "context='" + text + "',"
					+ "title='" + title + "' where id='" + item_ID + "';";
			try {
				database = dbHelper.getWritableDatabase();
				database.execSQL(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (database != null) {
					database.close();
				}
			}

		}
	}

	// ��˹���
	public void CheckNews(int item_ID, int state) {
		String sql = "update newsdetail set " + "state='" + state
				+ "' where id='" + item_ID + "';";
		try {
			database = dbHelper.getWritableDatabase();
			database.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}

	}

	// ��ѯ����
	public Cursor QueryDb() {

		String sql = "select * from newsdetail";
		Cursor cursor = null;
		try {
			database = dbHelper.getReadableDatabase();
			cursor = database.rawQuery(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ִ����������");
		return cursor;
	}

	// ����id��ѯ����
	public Cursor QueryByNewsId(int item_ID) {

		String sql = "select * from newsdetail where id='" + item_ID + "';";
		Cursor cursor = null;
		try {
			database = dbHelper.getReadableDatabase();
			cursor = database.rawQuery(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cursor;

	}

	// ����pColumn��ѯ����
	public Cursor QueryByPColumn(String pColumn) {
		String sql = "select * from newsdetail where pColumn='" + pColumn
				+ "';";
		Cursor cursor = null;
		try {
			database = dbHelper.getReadableDatabase();
			cursor = database.rawQuery(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;

	}

	// ����cColumn��ѯ����
	public Cursor QueryByCColumn(String cColumn) {
		String sql = "select * from newsdetail where cColumn='" + cColumn
				+ "';";
		SQLiteDatabase database = null;
		Cursor cursor = null;
		try {
			database = dbHelper.getReadableDatabase();
			cursor = database.rawQuery(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;

	}

	public Cursor QueryByColumn(String pColumn, String cColumn,String author) {
		String sql = "select * from newsdetail where pColumn='" + pColumn
				+ "' and cColumn= '" + cColumn + "' and author ='" + author + "';";
		Cursor cursor = null;
		try {
			database = dbHelper.getReadableDatabase();
			cursor = database.rawQuery(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}

	// ����idɾ������
	public void DeleteById(int item_ID) {

		String sql = "delete from newsdetail where id='" + item_ID + "'";

		try {

			database = dbHelper.getWritableDatabase();
			database.execSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
	}

	public Cursor Search(String startTime, String endTime,String keyWord) {
		String sql="select * from newsdetail where title like '%"+ keyWord +"%' or context like '%"+ keyWord+"%' and  (time >"+startTime+" and time<"+endTime+")";
		Cursor cursor = null;
		try {
			database = dbHelper.getReadableDatabase();
			cursor = database.rawQuery(sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}
	
	public void CloseDb() {
		database.close();
	}
}
