package com.vshershnov.CurrencyEXService.service;

import java.io.IOException;
import java.net.URL;

public interface CurrencyRateSpiderService {	
	public void startAllSpider() throws IOException;
	public void startSpider(URL url);
	public void stopSpider(URL url);
	public void stopAllSpider();
}
