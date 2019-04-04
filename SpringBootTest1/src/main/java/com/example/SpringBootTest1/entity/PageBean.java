package com.example.SpringBootTest1.entity;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {

	//当前页
	private long total;
	//当前页记录
	private List rows;
	
	public PageBean(long total, List rows) {
		this.setTotal(total);
		this.setRows(rows);
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
