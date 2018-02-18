package com.vshershnov.CurrencyEXService.spider;

import com.fasterxml.jackson.databind.util.JSONPObject;

public interface BankUaSpider {
	public JSONPObject getDataFromWebSource();
}
