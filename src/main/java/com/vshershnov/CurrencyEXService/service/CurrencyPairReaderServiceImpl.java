package com.vshershnov.CurrencyEXService.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyEurUahDao;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Service
public class CurrencyPairReaderServiceImpl implements CurrencyPairReaderService {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairReaderServiceImpl.class);
	
	@Autowired
	private CurrencyEurUahDao currencyEurUahDao;

	@Override
	public List<CurrencyPair> getAll() {	
		return currencyEurUahDao.getAll();
	}	

	@Override
	public CurrencyPair getRateByCurrency(String fromCur, String toCur) {
		return currencyEurUahDao.getRateByCurrency(fromCur, toCur);
	}

	@Override
	public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur,
			String date) {		
		return currencyEurUahDao.getRateByCurrencyToDate(fromCur, toCur, date);		
	}

}
