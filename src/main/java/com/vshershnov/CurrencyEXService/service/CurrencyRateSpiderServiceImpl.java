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
	
	@Autowired
	private CurrencyPairDao currencyPairDao;

	@Autowired
	private BankUaSpider bankUaSpider;
	
	@Autowired
	private NBUSpider nbuSpider;		
	

	@Override
	public void startAllSpider() throws IOException, ParseException {
		// TODO Auto-generated method stub
		logger.info("Start Currency Spider");
		CurrencyPair currencyPair = nbuSpider.getDataFromWebSource();
		saveToStorage(currencyPair);
	}

	@Override
	public void startSpider(URL url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopSpider(URL url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopAllSpider() {
		// TODO Auto-generated method stub
	}
	
	public void saveToStorage(CurrencyPair currencyPair) {
		currencyPairDao.add(currencyPair);		
	}
}
