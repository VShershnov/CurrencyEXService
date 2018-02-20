package com.vshershnov.CurrencyEXService.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.model.CurrencyPairMapper;
import com.vshershnov.CurrencyEXService.utils.TimestampUtils;

@Repository
public class CurrencyEurUahDaoImpl implements CurrencyEurUahDao{

	private static final Logger logger = LoggerFactory.getLogger(CurrencyEurUahDaoImpl.class);		
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;
	
	private final String SQL_FIND_RATE_BY_ID = "select * from \"EURUAH\" where id = ?";
	private final String SQL_FIND_RATE_BY_CURRENCY_PAIR = "select * from \"EURUAH\" where fromCurr = ? and toCurr = ?";
	private final String SQL_GET_ALL = "select id, fromCurr, toCurr, rate, rateTime, sourceID from \"EURUAH\"";
	private final String SQL_INSERT_RATE = "insert into \"EURUAH\"(fromCurr, toCurr, rate, rateTime, sourceID) values(?,?,?,?,?)";
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public CurrencyEurUahDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}	
	
	@Override
	public boolean add(CurrencyPair currency) {
		TimestampUtils timestampUtils = new TimestampUtils();
		Date date = timestampUtils.getDateForISO8601String(currency.getRateTime());
		logger.info("Save " + currency + " to CurrencyRate.db");
		int n = jdbcTemplate.update(SQL_INSERT_RATE, currency.getFromCurr(),
				currency.getToCurr(), currency.getRate(), date,
				currency.getSourceID());				
		return n  > 0;
	}

	@Override
	public List<CurrencyPair> getAll() {
				
		List<CurrencyPair> currencyRateList = new ArrayList<CurrencyPair>();	
		
		logger.info("DAO. return all currencies");
		currencyRateList = jdbcTemplate.query(SQL_GET_ALL, new CurrencyPairMapper());		
		return currencyRateList;
	}

	@Override
	public CurrencyPair getRateByCurrency(String fromCur, String toCur) {
		
		logger.info("Take Rate " + fromCur.toUpperCase() + " //" + toCur.toUpperCase() +" from CurrencyRate.db");
		CurrencyPair currency = jdbcTemplate.queryForObject(SQL_FIND_RATE_BY_CURRENCY_PAIR, 
				new Object[] {fromCur, toCur}, new CurrencyPairMapper());	
		return currency;
	}

	@Override
	public CurrencyPair getRateByCurrencyToDate(String fromCur, String toCur,
			String date) {
		String queryByDate = SQL_FIND_RATE_BY_CURRENCY_PAIR + " && rateTime = ?";
		
		logger.info("Take Rate " + fromCur.toUpperCase() + " //" + toCur.toUpperCase() +" from CurrencyRate.db");
		CurrencyPair currency = jdbcTemplate.queryForObject(queryByDate, 
				new Object[] { fromCur, toCur }, new CurrencyPairMapper());		
		return currency;
	}

	@Override
	public CurrencyPair getByPK(int id) {
		CurrencyPair currency = jdbcTemplate.queryForObject(SQL_FIND_RATE_BY_ID, 
				new Object[] { id }, new CurrencyPairMapper());
		return currency;
	}	
}
