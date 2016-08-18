package com.news.start.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import com.example.news.R;
import com.news.close.SysApplication;
import com.news.login.activity.LoginActivity;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		final Intent localIntent = new Intent(this, LoginActivity.class);

		Timer timer = new Timer();
		TimerTask tast = new TimerTask() {
			@Override
			public void run() {
				startActivity(localIntent);
				StartActivity.this.finish();
			}
		};
		timer.schedule(tast, 2000);
	}
}
