package com.vshershnov.CurrencyEXService.service;

import java.util.List;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

public interface CurrencyPairService {	
	
	public void create(CurrencyPair currency);
	
	public CurrencyPair getByPK(int key);
	
	public List<CurrencyPair> getAll();
	
	public void delete(CurrencyPair currency);
	
	public void update(CurrencyPair currency);
		
}
