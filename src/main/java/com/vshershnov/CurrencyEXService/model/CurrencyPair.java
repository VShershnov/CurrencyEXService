package com.vshershnov.CurrencyEXService.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class CurrencyPair implements Serializable{	
	
	private static final long serialVersionUID = 6789541185994526799L;

	private int id;
	
	private String fromCurr;
	
	private String toCurr;
	
	private String rateTime;
	
	private Integer rate;
	
	private Date createdDate;
	
	private String sourceID;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}	
	
	public String getToCurr() {
		return toCurr;
	}

	public void setToCurr(String toCurr) {
		this.toCurr = toCurr;
	}

	public String getFromCurr() {
		return fromCurr;
	}
	
	public void setFromCurr(String name) {
		this.fromCurr = name;
	}
	
	public String getRateTime() {
		return rateTime;
	}

	public void setRateTime(String rateTime) {
		this.rateTime = rateTime;
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
	
	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String source) {
		this.sourceID = source;
	}

	@Override
	public String toString() {
		return "CurrencyPair [id=" + id + ", fromCurr=" + fromCurr + ", rate=" + rate
				+ ", createdDate=" + createdDate + "]";
	}
}