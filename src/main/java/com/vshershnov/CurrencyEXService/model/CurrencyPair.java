package com.vshershnov.CurrencyEXService.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class CurrencyPair implements Serializable{	
	
	private static final long serialVersionUID = 6789541185994526799L;

	private int id;
	
	private String name;
	
	private Integer rate;
	
	private Date createdDate;
	
	private String source;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getRate() {
		return rate;
	}
	
	public void setRate(Integer rate) {
		this.rate = rate;
	}	
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}	
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "CurrencyPair [id=" + id + ", name=" + name + ", rate=" + rate
				+ ", createdDate=" + createdDate + "]";
	}
}
