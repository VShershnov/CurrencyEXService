package com.vshershnov.CurrencyEXService.service;

import java.util.List;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

public interface CurrencyPairReaderService {	
	
	public CurrencyPair getRateByCurrency(String fromCur, String toCur);
	
	public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur, String date);

	public List<CurrencyPair> getAll();
}
