package org.exchangesystem.web;

import java.util.List;

import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.model.BTCRate;
import org.exchangesystem.model.Country;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeStatus;
import org.exchangesystem.model.TransferMethod;
import org.exchangesystem.service.BTCRateService;
import org.exchangesystem.service.CountryService;
import org.exchangesystem.service.ExchangeUserService;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.TransferMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@Autowired
	SymbolService symbolService;
	
	@Autowired
	CountryService countryService;
	@Autowired
	ExchangeUserService exchangeUserService;
	
	@Autowired
	TransferMethodService transferMethodService;
	@Autowired
	BTCRateService btcRateService;
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String getAdmin(@ModelAttribute("symbol") Symbol symbol, @ModelAttribute("country") Country country, @ModelAttribute("transferMethod") TransferMethod transferMethod, @ModelAttribute("btcRate") BTCRate btcRate,  Model model){
		
		List<Symbol> symbolList = symbolService.findAll();
		List<Country> countryList = countryService.findAll();
		List<BTCRate> btcRateList = btcRateService.findAll();
		
		symbol = new Symbol();
		country = new Country();
		

		model.addAttribute("symbolList", symbolList);
		model.addAttribute("symbol", symbol);
		model.addAttribute("country", country);
		
		List<TransferMethod> transferMethodList = transferMethodService.findAll();
		model.addAttribute("transferMethodList", transferMethodList);

		
		model.addAttribute("user", exchangeSystemSession.getUser());
		model.addAttribute("countryList", countryList);
		model.addAttribute("btcRateList", btcRateList);
		//model.addAttribute("listUser", exchangeUserService.findAll());
		
		
		//Market Values Listing
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

		
		return "admin";
	}
	
	
	@RequestMapping(value="/symbol", method=RequestMethod.POST)
	public String symbols(@ModelAttribute("symbol") Symbol symbol, Model model){
		
		symbolService.add(symbol);
		
		List<Symbol> symbolList = symbolService.findAll();
		symbol = new Symbol();
		
		model.addAttribute("symbolList", symbolList);
		model.addAttribute("symbol", symbol);
		
	//	model.addAttribute("user", exchangeSystemSession.getUser());

		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/country", method=RequestMethod.POST)
	public String addCountry(@ModelAttribute("country") Country country, Model model){
		
		countryService.add(country);
		
		List<Country> countryList = countryService.findAll();
		country = new Country();
		
		model.addAttribute("countryList", countryList);
		model.addAttribute("country", country);

		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/transfermethod", method=RequestMethod.POST)
	public String addTransferMethod(@ModelAttribute("transferMethod") TransferMethod transferMethod, Model model ){
		
		transferMethodService.add(transferMethod);
		transferMethod = new TransferMethod();
		
		List<TransferMethod> transferMethodList = transferMethodService.findAll();
		model.addAttribute("transferMethodList", transferMethodList);
		model.addAttribute("transferMethod", transferMethod);
		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/btcrate", method=RequestMethod.POST)
	public String addbtcrate(@ModelAttribute("btcRate") BTCRate btcRate, Model model ){
		
		//Get the last BTC Rate and set it's status to old
		List<BTCRate> oldbtcRateList = btcRateService.findAll(btcRate.getSymbol());
		if (!oldbtcRateList.equals(null) && (oldbtcRateList.size() > 0)){
			BTCRate oldbtcRate = oldbtcRateList.get(0);
			oldbtcRate.setCurrent("ODL");
			btcRateService.update(oldbtcRate);
		}
		//.get(index)
		
		
		
		btcRateService.add(btcRate);
		
		btcRate = new BTCRate();
		model.addAttribute("btcRate", btcRate);
		
		return "redirect:/admin";
	}

}
