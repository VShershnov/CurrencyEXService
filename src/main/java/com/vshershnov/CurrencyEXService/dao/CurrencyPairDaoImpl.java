package com.vshershnov.CurrencyEXService.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Repository
public class CurrencyPairDaoImpl implements CurrencyPairDao{

	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairDaoImpl.class);
	
	//private SessionFactory sessionFactory;

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
