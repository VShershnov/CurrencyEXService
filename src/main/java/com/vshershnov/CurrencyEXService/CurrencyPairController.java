package com.vshershnov.CurrencyEXService;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

/**
 * Handles requests for the application home page.
 */
@RestController
public class CurrencyPairController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyPairController.class);
	
	/*
	private CurrencyPairService currencyPairService;
	
	@Autowired(required=true)
	@Qualifier(value="currencyPairService")
	public void setCurrencyPairService(CurrencyPairService currencyPairService) {
		this.currencyPairService = currencyPairService;
	}
	*/
	//Map to store employees, ideally we should use database
	Map<Integer, CurrencyPair> curData = new HashMap<Integer, CurrencyPair>();

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
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
	
	@RequestMapping(value = "/rate/usd/uah/dummy", method = RequestMethod.GET)
	public @ResponseBody CurrencyPair getDummyCurrencyPair() {
		logger.info("Start getDummyCurrencyPair");
		CurrencyPair curPair = new CurrencyPair();
		curPair.setId(9999);
		curPair.setFromCurr("Dummy");
		curPair.setCreatedDate(LocalDate.now());
		curPair.setRate(2875);
		curPair.setSourceID("nbu.gov.ua");
		curData.put(9999, curPair);
		return curPair;
	}
	
	@RequestMapping(value = "/rate/usd/uah/{id}", method = RequestMethod.GET)
	public @ResponseBody CurrencyPair getCurrencyPair(@PathVariable("id") int curId) {
		logger.info("Start getCurrencyPair.id="+ curId);
		
		return curData.get(curId);
	}
	
	//http://localhost:8080/rate/usd/uah?id=9999
	@RequestMapping(value = "/usd/uah")
	public CurrencyPair currencyRateReqParam(@RequestParam(value="id", defaultValue="") int curId) {
		logger.info("Start currencyRateReqParam.id="+ curId);
		
		return curData.get(curId);
	}
	
	*/
	
	//http://localhost:8080/rate/usd/uah/9999
		@RequestMapping(value = "/{usd}/{uah}")
		public CurrencyPair currencyRatePathVar(
				@PathVariable ("fromCurr") String fromCurr,
				@PathVariable ("toCurr") String toCurr) {			
			
			logger.info("Start currencyRatePathVar.fromCurr="+ fromCurr + " toCurr=" + toCurr);
			
			return new CurrencyPair(fromCurr, toCurr, 2685, "17", LocalDate.now(), "nbu api");
		}	
}
