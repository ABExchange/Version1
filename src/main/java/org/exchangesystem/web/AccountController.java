package org.exchangesystem.web;

import java.util.ArrayList;
import java.util.List;

import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.dto.SymbolBalance;
import org.exchangesystem.model.Bank;
import org.exchangesystem.model.Country;
import org.exchangesystem.model.Deposit;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeStatus;
import org.exchangesystem.model.TransferMethod;
import org.exchangesystem.model.Withdrawal;
import org.exchangesystem.service.AccountService;
import org.exchangesystem.service.BankService;
import org.exchangesystem.service.CountryService;
import org.exchangesystem.service.DepositService;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.TransferMethodService;
import org.exchangesystem.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

	@Autowired
	CountryService countryService;

	@Autowired
	BankService bankService;

	@Autowired
	SymbolService symbolService;

	@Autowired
	DepositService depositService;

	@Autowired
	TransferMethodService transferMethodService;
	@Autowired
	WithdrawalService withdrawalService;

	@Autowired
	ExchangeSystemSession exchangeSystemSession;

	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String account(@ModelAttribute("deposit") Deposit deposit,
			@ModelAttribute("bank") Bank bank,
			@ModelAttribute("withdrawal") Withdrawal withdrawal, Model model) {

		List<Bank> bankList = bankService.findAll();
		List<Country> countryList = countryService.findAll();
		List<Symbol> symbolList = symbolService.findAll();
		List<TransferMethod> transferMethodList = transferMethodService
				.findAll();
		List<Deposit> depositList = depositService.findAll();
		List<Withdrawal> withdrawalList = withdrawalService.findAll();

		model.addAttribute("countryList", countryList);
		deposit = new Deposit();
		model.addAttribute("deposit", deposit);
		model.addAttribute("withdrawal", withdrawal);
		model.addAttribute("bank", bank);
		model.addAttribute("bankList", bankList);
		model.addAttribute("symbolList", symbolList);
		model.addAttribute("transferMethodList", transferMethodList);
		model.addAttribute("depositList", depositList);
		model.addAttribute("withdrawalList", withdrawalList);
		model.addAttribute("user", exchangeSystemSession.getUser());

		// Market Values Listing
		List<Symbol> listSymbol = symbolService.findAll();
		TradeStatus tradeStatus = exchangeSystemSession.getTradeStatus();
		Symbol defaultSymbol = tradeStatus.getSymbol();// symbolService.findSymbol("USD");
		// tradeStatus.setSymbol(defaultSymbol);
		model.addAttribute("listSymbol", listSymbol);

		model.addAttribute("tradeStatus", tradeStatus);
		// Default Symbol - USD
		model.addAttribute("defaultSymbol", defaultSymbol);
		// Last Price
		Double lastPrice = symbolService.getLastPrice(defaultSymbol);
		model.addAttribute("lastPrice", lastPrice);
		// High
		Double highPrice = symbolService.getHighPrice(defaultSymbol);
		model.addAttribute("highPrice", highPrice);
		// Low
		Double lowPrice = symbolService.getLowPrice(defaultSymbol);
		model.addAttribute("lowPrice", lowPrice);
		// Volume
		Long volume = symbolService.getVolume(defaultSymbol);
		model.addAttribute("volume", volume);
		// AVG
		Double average = symbolService.getAverage(defaultSymbol);
		model.addAttribute("average", average);
		model.addAttribute("accountNo", (exchangeSystemSession.getUser()
				.getAccountNumber() != null) ? exchangeSystemSession.getUser()
				.getAccountNumber() : "");

		List<SymbolBalance> listSymbolBalances = new ArrayList<SymbolBalance>();
		Double balance;
		SymbolBalance symbolBalance;
		for (Symbol symbol : symbolList) {

			balance = accountService.getBalance(symbol);
			symbolBalance = new SymbolBalance();
			symbolBalance.setTotalBalance(balance);
			symbolBalance.setSymbol(symbol);
			
			listSymbolBalances.add(symbolBalance);

		}
		
		model.addAttribute("listSymbolBalances", listSymbolBalances);
		
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

		return "account";
	}

	@RequestMapping(value = "/addBank", method = RequestMethod.POST)
	public String addBank(@ModelAttribute("deposit") Deposit deposit,
			@ModelAttribute("bank") Bank bank,
			@ModelAttribute("withdrawal") Withdrawal withdrawal, Model model) {

		bankService.add(bank);

		List<Bank> bankList = bankService.findAll();
		List<Country> countryList = countryService.findAll();
		List<Symbol> symbolList = symbolService.findAll();

		model.addAttribute("countryList", countryList);

		model.addAttribute("deposit", deposit);
		model.addAttribute("withdrawal", withdrawal);
		model.addAttribute("bank", bank);
		model.addAttribute("bankList", bankList);
		model.addAttribute("symbolList", symbolList);

		return "redirect:/accounts";
	}

	@RequestMapping(value = "/account/deposit", method = RequestMethod.POST)
	public String addDeposit(@ModelAttribute("deposit") Deposit deposit,
			@ModelAttribute("bank") Bank bank,
			@ModelAttribute("withdrawal") Withdrawal withdrawal, Model model) {

		depositService.add(deposit);

		return "redirect:/accounts";
	}

	@RequestMapping(value = "/account/withdrawal", method = RequestMethod.POST)
	public String addWithdrawal(
			@ModelAttribute("withdrawal") Withdrawal withdrawal, Model model) {

		withdrawalService.add(withdrawal);

		return "redirect:/accounts";
	}

	
}
