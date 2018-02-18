package com.vshershnov.CurrencyEXService.spider;

import java.io.IOException;
import java.text.ParseException;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;


public interface NBUSpider {
	public CurrencyPair getDataFromWebSource() throws ParseException, IOException;
}
