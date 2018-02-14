package com.vshershnov.CurrencyEXService.dao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Repository
public class CurrencyPairDaoImpl implements CurrencyPairDao{

	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairDaoImpl.class);
	
	//private SessionFactory sessionFactory;

	private List<CurrencyPair> currencies = Arrays.asList(new CurrencyPair("usd", "uah", 2685, "17", LocalDate.now(), "nbu api"),
														new CurrencyPair("usd", "uah", 2655, "09", LocalDate.now(), "nbu api"),
														new CurrencyPair("usd", "uah", 2655, "09", LocalDate.of(2018, 2, 13), "nbu api"));
	
	@Override
	public void create(CurrencyPair currency) {
		logger.info("DAO. create: ", currency);
		currencies.add(currency);
	}

	@Override
	public CurrencyPair getByPK(int key) {
		return null;
	}

	@Override
	public List<CurrencyPair> getAll() {
		logger.info("DAO. return all currencies");
		return currencies;
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
