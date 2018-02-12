package com.vshershnov.CurrencyEXService.dao;

import java.util.Date;
import java.util.List;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

public interface CurrencyPairDao {
	
		public CurrencyPair create(String name, Integer rate, Date createdDate);
		
		public CurrencyPair getByPK(int key);
		
		public List<CurrencyPair> getAll();
		
		public void delete(CurrencyPair object);
		
		public void update(CurrencyPair object);

}
