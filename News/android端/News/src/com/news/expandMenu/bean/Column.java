package com.news.expandMenu.bean;

public class Column {
	private int id;
	private int pId;
	private String label;
	private String desc;

	public Column(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Column(int id, int pId, String label) {
		super();
		this.id = id;
		this.pId = pId;
		this.label = label;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

