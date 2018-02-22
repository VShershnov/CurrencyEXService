package com.vshershnov.CurrencyEXService.dao;

import java.io.IOException;
import java.util.List;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

public interface CurrencyEurUahDao {
	
		public void add(CurrencyPair currency) throws IOException;
				
		public CurrencyPair getRateByCurrency(String fromCur, String toCur);
		
		public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur, String rateTime);

		public List<CurrencyPair> getAll();

		public CurrencyPair getByPK(int id);
}
