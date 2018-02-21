package com.vshershnov.CurrencyEXService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyEurUahDao;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Service
public class CurrencyPairReaderServiceImpl implements CurrencyPairReaderService {
		
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
			String rateTime) {		
		return currencyEurUahDao.getRateByCurrencyToDate(fromCur, toCur, rateTime);		
	}

}
