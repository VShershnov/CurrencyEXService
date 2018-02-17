package com.vshershnov.CurrencyEXService.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;

@Service
public class NBUSpiderImpl implements NBUSpider {	
	
	private static final Logger logger = LoggerFactory
			.getLogger(NBUSpiderImpl.class);

	private static int CONNECT_TIMEOUT = 10 * 1000;
	private static int READ_TIMEOUT = 1 * 60 * 1000;
	
	private static String GATE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
	
	@Override
	public CurrencyPair getDataFromWebSource() throws IOException {
		logger.info("Send GET to " + GATE_URL);		
		return sendGET(GATE_URL);
	}	
	
	private CurrencyPair JSONToObject(JSONPObject response) {		
		// TODO Auto-generated method stub
		return null;
	}

	private CurrencyPair sendGET(String url) throws IOException {
			
		HttpURLConnection conn = null;
		InputStream stream = null;
		URL urlLink = new URL(url);
		OutputStreamWriter writer = null;
		
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
			
		logger.info("stream " + stream2String(stream));
		return stream2Object(stream);		
	}
	

	private CurrencyPair stream2Object(InputStream stream) throws IOException {
		
		CurrencyPair currency = new CurrencyPair();
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//read JSON like DOM Parser
		logger.info("Create JsonNode");
		JsonNode rootNode = objectMapper.readTree(stream);
		
		
		JsonNode idNode = rootNode.path("id");
		currency.setId(idNode.asInt());
		logger.info("id = "+idNode.asInt());
		
		currency.setRateTime("2018-02-17");
				
		return currency;
	}

	private String stream2String(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder(8192);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}