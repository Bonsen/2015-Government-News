package com.news.checkNews;

import java.util.HashMap;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.news.R;
import com.news.close.SysApplication;
import com.news.code.NewsCode;
import com.news.http.HttpUtils;
import com.news.json.ObjectToJson;

public class CheckActivity extends Activity implements OnClickListener {

	private Button cancelButton;
	private Button checkButton;
	private int id=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_check);
		Intent intent = getIntent();
		HashMap<String, Object> map = (HashMap<String ,Object>)intent.getSerializableExtra("News");
		id=(Integer)map.get("id");
		cancelButton = (Button)findViewById(R.id.cancelButton);
		checkButton = (Button)findViewById(R.id.checkButton);
		cancelButton.setOnClickListener(this);
		checkButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.cancelButton:
			CheckActivity.this.finish();
			break;
		case R.id.checkButton:
			// …Û∫À–¬Œ≈
			new Thread(new Thread() {
				public void run() {
					try {
						HttpUtils.CheckNews(NewsCode.URL, ObjectToJson.CheckNews(id));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			CheckActivity.this.finish();
			break;
		}
	}
}
