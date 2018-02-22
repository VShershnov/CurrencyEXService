package com.vshershnov.CurrencyEXService;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vshershnov.CurrencyEXService.dao.exception.DaoException;
import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.service.CurrencyPairReaderService;
import com.vshershnov.CurrencyEXService.service.CurrencyRateSpiderService;

/**
 * Handles REST GET requests for the application.
 */
@RestController
public class CurrencyPairController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairController.class);
	
	@Autowired
	private CurrencyPairReaderService currencyPairReaderService;
	
	@Autowired
	private CurrencyRateSpiderService currencyRateSpiderService;

	
	/**
	 * Handles on start request and start currency rate spider
	 * 
	 * @return welcome message
	 * @throws IOException, ParseException, InterruptedException, DaoException
	 */
	@RequestMapping(value = "/", produces = {"text/html"})	
	public ResponseEntity < String > welcome() throws IOException, ParseException, InterruptedException, DaoException {
		
		logger.info("Start Currency Spider");
		currencyRateSpiderService.startAllSpider();
		
		logger.info("Welcome page message");
		return new ResponseEntity < String > ("Welcome page message", HttpStatus.OK);
	}
	
	/**
	 * http://localhost:8080/rate/eur/uah/
	 * 
	 * @param fromCurr
	 * @param toCurr
	 * @return JSON with today currencyRate
	 */
	
	@RequestMapping(value = "/rate/{fromCurr}/{toCurr}/")
	public CurrencyPair currencyRateFromCurrToCurr(
			@PathVariable String fromCurr,
			@PathVariable String toCurr) {

		logger.info("Start user currency rate GET request fromCurr=" + fromCurr
				+ " toCurr=" + toCurr);
		
		return currencyPairReaderService.getRateByCurrency(fromCurr, toCurr);
	}
	
	/**
	 * //http://localhost:8080/rate/usd/uah/2018-02-20
	 * 
	 * @param PathVariable fromCurr
	 * @param PathVariable toCurr
	 * @param PathVariable rateTime
	 * @return JSON with currencyRate for rateTime date or less
	 */
	
	@RequestMapping(value = "/rate/{fromCurr}/{toCurr}/{rateTime}/")
	public CurrencyPair currencyRateFromCurrToCurrToDate(
			@PathVariable String fromCurr, @PathVariable String toCurr,
			@PathVariable String rateTime) {

		logger.info("Start currencyRateFromCurrToCurrToDate fromCurr="
				+ fromCurr + " toCurr=" + toCurr + " toDate=" + rateTime);

		return currencyPairReaderService.getRateByCurrencyToDate(fromCurr,
				toCurr, rateTime);
	}

	/**
	 * manually start Currency Spider
	 * 
	 * @return
	 * @throws IOException, ParseException, InterruptedException, DaoException
	 */
	@RequestMapping(value = "/spiders/start")
	public String startSpiders() throws IOException, ParseException, InterruptedException, DaoException {
		
		logger.info("Start Currency Spider");
		currencyRateSpiderService.startAllSpider();
		
		logger.info("Spiders started");
		return "Spiders started";
	}

	/**
	 * manually stop Currency Spider
	 * 
	 * @return
	 * @throws IOException, ParseException, InterruptedException
	 */
	@RequestMapping(value = "/spiders/stop")
	public String stopSpiders() throws IOException, ParseException, InterruptedException {
		
		logger.info("Stopping Currency Spider");
		currencyRateSpiderService.stopAllSpider();
		
		logger.info("Spiders stopped");		
		return "Spiders stopped";
	}
}