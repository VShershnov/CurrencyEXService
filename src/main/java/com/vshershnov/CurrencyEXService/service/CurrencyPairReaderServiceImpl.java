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
public class CurrencyPairReaderServiceImpl implements CurrencyPairReaderService {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairReaderServiceImpl.class);
	
	@Autowired
	private CurrencyPairDao currencyPairDao;

	@Override
	public List<CurrencyPair> getAll() {	
		return currencyPairDao.getAll();
	}	

	@Override
	public CurrencyPair getRateByCurrency(String fromCur, String toCur) {
		return currencyPairDao.getRateByCurrency(fromCur, toCur);
	}

	@Override
	public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur,
			String date) {		
		return currencyPairDao.getRateByCurrencyToDate(fromCur, toCur, date);		
	}

}
