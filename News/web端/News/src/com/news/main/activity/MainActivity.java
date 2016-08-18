package com.news.main.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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
import com.news.code.NewsCode;
import com.news.content.bo.ContentFragment;
import com.news.content.test.TestContentFragment;
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
	private ContentFragment cf;
	private TreeMenuFragment tmf;
	private TestContentFragment tcf;
	@SuppressWarnings("unchecked")
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		home = (Button) findViewById(R.id.home);
		check = (Button) findViewById(R.id.check);
		search = (Button) findViewById(R.id.search);
		add = (Button) findViewById(R.id.add);
		home.setOnClickListener(this);
		check.setOnClickListener(this);
		search.setOnClickListener(this);
		add.setOnClickListener(this);
		List<FileBean> fList = new ArrayList<FileBean>();
		ArrayList<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		//获得栏目数据
		fList=(List<FileBean>)getIntent().getSerializableExtra("fList");
		//获得新闻数据
		listMap=(ArrayList<Map<String, Object>>)getIntent().getSerializableExtra("listMap");
		setDefaultFragment(fList,listMap);
	}
	
	//用来初始化
	public void setDefaultFragment(List<FileBean> fList,ArrayList<Map<String, Object>> listMap) {
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
		new Thread(new Thread() {
			public void run() {
				Message msg = handler.obtainMessage();
				try {
					SharedPreferences sharedPreference =getApplicationContext().getSharedPreferences("ShareData", Context.MODE_PRIVATE);
					String name=sharedPreference.getString("author", "");
					String pColumn=sharedPreference.getString("pColumn", "");
					String cColumn=sharedPreference.getString("cColumn", "");
					msg.obj=DataProvider.GetNewsListByColumn(HttpUtils.GetNewsbyColumn(NewsCode.URL, ObjectToJson.GetNewsByColumn(cColumn,pColumn,name)));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendMessage(msg);
			}
		}).start();
	}
	
	Handler handler=new Handler()
	{
		@Override
		public void handleMessage(Message msg) 
		{
			ArrayList<Map<String, Object>>  list = (ArrayList<Map<String, Object>>) msg.obj;
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			if(list.size()>0)
			{
				//如果有数据
				ContentFragment contentFragment = new ContentFragment(list);
				transaction.replace(R.id.fragment_right, contentFragment,
						"contentFragment");
			}
			else
			{
				//如果没有数据
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
			tmf = new TreeMenuFragment();
			ft.replace(R.id.fragment_middle, tmf);
			cf = new ContentFragment();
			ft.replace(R.id.fragment_right, cf);
			ft.commit();
			break;

		case R.id.check:
			tmf = new TreeMenuFragment();
			tcf = new TestContentFragment();
			ft.replace(R.id.fragment_middle, tmf);
			ft.replace(R.id.fragment_right, tcf);
			ft.commit();
			break;
		case R.id.search:
			// 搜索的跳转
			Intent searchIntent = new Intent(MainActivity.this,
					SearchActivity.class);
			startActivity(searchIntent);
			break;
		case R.id.add:
			//判断是管理员还是用户
			SharedPreferences sharedPreferences=(getApplicationContext()).getSharedPreferences("ShareData", Context.MODE_PRIVATE);
			int UserStatus= sharedPreferences.getInt("UserStatus", 0);
			if(UserStatus==NewsCode.ADMIN)//如果是管理员
			{
				Toast.makeText(MainActivity.this, "管理员没有添加权限!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				intent.putExtra("editModel", "newsAdd");
				startActivity(intent);
			}
			break;
		}
	}
}