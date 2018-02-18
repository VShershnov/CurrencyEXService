package com.vshershnov.CurrencyEXService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.service.CurrencyPairReaderService;
import com.vshershnov.CurrencyEXService.service.CurrencyRateSpiderService;
import com.vshershnov.CurrencyEXService.utils.TimestampUtils;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CurrencyPairController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairController.class);
	
	@Autowired
	private CurrencyPairReaderService currencyPairReaderService;
	
	@Autowired
	private CurrencyRateSpiderService currencyRateSpiderService;
	
	@Autowired
	private TimestampUtils timestampUtils;
			
	
	public void setTimestampUtils(TimestampUtils timestampUtils) {
		this.timestampUtils = timestampUtils;
	}

	@RequestMapping(value = "/")
	public String welcome() throws IOException, ParseException, InterruptedException {
		
		//logger.info("Start Currency Spider");
		//currencyRateSpiderService.startAllSpider();
		
		logger.info("Welcome page message");
		return "Welcome to RestTemplate";
	}
	
	@RequestMapping(value = "/spiders/stop")
	public String stopSpiders() throws IOException, ParseException, InterruptedException {
		
		logger.info("Stopping Currency Spider");
		currencyRateSpiderService.stopAllSpider();
		
		logger.info("Spiders stopped");
		return "Spiders stopped";
	}
	
	@RequestMapping(value = "/spiders/start")
	public String startSpiders() throws IOException, ParseException, InterruptedException {
		
		logger.info("Start Currency Spider");
		currencyRateSpiderService.startAllSpider();
		
		logger.info("Spiders started");
		return "Spiders started";
	}

	@RequestMapping(value = "/all")
	public List<CurrencyPair> getAllCurrencyRate() {

		logger.info("Return all currency rates:");

		return currencyPairReaderService.getAll();
	}
	
	//http://localhost:8080/rate/usd/uah/
	@RequestMapping(value = "/rate/{fromCurr}/{toCurr}")
	public CurrencyPair currencyRateFromCurrToCurr(
			@PathVariable String fromCurr,
			@PathVariable String toCurr) {

		logger.info("Start currencyRateFromCurrToCurr fromCurr=" + fromCurr
				+ " toCurr=" + toCurr);

		String rateTime = timestampUtils.getISO8601StringForCurrentDate();
		return new CurrencyPair(fromCurr, toCurr, 26.85, rateTime, "nbu api");
	}
	
	//http://localhost:8080/rate/usd/uah/
		@RequestMapping(value = "/rate/{fromCurr}/{toCurr}/{rateTime}")
		public CurrencyPair currencyRateFromCurrToCurrToDate(
				@PathVariable String fromCurr,
				@PathVariable String toCurr,
				@PathVariable String rateTime) {
			
			logger.info("Start currencyRateFromCurrToCurrToDate fromCurr=" + fromCurr
					+ " toCurr=" + toCurr + " toDate=" + rateTime);

			return new CurrencyPair(fromCurr, toCurr, 26.85, rateTime, "nbu api");
		}
}
