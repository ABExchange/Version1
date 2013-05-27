package org.exchangesystem.web;

import java.util.List;

import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeStatus;
import org.exchangesystem.service.AccountService;
import org.exchangesystem.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/****
 * @author japheth The main controller that is the starting point in the
 *         exchange system
 * 
 **/
@Controller
public class ExchangeMainController {
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	@Autowired
	SymbolService symbolService;
	
	@Autowired
	AccountService accountService;
	/***
	 *The default page is here 
	 **/
	@RequestMapping(value={"/main", "/exchangesystem", "/exchangesystem/", ""}, method=RequestMethod.GET)
	public String main(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser, ModelMap model) {
		
		List<Symbol> listSymbol = symbolService.findAll();
		
		
		

		TradeStatus tradeStatus = exchangeSystemSession.getTradeStatus();
		Symbol defaultSymbol = tradeStatus.getSymbol();//symbolService.findSymbol("USD");
		//tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);
		
		model.addAttribute("tradeStatus", tradeStatus);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("user", exchangeSystemSession.getUser());
		
		model.addAttribute("accountNo", ((exchangeSystemSession.getUser() != null) && (exchangeSystemSession.getUser().getAccountNumber() != null)) ? exchangeSystemSession.getUser().getAccountNumber() : "");

		Symbol currencySymbol = symbolService.findSymbol("HKD");
		Double hkdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("RMB");
		Double rmbBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("USD");
		Double usdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("BTC");
		Double btcBalance = accountService.getBalance(currencySymbol);
		
		model.addAttribute("hkdBalance", hkdBalance);
		model.addAttribute("rmbBalance", rmbBalance);
		model.addAttribute("usdBalance", usdBalance);
		model.addAttribute("btcBalance", btcBalance);
		return "main";
	}
	
	
	@RequestMapping(value={"/main/{symbolId}"}, method=RequestMethod.POST)
	public String mainWithSymbol(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser, @ModelAttribute("tradeStatus") TradeStatus tradeStatus, @PathVariable("symbolId") Long symbolId,  ModelMap model) {
		
		List<Symbol> listSymbol = symbolService.findAll();
		//TradeStatus tradeStatus = new TradeStatus();
		
		Symbol defaultSymbol = tradeStatus.getSymbol();
		//symbolService.findById(tradeStatus.getSymbol().getId());
		tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("tradeStatus", tradeStatus);
		
		model.addAttribute("listSymbol", listSymbol);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("user", exchangeSystemSession.getUser());
		exchangeSystemSession.setTradeStatus(tradeStatus);
		
		model.addAttribute("accountNo", (exchangeSystemSession.getUser().getAccountNumber() != null) ? exchangeSystemSession.getUser().getAccountNumber() : "");

		Symbol currencySymbol = symbolService.findSymbol("HKD");
		Double hkdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("RMB");
		Double rmbBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("USD");
		Double usdBalance = accountService.getBalance(currencySymbol);
		currencySymbol = symbolService.findSymbol("BTC");
		Double btcBalance = accountService.getBalance(currencySymbol);
		
		model.addAttribute("hkdBalance", hkdBalance);
		model.addAttribute("rmbBalance", rmbBalance);
		model.addAttribute("usdBalance", usdBalance);
		model.addAttribute("btcBalance", btcBalance);
		return "main";
	}
	
	
	
	@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
	public String home(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser, ModelMap model) {
		
		List<Symbol> listSymbol = symbolService.findAll();
		Symbol defaultSymbol = symbolService.findSymbol("USD");

		TradeStatus tradeStatus = new TradeStatus();
		tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);
		
		model.addAttribute("tradeStatus", tradeStatus);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("user", exchangeSystemSession.getUser());
		exchangeSystemSession.setTradeStatus(tradeStatus);
		
		model.addAttribute("accountNo", ((exchangeSystemSession.getUser() != null) && (exchangeSystemSession.getUser().getAccountNumber() != null)) ? exchangeSystemSession.getUser().getAccountNumber() : "");

		return "home";
	}
	
	
	@RequestMapping(value={"/home/{symbolId}"}, method=RequestMethod.POST)
	public String homeWithSymbol(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser, @ModelAttribute("tradeStatus") TradeStatus tradeStatus, @PathVariable("symbolId") Long symbolId, ModelMap model) {
		
		List<Symbol> listSymbol = symbolService.findAll();
		//TradeStatus tradeStatus = new TradeStatus();
		
		Symbol defaultSymbol = tradeStatus.getSymbol();
		//symbolService.findById(tradeStatus.getSymbol().getId());
		tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("tradeStatus", tradeStatus);
		
		model.addAttribute("listSymbol", listSymbol);
		//Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		//Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		//High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		//Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		//Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		//AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		
		model.addAttribute("user", exchangeSystemSession.getUser());
		exchangeSystemSession.setTradeStatus(tradeStatus);
		
		model.addAttribute("accountNo", ((exchangeSystemSession.getUser() != null) && (exchangeSystemSession.getUser().getAccountNumber() != null)) ? exchangeSystemSession.getUser().getAccountNumber() : "");

		return "home";
	}
}
