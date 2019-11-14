package com.vshershnov.CurrencyEXService.spider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.utils.TimestampUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Iterator;

/**
 * 
 * @author vshershnov
 *
 * Spider Class.
 * Get info from NBU public API.
 * Parse multi currency JSON Array response
 * 
 */

@Service
public class NBUSpiderImpl implements NBUSpider {	
	
	private static final Logger logger = LoggerFactory
			.getLogger(NBUSpiderImpl.class);

	private static int CONNECT_TIMEOUT = 10 * 1000;
	private static int READ_TIMEOUT = 1 * 60 * 1000;
	
	private static String GATE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
	
		
	@Override
	public CurrencyPair getDataFromWebSource() throws IOException, ParseException {
		logger.info("Send GET to " + GATE_URL);		
		return sendGET(GATE_URL);
	}	
	
	private CurrencyPair sendGET(String url) throws IOException, ParseException {
			
		HttpURLConnection conn = null;
		InputStream stream = null;
		URL urlLink = new URL(url);
		
		logger.info("Open connectio to " + url);
		conn = (HttpURLConnection) urlLink.openConnection();
		conn.setDoInput(true);		
		conn.setConnectTimeout(CONNECT_TIMEOUT);
		conn.setReadTimeout(READ_TIMEOUT);
		conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
					
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			logger.info("Open Input Sream from " + url);
			stream = conn.getInputStream();
		} else {
			stream = conn.getErrorStream();
		}
		if (stream == null) {
			logger.info("Response code is " + conn.getResponseCode());
			return null;
		}		
		return stream2Object(stream);		
	}

	private CurrencyPair stream2Object(InputStream stream) throws IOException, ParseException {
				
		ObjectMapper objectMapper = new ObjectMapper();
						
		//read JSON like DOM Parser
		logger.info("Create JsonNode");
		JsonNode jsonTree = objectMapper.readTree(stream);
		
		return JSON2Object(jsonTree);		
	}

	private CurrencyPair JSON2Object(JsonNode jsonTree) throws ParseException {		

		CurrencyPair currency = new CurrencyPair();	
		
		Iterator<JsonNode> elements = jsonTree.elements();
		while(elements.hasNext()){
			JsonNode currencyNode = elements.next();
			String fromCurr = currencyNode.path("cc").asText().toLowerCase();
			
			if(fromCurr.equals("eur")){
				currency.setFromCurr(fromCurr);
			
				JsonNode rateNode = currencyNode.path("rate");
				currency.setRate(BigDecimal.valueOf(rateNode.asDouble()));
				
				JsonNode rateTimeNode = currencyNode.path("exchangedate");
				TimestampUtils timestampUtils = new TimestampUtils();
				String rateTime = timestampUtils.getISOStringForNBUDate(rateTimeNode.asText());
				currency.setRateTime(rateTime);				
			}		
		}
		
		currency.setToCurr("uah");
		currency.setSourceID("NBU API");
		
		logger.info("Parse nbuJSON to "+ currency);
		return currency;
	}	
}