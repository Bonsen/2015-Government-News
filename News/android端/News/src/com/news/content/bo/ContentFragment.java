package com.news.content.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.news.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.news.code.NewsCode;
import com.news.content.bean.NewsContentBean;

@SuppressLint({ "NewApi", "ValidFragment" })
public class ContentFragment extends Fragment {
	//
	private PullToRefreshListView mPullRefreshListView;
	private ArrayList<Map<String, Object>> listItem ;
	private static ArrayList<Map<String, Object>> listItem2 = new ArrayList<Map<String, Object>>();
	ContentAdapter contentAdapter = null;
	private TextView newscount;
	private String cColumn ;
	private static int newsCode;
	private NewsContentBean newsContentBean;
	
	
	public ContentFragment(){}
	public ContentFragment(NewsContentBean newsContentBean,int newsCode)
	{
		this.newsContentBean=newsContentBean;
		this.newsCode=newsCode;
	}
	public ContentFragment(ArrayList<Map<String, Object>> listItem)
	{
		//进行初始化工作
		this.listItem2=listItem;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_contentfargment, null);
		SharedPreferences sharedPreferences = (getActivity()).getSharedPreferences("ShareData", Context.MODE_PRIVATE);
		cColumn = sharedPreferences.getString("cColumn", "");
		listItem = new ArrayList<Map<String, Object>>();
		//找出本栏目的数据
		for(int i=0;i<listItem2.size();i++)
		{
			if(listItem2.get(i).get("cColumn").equals(cColumn))
			{
				listItem.add(listItem2.get(i));
			}
		}
//		if(newsCode==NewsCode.ADD_NEWS)
//		{
//			Map<String ,Object> map=new HashMap<String,Object>();
//		
//			map.put("id", (newsContentBean.getId()));
//			map.put("author", (newsContentBean.getAuthor()));
//			map.put("content", (newsContentBean.getContent()));
//			map.put("cColumn", (newsContentBean.getcColumn()));
//			map.put("pColumn", (newsContentBean.getpColumn()));
//			map.put("status",(newsContentBean.getStatus()));
//			map.put("time", (newsContentBean.getTime()));
//			map.put("title", (newsContentBean.getTitle()));
//			listItem.add(map);
//		}
//		if(newsCode==NewsCode.UPDATE_NEWS)
//		{
//			for(int i=0;i<listItem.size();i++)
//			{
//				if(Integer.parseInt((String) listItem.get(i).get("id"))==newsContentBean.getId())
//				{
//					listItem.get(i).put("content", (newsContentBean.getContent()));
//					listItem.get(i).put("title", (newsContentBean.getTitle()));
//				}
//			}
//		}
		
		// 更具栏目名称对对标题进行设置
		TextView tv = (TextView) view.findViewById(R.id.column);
		tv.setText(cColumn);
		mPullRefreshListView = (PullToRefreshListView) view
				.findViewById(R.id.pull_refresh_list);
		// 设定下拉监听函数
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(
							PullToRefreshBase<ListView> refreshView) {
						String label = DateUtils.formatDateTime(getActivity()
								.getApplicationContext(), System
								.currentTimeMillis(),
								DateUtils.FORMAT_SHOW_TIME
										| DateUtils.FORMAT_SHOW_DATE
										| DateUtils.FORMAT_ABBREV_ALL);

						// Update the LastUpdatedLabel
						refreshView.getLoadingLayoutProxy()
								.setLastUpdatedLabel(label);

						// Do work to refresh the list here.
						new GetDataTask().execute();
					}
				});

		mPullRefreshListView.setMode(Mode.PULL_FROM_START);// 设置底部下拉刷新模式

//		// 如果是搜索界面传过来的值，进行搜索处理
		try {
//			Bundle bundle;
//			if(getArguments()==null)
//			{
//				bundle=new Bundle();
//				System.out.println("getArguments is null");
//			}
//			else
//			{
//				bundle = getArguments();
//			}
//			
//			if (bundle.getString("search","")!="") {
//				//进行搜索操作
//				listItem = (ArrayList<Map<String, Object>>) DataProvider
//						.showSearchNewsList(getActivity(),
//								bundle.getString("startTime"),
//								bundle.getString("endTime"),
//								bundle.getString("keyWord"));
//			} 
//			else// 否则正常处理
//			{
//				listItem = (ArrayList<Map<String, Object>>) DataProvider
//						.showNewsList(getActivity());
//			}
			
			newscount = (TextView) view.findViewById(R.id.newscount);
			newscount.setText(listItem.size() + "条新闻");
			ListView actualListView = mPullRefreshListView.getRefreshableView();
			contentAdapter = new ContentAdapter(listItem, getActivity());
			actualListView.setAdapter(contentAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	private class GetDataTask extends
			AsyncTask<Void, Void, HashMap<String, Object>> {

		// 后台处理部分
		@Override
		protected HashMap<String, Object> doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {

				map = new HashMap<String, Object>();
				map.put("title", "手动阀手动阀");
				map.put("content", "a 阿三发射点发射点");
				map.put("time", "2014");
				map.put("status", "1");

			} catch (Exception e) {
				// TODO: handle exception
				System.out.print("error!");
				return null;
			}
			return map;
		}

		@Override
		protected void onPostExecute(HashMap<String, Object> result) {
			// 在头部增加新添内容
			try {
				listItem.add(0, result);// 监听的是指向，不能new
				// 通知程序数据集已经改变，如果不做通知，那么将不会刷新
				contentAdapter.notifyDataSetChanged();
				// Call onRefreshComplete when the list has been refreshed.
				mPullRefreshListView.onRefreshComplete();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.print(e.getMessage());
			}
			super.onPostExecute(result);
		}
	}
}
