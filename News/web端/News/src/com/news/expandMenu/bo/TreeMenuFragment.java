package com.news.expandMenu.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.news.R;
import com.news.code.NewsCode;
import com.news.content.bo.ContentFragment;
import com.news.data.news.DataProvider;
import com.news.expandMenu.bean.FileBean;
import com.news.expandMenu.bo.adapter.SimpleTreeListViewAdapter;
import com.news.expandMenu.bo.treeviewutils.Node;
import com.news.expandMenu.bo.treeviewutils.adapter.TreeListViewAdapter.OnTreeNodeClickListener;
import com.news.http.HttpUtils;
import com.news.json.ObjectToJson;
import com.news.login.activity.LoginActivity;

@SuppressLint({ "NewApi", "ResourceAsColor", "ValidFragment" })
public class TreeMenuFragment extends Fragment {

//	private Handler handler = null;
	private ListView mTree;
	private SimpleTreeListViewAdapter<FileBean> mAdapter;
	private List<FileBean> mDatas=new ArrayList<FileBean>();
	private int last_item = -1;
	private RelativeLayout oldView;
	private String pColumn="";
	private String cColumn="";
	private String name="";
	private ProgressDialog mpDialog;
	
	TextView textView;
	TextView textView2;
	ImageView imageViewicon;
	ImageView imageViewicon2;
	public TreeMenuFragment(List<FileBean> mDatas)
	{
		this.mDatas=mDatas;
	}
	public TreeMenuFragment()
	{}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_treemenufragment,
				container, false);
		mTree = (ListView) view.findViewById(R.id.id_listview);
		mTree.setDividerHeight(0);
		try {
			// ����������
			mAdapter = new SimpleTreeListViewAdapter<FileBean>(
					mTree, getActivity(), mDatas, 0);
			mTree.setAdapter(mAdapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initEvent();
		return view;
	}

	private void initEvent() {
		mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
			@SuppressLint("CommitTransaction")
			@Override
			public void onClick(Node node, int position, View view) {
				RelativeLayout item = (RelativeLayout) view;
				// ����Ŀ���Ʊ��浽�����ļ���ȥ
				SharedPreferences sharedPreference = getActivity()
						.getSharedPreferences("ShareData", Context.MODE_PRIVATE);
				name=sharedPreference.getString("author", "");
				// �����ݽ��б༭
				SharedPreferences.Editor editor = sharedPreference.edit();
				
				// �����Ҷ�ӽڵ�
				if (node.isLeaf()) {
					editor.putString("pColumn", node.getParent().getName());
					pColumn=node.getParent().getName();
					editor.putString("cColumn", node.getName());
					cColumn=node.getName();
				} else// �Ǹ��ڵ�
				{
					editor.putString("pColumn", node.getName());
					pColumn=node.getName();
					editor.putString("cColumn", Node.FindLeafNode(node).getName());
					cColumn=Node.FindLeafNode(node).getName();
				}
				// �����ݳ־û����洢������
				editor.commit();
				
				System.out.println("pColumn is : "+pColumn);
				System.out.println("cColumn is : "+cColumn);
				
				textView = (TextView) item.findViewById(R.id.id_item_text);
				textView.setTextColor(getResources().getColor(
						R.color.expandmenutextchoisecolor));
				item.setBackgroundResource(R.drawable.expandmenu_item_bg);// �ѵ�ǰѡ�е���Ŀ����ѡ��Ч��
				imageViewicon = (ImageView) item.findViewById(R.id.imglabel);
				imageViewicon.setImageResource(R.drawable.expandlabel_blue);
				if (last_item != -1 && last_item != position) {// ����Ѿ���������Ŀ�����ϴα����itemλ�ú͵�ǰλ�ò�ͬ
					oldView.setBackgroundResource(R.color.expandinitcolor);// ���ϴ�ѡ�е���ʽȥ��
					// ����������ɫ
					textView2 = (TextView) oldView
							.findViewById(R.id.id_item_text);
					textView2.setTextColor(getResources().getColor(
							R.color.expandmenutextcolor));
					// ����label��ɫ
					imageViewicon2 = (ImageView) oldView
							.findViewById(R.id.imglabel);
					imageViewicon2.setImageResource((R.drawable.expandlabel));
				}
				oldView = item;// �ѵ�ǰ����Ŀ��������
				last_item = position;// �ѵ�ǰ��λ�ñ�������
				
				mpDialog = new ProgressDialog(getActivity());
				mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				mpDialog.setMessage("loading......");
				mpDialog.setCancelable(false);
				mpDialog.show();
				//����һ���̣߳���������
				new Thread(new Thread() {
					public void run() {
						Message msg = handler.obtainMessage();
						try {
							msg.obj=DataProvider.GetNewsListByColumn(HttpUtils.GetNewsbyColumn(NewsCode.URL, ObjectToJson.GetNewsByColumn(cColumn,pColumn,name)));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handler.sendMessage(msg);
					}
				}).start();
			}
		});
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
				//���������
				ContentFragment contentFragment = new ContentFragment(list);
				transaction.replace(R.id.fragment_right, contentFragment,
						"contentFragment");
			}
			else
			{
				//���û������
				ContentFragment contentFragment = new ContentFragment();
				transaction.replace(R.id.fragment_right, contentFragment,
						"contentFragment");
			}
			transaction.commit();
			mpDialog.dismiss();
		}
	};
}
