package com.vshershnov.CurrencyEXService.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.utils.TimestampUtils;

@Repository
public class CurrencyPairDaoImpl implements CurrencyPairDao{

	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairDaoImpl.class);
	
	//private SessionFactory sessionFactory;
	
	@Autowired
	private TimestampUtils timestampUtils;
	
	
	private List<CurrencyPair> currencies = new ArrayList<CurrencyPair>(Arrays.asList(new CurrencyPair("usd", "uah", 26.85, "2018-02-13", "temp"),
														new CurrencyPair("usd", "uah", 26.55, "2018-02-14", "temp"),
														new CurrencyPair("usd", "uah", 26.75, "2018-02-13'T'09:00'Z'", "temp")));	
	
	
	public void setTimestampUtils(TimestampUtils timestampUtils) {
		this.timestampUtils = timestampUtils;
	}
	
	
	@Override
	public void add(CurrencyPair currency) {
		// TODO Auto-generated method stub
		logger.info("DAO. add: " + currency);
		currencies.add(currency);
	}	

	@Override
	public List<CurrencyPair> getAll() {
		// TODO Auto-generated method stub
		logger.info("DAO. return all currencies");
		return currencies;
	}

	@Override
	public CurrencyPair getRateByCurrency(String fromCur, String toCur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur,
			String date) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @return the currencies
	 */
	public List<CurrencyPair> getCurrencies() {
		return currencies;
	}


	/**
	 * @param currencies the currencies to set
	 */
	public void setCurrencies(List<CurrencyPair> currencies) {
		this.currencies = currencies;
	}
	
	
}
