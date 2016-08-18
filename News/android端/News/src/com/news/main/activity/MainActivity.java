package com.news.main.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.news.R;
import com.news.addNews.activity.AddActivity;
import com.news.close.SysApplication;
import com.news.code.NewsCode;
import com.news.content.bo.ContentFragment;
import com.news.data.news.DataProvider;
import com.news.expandMenu.bean.FileBean;
import com.news.expandMenu.bo.TreeMenuFragment;
import com.news.http.HttpUtils;
import com.news.json.ObjectToJson;
import com.news.search.activity.SearchActivity;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {

	private Button home;
	private Button check;
	private Button search;
	private Button add;
	private Button set;
	private ContentFragment cf;
	private TreeMenuFragment tmf;

	@SuppressWarnings("unchecked")
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		home = (Button) findViewById(R.id.home);
		check = (Button) findViewById(R.id.check);
		search = (Button) findViewById(R.id.search);
		add = (Button) findViewById(R.id.add);
		set = (Button) findViewById(R.id.set);
		home.setOnClickListener(this);
		check.setOnClickListener(this);
		search.setOnClickListener(this);
		add.setOnClickListener(this);
		set.setOnClickListener(this);
		List<FileBean> fList = new ArrayList<FileBean>();
		ArrayList<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		// �����Ŀ����
		fList = (List<FileBean>) getIntent().getSerializableExtra("fList");
		// �����������
		listMap = (ArrayList<Map<String, Object>>) getIntent()
				.getSerializableExtra("listMap");
		
		for(int i=0;i<listMap.size();i++)
		{
			System.out.println("title is :"+listMap.get(i).get("title"));
		}
		setDefaultFragment(fList, listMap);
	}

	// ������ʼ��
	public void setDefaultFragment(List<FileBean> fList,
			ArrayList<Map<String, Object>> listMap) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		tmf = new TreeMenuFragment(fList);
		cf = new ContentFragment(listMap);
		ft.replace(R.id.fragment_middle, tmf);
		ft.replace(R.id.fragment_right, cf);
		ft.commit();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences sharedPreference = getApplicationContext()
				.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
		final SharedPreferences.Editor edit = sharedPreference.edit();
		final String name = sharedPreference.getString("author", "");
		final String search = sharedPreference.getString("search", "");
		final String startTime = sharedPreference.getString("startTime", "");
		final String endTime = sharedPreference.getString("endTime", "");
		final String keyWord = sharedPreference.getString("keyWord", "");
		//��ղ���
		edit.putString("search", "");
		edit.putString("startTime", "");
		edit.putString("endTime", "");
		edit.putString("keyWord", "");
		edit.commit();
		if (search != null && search != "") {
			new Thread(new Thread() {
				public void run() {
					Message msg = handler.obtainMessage();
					try {
						msg.obj = DataProvider.SearchNews(HttpUtils.SearchNews(
								NewsCode.URL, ObjectToJson.SearchNews(name,
										startTime, endTime, keyWord)));
						// �ֶ���ջ���

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendMessage(msg);
				}
			}).start();
		} else {
			new Thread(new Thread() {
				public void run() {
					Message msg = handler.obtainMessage();
					try {
						SharedPreferences sharedPreference = getApplicationContext()
								.getSharedPreferences("ShareData",
										Context.MODE_PRIVATE);
						final int UserStatus=sharedPreference.getInt("UserStatus", 10);
						//������Ñ�
						if(UserStatus==0)
						{
							String name = sharedPreference.getString("author", "");
							String pColumn = sharedPreference.getString("pColumn",
									"");
							String cColumn = sharedPreference.getString("cColumn",
									"");
							msg.obj = DataProvider.GetNewsListByColumn(HttpUtils
									.GetNewsbyColumn(NewsCode.URL,
											ObjectToJson.GetNewsByColumn(cColumn,
													pColumn, name)));
						}
						//����ǹ���T
						if(UserStatus==1)
						{
							System.out.println("����T��ԓ���@�l·�ð�");
							String pColumn = sharedPreference.getString("pColumn",
									"");
							String cColumn = sharedPreference.getString("cColumn",
									"");
							msg.obj = DataProvider.GetNewsListByColumnAmin(HttpUtils
									.GetNewsbyColumn(NewsCode.URL,
											ObjectToJson.GetNewsByColumnAdmin(cColumn,
													pColumn)));
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.sendMessage(msg);
				}
			}).start();
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) msg.obj;
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			if (list.size() > 0) {
				// ���������
				ContentFragment contentFragment = new ContentFragment(list);
				transaction.replace(R.id.fragment_right, contentFragment,
						"contentFragment");
			} else {
				// ���û������
				ContentFragment contentFragment = new ContentFragment();
				transaction.replace(R.id.fragment_right, contentFragment,
						"contentFragment");
			}
			transaction.commit();
		}
	};

	@Override
	public void onClick(View v) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		switch (v.getId()) {

		case R.id.home:
//			tmf = new TreeMenuFragment();
//			ft.replace(R.id.fragment_middle, tmf);
//			cf = new ContentFragment();
//			ft.replace(R.id.fragment_right, cf);
//			ft.commit();
			break;

		case R.id.check:
//			tmf = new TreeMenuFragment();
//			ft.replace(R.id.fragment_middle, tmf);
//			ft.commit();
			break;
		case R.id.search:
			// ��������ת
			Intent searchIntent = new Intent(MainActivity.this,
					SearchActivity.class);
			startActivity(searchIntent);
			break;
		case R.id.add:
			// �ж��ǹ���Ա�����û�
			SharedPreferences sharedPreferences = (getApplicationContext())
					.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
			int UserStatus = sharedPreferences.getInt("UserStatus", 0);
			if (UserStatus == NewsCode.ADMIN)// ����ǹ���Ա
			{
				Toast.makeText(MainActivity.this, "����Աû�����Ȩ��!",
						Toast.LENGTH_SHORT).show();
			} else {
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				intent.putExtra("editModel", "newsAdd");
				startActivity(intent);
			}
			break;

		case R.id.set:
			AlertDialog.Builder normalDia=new AlertDialog.Builder(MainActivity.this);  
	        normalDia.setMessage("ȷ���˳�ô��");  
	        normalDia.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub  
	            	SharedPreferences sharedPreference = getApplicationContext()
	        				.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
	            	SharedPreferences.Editor edit=sharedPreference.edit();
	            	edit.putString("start", "");
	            	edit.commit();
	            	SysApplication.getInstance().exit();
	            }  
	        });  
	        normalDia.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                // TODO Auto-generated method stub  
	            } 
	        });  
	        normalDia.create().show();
			break;
		}
	}
}