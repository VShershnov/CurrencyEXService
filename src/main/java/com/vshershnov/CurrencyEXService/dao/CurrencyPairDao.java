package com.vshershnov.CurrencyEXService.dao;

import java.util.List;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

public interface CurrencyPairDao {
	
		public void create(CurrencyPair currency);
		
		public CurrencyPair getByPK(int key);
		
		public List<CurrencyPair> getAll();
		
		public void delete(CurrencyPair object);
		
		public void update(CurrencyPair object);

}
