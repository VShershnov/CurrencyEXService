package com.vshershnov.CurrencyEXService.spider;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

import java.io.IOException;
import java.text.ParseException;


public interface NBUSpider {
	public CurrencyPair getDataFromWebSource() throws ParseException, IOException;
}
