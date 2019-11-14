package com.vshershnov.CurrencyEXService.service;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

import java.util.List;

public interface CurrencyPairReaderService {	
	
	public CurrencyPair getRateByCurrency(String fromCur, String toCur);
	
	public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur, String date);

	public List<CurrencyPair> getAll();
}
