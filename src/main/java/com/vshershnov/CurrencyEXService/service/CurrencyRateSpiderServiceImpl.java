package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyPairDao;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.spider.BankUaSpider;
import com.vshershnov.CurrencyEXService.spider.NBUSpider;

@Service
public class CurrencyRateSpiderServiceImpl implements CurrencyRateSpiderService{
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyRateSpiderServiceImpl.class);
	
	private boolean isStopped;
	
	@Autowired
	private CurrencyPairDao currencyPairDao;

	@Autowired
	private BankUaSpider bankUaSpider;
	
	@Autowired
	private NBUSpider nbuSpider;		
	

	@Override
	public void startAllSpider() throws IOException, ParseException,
			InterruptedException {
		logger.info("Start Currency Spider");
		setStopped(false);
		while (!isStopped) {
			CurrencyPair currencyPair = nbuSpider.getDataFromWebSource();
			saveToStorage(currencyPair);
			Thread.sleep(1000 * 60);
		}
	}	

	@Override
	public void stopAllSpider() {
		setStopped(true);
	}
	
	public void saveToStorage(CurrencyPair currencyPair) {
		currencyPairDao.add(currencyPair);		
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
}
