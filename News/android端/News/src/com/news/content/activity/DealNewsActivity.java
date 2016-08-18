package com.news.content.activity;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.news.R;
import com.news.addNews.activity.AddActivity;
import com.news.close.SysApplication;
import com.news.code.NewsCode;
import com.news.data.news.DataProvider;

//这个类主要是用来处理长按新闻列表项之后的事务逻辑
public class DealNewsActivity extends Activity {

	private Button delButton;
	private Button editButton;
	private int UserStatus;
	int item_id;
	String editModel;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_deal_news);
		intent = getIntent();
		item_id = Integer.parseInt((String) intent.getStringExtra("NewsId"));

		System.out.println("item_id is : " + item_id);
		UserStatus = intent.getIntExtra("UserStatus", 0);
		editModel = (String) intent.getStringExtra("editModel");
		delButton = (Button) findViewById(R.id.delButton);
		editButton = (Button) findViewById(R.id.editButton);

		editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(DealNewsActivity.this,
						AddActivity.class);
				intent2.putExtra("editModel", editModel);
				intent2.putExtra("NewsId",
						(String) intent.getStringExtra("NewsId"));
				startActivity(intent2);
				DealNewsActivity.this.finish();
			}
		});

		delButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 弹出对话框表示没有权限操作
				if (UserStatus == NewsCode.USER) {
					Toast.makeText(DealNewsActivity.this,
							"对不起，您没有操作权限，请与管理员取得联系！", Toast.LENGTH_LONG).show();
					DealNewsActivity.this.finish();
				} else {
					new Thread(new Thread() {
						public void run() {
							try {
								DataProvider.DeleteNews(item_id);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
							}
						}
					}).start();
					DealNewsActivity.this.finish();
				}

			}
		});
	}
}