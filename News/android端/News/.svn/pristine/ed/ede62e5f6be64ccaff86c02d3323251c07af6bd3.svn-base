package com.news.expandMenu.bo.treeviewutils.adapter;

import java.util.List;

import com.news.expandMenu.bo.treeviewutils.Node;
import com.news.expandMenu.bo.treeviewutils.TreeHelper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class TreeListViewAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<Node> mAllNodes;
	protected List<Node> mVisibleNodes;
	protected LayoutInflater mInflater;
	protected ListView mTree;
	
	// 设置node的点击回调
	public interface OnTreeNodeClickListener {
		void onClick(Node node, int position,View view);
	}

	private OnTreeNodeClickListener mListener;

	public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
		this.mListener = mListener;
	}

	public TreeListViewAdapter(ListView tree, Context context, List<T> datas,
			int defaultExpandLevel) throws IllegalAccessException,
			IllegalArgumentException {
		mContext = context;
		mInflater=LayoutInflater.from(mContext);
		mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
		mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
		mTree = tree;
		mTree.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				expandOrCollapse(position);
				if (mListener != null) {
					mListener.onClick(mVisibleNodes.get(position), position,view);
				}
			}
		});
	}

	private void expandOrCollapse(int position) {
		Node n = mVisibleNodes.get(position);
		if (n != null) {
			if (n.isLeaf()) {
				return;
			}
			n.setExpand(!n.isExpand());
			mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mVisibleNodes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mVisibleNodes.get(position);
	}

	// 传回的是真正对象的id
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mVisibleNodes.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Node node = mVisibleNodes.get(position);
		convertView = getConvertView(node, position, convertView, parent);
		// 设置内边距
		convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
		return convertView;
	}

	public abstract View getConvertView(Node node, int position,
			View convertView, ViewGroup parent);
}
