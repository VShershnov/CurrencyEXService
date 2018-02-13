package com.vshershnov.CurrencyEXService;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vshershnov.CurrencyEXService.model.CurrencyPair;

/**
 * Handles requests for the application home page.
 */
@Controller
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
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/currencyPair/dummy", method = RequestMethod.GET)
	public @ResponseBody CurrencyPair getDummyCurrencyPair() {
		logger.info("Start getDummyCurrencyPair");
		CurrencyPair curPair = new CurrencyPair();
		curPair.setId(9999);
		curPair.setName("Dummy");
		curPair.setCreatedDate(new Date());
		curPair.setRate(2875);
		curPair.setSource("nbu.gov.ua");
		curData.put(9999, curPair);
		return curPair;
	}
	
	@RequestMapping(value = "/currencyPair/{id}", method = RequestMethod.GET)
	public @ResponseBody CurrencyPair getCurrencyPair(@PathVariable("id") int curId) {
		logger.info("Start getCurrencyPair.id="+ curId);
		
		return curData.get(curId);
	}
}
