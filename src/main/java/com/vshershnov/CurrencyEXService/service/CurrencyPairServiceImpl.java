package com.vshershnov.CurrencyEXService.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyPairDao;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Service
public class CurrencyPairServiceImpl implements CurrencyPairService {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairServiceImpl.class);
	
	private CurrencyPairDao currencyPairDao;	

	public void setCurrencyPairDao(CurrencyPairDao currencyPairDao) {
		this.currencyPairDao = currencyPairDao;
	}

	@Override
	public CurrencyPair create(String name, Integer rate, Date createdDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyPair getByPK(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrencyPair> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CurrencyPair object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CurrencyPair object) {
		// TODO Auto-generated method stub
		
	}


}
