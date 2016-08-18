package com.news.search.activity;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;

import com.example.news.R;
import com.news.close.SysApplication;
import com.news.main.activity.MainActivity;

public class SearchActivity extends Activity implements OnDateChangedListener {

	private DatePicker datePickerStart;
	private DatePicker datePickerEnd;
	private EditText searchKey;
	private Button sure_button;
	private Button cancel_button;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_search);
		datePickerStart = (DatePicker) findViewById(R.id.datePickerStart);
		datePickerEnd = (DatePicker) findViewById(R.id.datePickerEnd);
		searchKey = (EditText) findViewById(R.id.searchkey);
		sure_button = (Button) findViewById(R.id.sure_button);
		cancel_button = (Button) findViewById(R.id.cancel_button);
		datePickerStart.init(2011, 1, 1, this);
		Calendar c = Calendar.getInstance();
		datePickerEnd.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), this);
		System.out.println("year is : " + c.get(Calendar.YEAR)
				+ " ; month is : " + c.get(Calendar.MONTH) + " ; day is : "
				+ c.get(Calendar.MONTH));
		sure_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 当点击按钮的时候需要在这里获得关键字和时间，默认都是搜索未审核的新闻

				// if((datePickerEnd.getYear() + "-"+ datePickerEnd.getMonth()+
				// datePickerEnd.getDayOfMonth())<(datePickerStart.getYear() +
				// "-"+ datePickerStart.getMonth()+
				// datePickerStart.getDayOfMonth()))
				// {
				// //结束时间不能早于开始时间
				//
				// }
				if (searchKey.getText().toString() == null||searchKey.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "关键字不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					SharedPreferences sharedPreference =getApplicationContext().getSharedPreferences("ShareData", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor=sharedPreference.edit();
					editor.putString("endTime", datePickerEnd.getYear() + "-"
							+ datePickerEnd.getMonth()
							+ "-"+datePickerEnd.getDayOfMonth());
					editor.putString("startTime", datePickerStart.getYear()
							+ "-" + datePickerStart.getMonth()+"-"
							+ datePickerStart.getDayOfMonth());
					editor.putString("keyWord", searchKey.getText().toString());
					editor.putString("search", "search");
					editor.commit();
					SearchActivity.this.finish();
				}
			}
		});

		cancel_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SearchActivity.this.finish();
			}
		});
	}

	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}
}
