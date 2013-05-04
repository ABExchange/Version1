package org.exchangesystem.web;

import java.util.List;

import org.exchangesystem.model.Bank;
import org.exchangesystem.model.Country;
import org.exchangesystem.model.Deposit;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TransferMethod;
import org.exchangesystem.model.Withdrawal;
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
	
	@RequestMapping(value="/accounts", method=RequestMethod.GET)
	public String account(@ModelAttribute("deposit") Deposit deposit, @ModelAttribute("bank") Bank bank, @ModelAttribute("withdrawal") Withdrawal withdrawal, Model model) {
		
		List<Bank> bankList = bankService.findAll();
		List<Country> countryList = countryService.findAll();
		List<Symbol> symbolList = symbolService.findAll();
		List<TransferMethod> transferMethodList = transferMethodService.findAll();
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

		return "account";
	}
	
	
	@RequestMapping(value="/addBank", method=RequestMethod.POST)
	public String addBank(@ModelAttribute("deposit") Deposit deposit, @ModelAttribute("bank") Bank bank, @ModelAttribute("withdrawal") Withdrawal withdrawal, Model model){

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
	
	@RequestMapping(value="/account/deposit", method=RequestMethod.POST)
	public String addDeposit(@ModelAttribute("deposit") Deposit deposit, @ModelAttribute("bank") Bank bank, @ModelAttribute("withdrawal") Withdrawal withdrawal, Model model){
		
		depositService.add(deposit);
		
		
		
		return "redirect:/accounts";
	}
	
	@RequestMapping(value="/account/withdrawal", method=RequestMethod.POST)
	public String addWithdrawal(@ModelAttribute("withdrawal") Withdrawal withdrawal, Model model){
		
		withdrawalService.add(withdrawal);
		
		return "redirect:/accounts";
	}
}
