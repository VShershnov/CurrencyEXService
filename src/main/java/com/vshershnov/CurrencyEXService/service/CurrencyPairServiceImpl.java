package com.vshershnov.CurrencyEXService.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyPairDao;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Service
public class CurrencyPairServiceImpl implements CurrencyPairService {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairServiceImpl.class);
	
	@Autowired
	private CurrencyPairDao currencyPairDao;

	@Override
	public void create(CurrencyPair currency) {
		currencyPairDao.create(currency);
	}

	@Override
	public CurrencyPair getByPK(int key) {		
		return currencyPairDao.getByPK(key);
	}

	@Override
	public List<CurrencyPair> getAll() {		
		return currencyPairDao.getAll();
	}

	@Override
	public void delete(CurrencyPair currency) {
		currencyPairDao.delete(currency);
	}

	@Override
	public void update(CurrencyPair currency) {
		currencyPairDao.update(currency);
	}


}
