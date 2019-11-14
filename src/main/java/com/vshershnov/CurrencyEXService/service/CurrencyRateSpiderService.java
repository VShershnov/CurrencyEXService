package com.vshershnov.CurrencyEXService.service;

import com.vshershnov.CurrencyEXService.dao.exception.DaoException;

import java.io.IOException;
import java.text.ParseException;

public interface CurrencyRateSpiderService {	
	public void startAllSpider() throws IOException, ParseException, InterruptedException, DaoException;	
	public void stopAllSpider();
}
