package com.vshershnov.CurrencyEXService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.service.CurrencyPairReaderService;
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
	private TimestampUtils timestampUtils;
			
	
	public void setTimestampUtils(TimestampUtils timestampUtils) {
		this.timestampUtils = timestampUtils;
	}

	@RequestMapping(value = "/")
	public String welcome() {
		logger.info("Welcome page message");
		return "Welcome to RestTemplate";
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
