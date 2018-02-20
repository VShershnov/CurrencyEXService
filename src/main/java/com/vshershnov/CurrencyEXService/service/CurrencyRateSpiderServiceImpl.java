package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyEurUahDao;
import com.vshershnov.CurrencyEXService.dao.exception.DaoException;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.spider.BankUaSpider;
import com.vshershnov.CurrencyEXService.spider.NBUSpider;

@Service
public class CurrencyRateSpiderServiceImpl implements CurrencyRateSpiderService{
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyRateSpiderServiceImpl.class);
	
	private boolean isStopped;
	
	@Autowired
	private CurrencyEurUahDao currencyEurUahDao;

	@Autowired
	private BankUaSpider bankUaSpider;
	
	@Autowired
	private NBUSpider nbuSpider;		
	

	@Override
	public void startAllSpider() throws IOException, ParseException,
			InterruptedException, DaoException {
		logger.info("Start Currency Spider");
		setStopped(false);
		//while (!isStopped) {
			CurrencyPair currencyPair = nbuSpider.getDataFromWebSource();
			saveToStorage(currencyPair);
			//Thread.sleep(1000 * 60);
		//}
	}	

	@Override
	public void stopAllSpider() {
		setStopped(true);
	}
	
	public void saveToStorage(CurrencyPair currencyPair) throws DaoException, IOException {
		if (currencyPair != null) {
			CurrencyPair c = getCurrencyPair(currencyPair);
			if (c == null) {
				logger.info("To storege added NEW " + currencyPair);
				currencyEurUahDao.add(currencyPair);
			}
		}
	}

	/**
	 * @return the isStopped
	 */
	public boolean isStopped() {
		return isStopped;
	}

	/**
	 * @param isStopped the isStopped to set
	 */
	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}	
	
	public CurrencyPair getCurrencyPair(CurrencyPair currency) throws DaoException {
		if (currency != null) {
			if (currency.getId() != null) {
				return currencyEurUahDao.getByPK(currency.getId());
			}
			return getCurrencyPairByPairRateTimeSourse(currency);
		}
		return null;
	}
	
	private CurrencyPair getCurrencyPairByPairRateTimeSourse(CurrencyPair currency) throws DaoException {
		List<CurrencyPair> currencies = currencyEurUahDao.getAll();
		for (CurrencyPair c : currencies) {
			if (c.equals(currency)){
				return c;
			}
		}
		return null;
	}
}
