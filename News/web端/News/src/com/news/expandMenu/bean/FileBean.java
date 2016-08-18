package com.news.expandMenu.bean;

import java.io.Serializable;

import com.news.expandMenu.bo.treeviewutils.annotation.*;


public class FileBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TreeNodeId
	private int id;
	@TreeNodePid
	private int pId;
	@TreeNodeLabel
	private String label;
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public FileBean() {
	}

	public FileBean(int id, int pId, String label) {
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

