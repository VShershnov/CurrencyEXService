package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

public interface CurrencyRateSpiderService {	
	public void startAllSpider() throws IOException, ParseException;
	public void startSpider(URL url);
	public void stopSpider(URL url);
	public void stopAllSpider();
}
