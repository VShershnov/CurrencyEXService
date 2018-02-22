package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.text.ParseException;

import com.vshershnov.CurrencyEXService.dao.exception.DaoException;

public interface CurrencyRateSpiderService {	
	public void startAllSpider() throws IOException, ParseException, InterruptedException, DaoException;	
	public void stopAllSpider();
}
