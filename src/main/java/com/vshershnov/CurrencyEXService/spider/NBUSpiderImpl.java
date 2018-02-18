package com.vshershnov.CurrencyEXService.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.utils.TimestampUtils;

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
		
		//String steamString = stream2String(stream);
		//logger.info("stream " + steamString);
		return stream2Object(stream);		
	}
	

	private CurrencyPair stream2Object(InputStream stream) throws IOException {
		
		CurrencyPair currency = new CurrencyPair();
		
		ObjectMapper objectMapper = new ObjectMapper();
		//ObjectNode jsonTree = (ObjectNode) objectMapper.readTree(stream);
		//List<JsonNode> jsonNodes = objectMapper.readValue(stream, new TypeReference<List<JsonNode>>(){});
		
		/*
		JSONArray jsonArr = new JSONArray(stream);
		
		for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);

            System.out.println(jsonObj);
        }
        */
		
		//read JSON like DOM Parser
		logger.info("Create JsonNode");
		JsonNode jsonTree = objectMapper.readTree(stream);
		//JsonNode rootNode = objectMapper.readValue(stream, JsonNode.class);
		
		Iterator<JsonNode> elements = jsonTree.elements();
		while(elements.hasNext()){
			JsonNode currencyNode = elements.next();
			JsonNode rateNode = currencyNode.path("rate");
			currency.setRate(BigDecimal.valueOf(rateNode.asDouble()));
			logger.info("rate = "+ BigDecimal.valueOf(rateNode.asDouble()));
		}
		
		//JsonNode rateNode = jsonTree.get("rate");
		
		
		/*
		JsonNode ccNode = rootNode.path("cc");
		currency.setFromCurr(ccNode.asText().toLowerCase());
		logger.info("fromCurr = "+ ccNode.asText().toLowerCase());
		
		JsonNode rateTimeNode = rootNode.path("exchangedate");
		TimestampUtils timestampUtils = new TimestampUtils();
		String rateTime = timestampUtils.getISOStringForNBUDate(rateTimeNode.asText());
		logger.info("rateTime = "+ rateTime);
		currency.setRateTime(rateTime);
		*/
		currency.setToCurr("uah");
		currency.setSourceID("NBU API");
		
		
		/*
		 JsonFactory factory = new JsonFactory();
	     JsonParser parser = factory.createParser(stream);
	     parser.nextToken();
	     parser.nextToken();
	     while (parser.nextToken() != JsonToken.END_ARRAY) { //loop until "]"
	    	 System.out.println(parser.toString());
	    	 System.out.println(parser.getText());
	    	 parser.nextToken();
	    	 System.out.println(parser.getText());
	    	 while (parser.nextToken() != JsonToken.END_OBJECT) { //loop until "}
				String fieldName = parser.getCurrentName();
				if (fieldName.equals("rate")) {
					parser.nextToken();
					System.out.println("rate : " + BigDecimal.valueOf(parser.getDoubleValue()));
				} else if (fieldName.equals("cc")) {
					parser.nextToken();
					System.out.println("fromCurr = "+ parser.getText().toLowerCase());
				} else { // unexpected token, generate error
					throw new IOException("Unrecognized field '" + fieldName + "'");
				}
	    	 }
		}
	    parser.close(); 
		*/
		
		
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