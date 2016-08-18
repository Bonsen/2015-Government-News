package com.news.expandMenu.bo.treeviewutils;


import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	/*
	 * 根节点的pid为0
	 */
	private int pId = 0;
	private String name;
	// 树的层级
	private int level;
	// 是否展开
	private boolean isExpand = false;
	// 图标
	private int icon;

	
	public Node()
	{
		
	}
	
	
	public Node(int id, int pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//得到当前节点的层级
	public int getLevel() {
		
		return parent==null?0:parent.getLevel()+1;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isExpand() {
		return isExpand;
	}

	public void setExpand(boolean isExpand) {
		
		this.isExpand = isExpand;
		if(!isExpand)
		{
			for(Node node:children)
			{
				node.setExpand(false);
			}
		}
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	// 父节点
	private Node parent;
	// 子节点
	private List<Node> children = new ArrayList<Node>();
	
	
	//是否是根节点
	public boolean isRoot()
	{
		return parent ==null;
		
	}
	//判断父节点是否展开
	public boolean isParentExpand()
	{
		if(parent==null)
		{
			return false;
		}
		return parent.isExpand();
	}
	
	//是否叶节点
	public boolean isLeaf()
	{
		return children.size()==0;
	}
	
	//找到到某节点的叶子节点
	public static Node FindLeafNode(Node node)
	{
		
		if(!node.isLeaf()&&node!=null)
		{			
			return	FindLeafNode(node.getChildren().get(0));
		}
		else
		{
			return node;
		}
	}
}
