package com.vshershnov.CurrencyEXService;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;
import com.vshershnov.CurrencyEXService.service.CurrencyPairReaderService;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CurrencyPairController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairController.class);
	
	@Autowired
	private CurrencyPairReaderService currencyPairReaderService;	
			
	//Map to store employees, ideally we should use database
	Map<Integer, CurrencyPair> curData = new HashMap<Integer, CurrencyPair>();
	
	
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}	

	*/	
	
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
	public CurrencyPair currencyRatePathVar(
			@PathVariable String fromCurr,
			@PathVariable String toCurr) {

		logger.info("Start currencyRatePathVar.fromCurr=" + fromCurr
				+ " toCurr=" + toCurr);

		return new CurrencyPair(fromCurr, toCurr, 2685, "17", LocalDate.now(),
				"nbu api");
	}
}
