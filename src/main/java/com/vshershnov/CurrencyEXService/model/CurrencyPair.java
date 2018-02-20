package com.vshershnov.CurrencyEXService.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurrencyPair implements Serializable{	
	
	private static final long serialVersionUID = 6789541185994526799L;

	private Integer id;
	
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

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	@Override
	public String toString() {
		return "CurrencyPair [fromCurr=" + fromCurr + ", toCurr=" + toCurr
				+ ", rate=" + rate + ", rateTime=" + rateTime + ", sourceID="
				+ sourceID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromCurr == null) ? 0 : fromCurr.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result
				+ ((rateTime == null) ? 0 : rateTime.hashCode());
		result = prime * result
				+ ((sourceID == null) ? 0 : sourceID.hashCode());
		result = prime * result + ((toCurr == null) ? 0 : toCurr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyPair other = (CurrencyPair) obj;
		if (fromCurr == null) {
			if (other.fromCurr != null)
				return false;
		} else if (!fromCurr.equals(other.fromCurr))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (rateTime == null) {
			if (other.rateTime != null)
				return false;
		} else if (!rateTime.equals(other.rateTime))
			return false;
		if (sourceID == null) {
			if (other.sourceID != null)
				return false;
		} else if (!sourceID.equals(other.sourceID))
			return false;
		if (toCurr == null) {
			if (other.toCurr != null)
				return false;
		} else if (!toCurr.equals(other.toCurr))
			return false;
		return true;
	}	
}