package com.news.content.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.news.addNews.activity.AddActivity;
import com.news.checkNews.CheckActivity;
import com.news.code.NewsCode;
import com.news.content.activity.DealNewsActivity;
import com.news.content.bean.NewsContentBean;

@SuppressLint("ResourceAsColor")
public class ContentAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Map<String, Object>> listItem;
	private Context context;
	ViewHolder holder = null;
	int UserStatus;
	public ContentAdapter(ArrayList<Map<String, Object>> listItem2,
			Context context) {
		// TODO Auto-generated constructor stub
		this.mInflater = LayoutInflater.from(context);
		this.context = context;
		this.listItem = listItem2;
		SharedPreferences sharedPreferences=context.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
		UserStatus=sharedPreferences.getInt("UserStatus",0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			holder = new ViewHolder();
			// 可以理解为从vlist获取view 之后把view返回给ListView
			convertView = mInflater.inflate(R.layout.content_list_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.news_title);
			holder.context = (TextView) convertView
					.findViewById(R.id.news_thumbnail_content);
			holder.time = (TextView) convertView.findViewById(R.id.news_time);
			holder.status = (Button) convertView.findViewById(R.id.checkbutton);
			holder.tv_news_id = (TextView) convertView
					.findViewById(R.id.tv_news_id);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText((String) listItem.get(position).get("title"));
		holder.time.setText((String) listItem.get(position).get("time"));
		holder.context.setText((String) listItem.get(position).get("context"));
		if(String.valueOf(listItem.get(position).get("status")).equals("0"))
		{
			holder.status.setText("待审");
		}
		else
		{
			System.out.println("不是应该不能点击了么！");
			holder.status.setText("已审");
			holder.status.setTextColor(R.color.grey);
		}
		holder.tv_news_id.setText(String.valueOf( listItem.get(position).get("id")));
		holder.status.setTag(position);
		
		// 给Button添加单击事件 添加Button之后ListView将失去焦点 需要的直接把Button的焦点去掉
		holder.status.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//这里做一个判断，如果是用户的话就不能点击，如果是管理员的话就可以
				if(UserStatus==NewsCode.USER)
				{
					
				}
				else
				{
					if(String.valueOf(listItem.get(position).get("status")).equals("1"))
					{
						Toast.makeText(context, "不能重复审核！", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Intent intent = new Intent(context, CheckActivity.class);
						// 在意图中传递数据
						intent.putExtra("News",(HashMap<String,Object>)listItem.get(position));
						context.startActivity(intent);
					}
				}
			}
		});
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, AddActivity.class);
				intent.putExtra("editModel", "update");
				//在这里将数据发送过去就可以
				intent.putExtra("News",(HashMap<String,Object>)listItem.get(position));
				// 在意图中传递数据
				context.startActivity(intent);
			}
		});
		
		convertView.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(final View v) {
				Intent intent =new Intent(context,DealNewsActivity.class);
				intent.putExtra("NewsId", ((ViewHolder) v.getTag()).tv_news_id.getText());
				System.out.println("id is : "+((ViewHolder) v.getTag()).tv_news_id.getText());
				intent.putExtra("editModel", "update");
				intent.putExtra("UserStatus",UserStatus);
				context.startActivity(intent);
				return true;
			}
		});
		return convertView;
	}

	public final class ViewHolder {
		public TextView title;
		public TextView context;
		public TextView time;
		public TextView tv_news_id;
		public Button status;
	}

	/*
	 * public void showInfo(int position) {
	 * 
	 * ImageView img = new ImageView(context);
	 * img.setImageResource(R.drawable.ic_launcher); new
	 * AlertDialog.Builder(context) .setView(img) .setTitle("详情" + position)
	 * .setMessage( "地点：" + (String) listItem.get(position).get("title") +
	 * "   水:" + (String) listItem.get(position).get("info"))
	 * .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	 * 
	 * @Override public void onClick(DialogInterface dialog, int which) { }
	 * }).show(); }
	 */

	// 简单列表对话框，用于选择操作
	public void simpleList(final int item_id) {

	}
}
