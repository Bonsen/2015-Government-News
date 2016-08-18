package com.news.expandMenu.bo.treeviewutils;


import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	/*
	 * ���ڵ��pidΪ0
	 */
	private int pId = 0;
	private String name;
	// ���Ĳ㼶
	private int level;
	// �Ƿ�չ��
	private boolean isExpand = false;
	// ͼ��
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

	//�õ���ǰ�ڵ�Ĳ㼶
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

	// ���ڵ�
	private Node parent;
	// �ӽڵ�
	private List<Node> children = new ArrayList<Node>();
	
	
	//�Ƿ��Ǹ��ڵ�
	public boolean isRoot()
	{
		return parent ==null;
		
	}
	//�жϸ��ڵ��Ƿ�չ��
	public boolean isParentExpand()
	{
		if(parent==null)
		{
			return false;
		}
		return parent.isExpand();
	}
	
	//�Ƿ�Ҷ�ڵ�
	public boolean isLeaf()
	{
		return children.size()==0;
	}
	
	//�ҵ���ĳ�ڵ��Ҷ�ӽڵ�
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
