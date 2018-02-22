package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshershnov.CurrencyEXService.dao.CurrencyEurUahDao;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.spider.NBUSpider;

@Service
public class CurrencyRateSpiderServiceImpl implements CurrencyRateSpiderService{
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyRateSpiderServiceImpl.class);
	
	private Timer timer;
	
	@Autowired
	private CurrencyEurUahDao currencyEurUahDao;
	
	@Autowired
	private NBUSpider nbuSpider;		
	

	@Override
	public void startAllSpider() throws IOException, ParseException,
			InterruptedException {
		logger.info("Start Currency Spider");
		timer = new Timer(true);
		TimerTask task = new TimerTask() {
			public void run() {
				doSpiders();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 15 * 1000);
	}

	@Override
	public void stopAllSpider() {
		timer.cancel();
        timer.purge();
        logger.info("Currency Spider stopped");
	}
	
	public void saveToStorage(CurrencyPair currencyPair) throws IOException {
		if (currencyPair != null) {
			CurrencyPair c = getCurrencyPair(currencyPair);
			if (c == null) {
				logger.info("To storege added NEW " + currencyPair);
				currencyEurUahDao.add(currencyPair);
			} else {
				logger.info("CurrencyRate " + currencyPair + " already exist in DB");
			}			
		}
	}	
	
	public CurrencyPair getCurrencyPair(CurrencyPair currency) {
		if (currency != null) {
			if (currency.getId() != null) {
				return currencyEurUahDao.getByPK(currency.getId());
			}
			return getCurrencyPairByPairRateTimeSourse(currency);
		}
		return null;
	}
	
	private void doSpiders() {
		CurrencyPair currencyPair;
		try {
			currencyPair = nbuSpider.getDataFromWebSource();
			saveToStorage(currencyPair);
		} catch (ParseException e) {
			logger.error("Wrong parsing mechanism. Check currency rate sourse format");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Cannot read currency rate sourse. Check currency rate sourse url");
			e.printStackTrace();
		}
	}

	private CurrencyPair getCurrencyPairByPairRateTimeSourse(CurrencyPair currency) {
		List<CurrencyPair> currencies = currencyEurUahDao.getAll();
		for (CurrencyPair c : currencies) {
			if (c.equals(currency)){
				return c;
			}
		}
		return null;
	}
}