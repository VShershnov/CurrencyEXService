package com.vshershnov.CurrencyEXService.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurrencyPair implements Serializable{	
	
	private static final long serialVersionUID = 6789541185994526799L;

	private int id;
	
	private String fromCurr;
	
	private String toCurr;
	
	private BigDecimal rate;

	private String rateTime;
	
	private String sourceID;	
	
	
	public CurrencyPair() {
		super();
	}

	public CurrencyPair(String fromCurr, String toCurr, Double rate, String rateTime, String sourceID) {
		super();
		this.fromCurr = fromCurr;
		this.toCurr = toCurr;
		this.rateTime = rateTime;
		this.rate = BigDecimal.valueOf(rate);
		this.sourceID = sourceID;		
	}

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

	public BigDecimal getRate() {
		return rate;
	}
	
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}	
	
	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String source) {
		this.sourceID = source;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurrencyPair [fromCurr=" + fromCurr + ", toCurr=" + toCurr
				+ ", rate=" + rate + ", rateTime=" + rateTime + ", sourceID="
				+ sourceID + "]";
	}

	
}