package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.text.ParseException;

public interface CurrencyRateSpiderService {	
	public void startAllSpider() throws IOException, ParseException, InterruptedException;	
	public void stopAllSpider();
}
