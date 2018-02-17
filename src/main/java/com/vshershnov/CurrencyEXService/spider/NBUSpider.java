package com.vshershnov.CurrencyEXService.spider;

import java.io.IOException;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;


public interface NBUSpider {
	public CurrencyPair getDataFromWebSource() throws IOException;
}
