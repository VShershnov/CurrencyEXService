package com.vshershnov.CurrencyEXService.service;

import java.net.URL;

public interface CurrencyRateSpiderService {	
	public void startAllSpider();
	public void startSpider(URL url);
	public void stopSpider(URL url);
	public void stopAllSpider();
}
