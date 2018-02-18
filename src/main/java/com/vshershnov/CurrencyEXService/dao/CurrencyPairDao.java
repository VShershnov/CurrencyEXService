package com.vshershnov.CurrencyEXService.dao;

import java.util.List;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

public interface CurrencyPairDao {
	
		public void add(CurrencyPair currency);
		
		public CurrencyPair getRateByCurrency(String fromCur, String toCur);
		
		public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur, String date);

		public List<CurrencyPair> getAll();
}
