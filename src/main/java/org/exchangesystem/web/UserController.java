package org.exchangesystem.web;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exchangesystem.dao.ExchangeUserDao;
import org.exchangesystem.dao.UserRoleDao;
import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.SignInLog;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeStatus;
import org.exchangesystem.model.UserRole;
import org.exchangesystem.service.AccountService;
import org.exchangesystem.service.ExchangeUserService;
import org.exchangesystem.service.SignInLogService;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user")
public class UserController {
	protected static Logger logger = Logger.getLogger(UserController.class); 
	
	@Autowired
	ExchangeUserService userService;
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	SymbolService symbolService;
	
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	ExchangeUserDao userDao;
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	@Autowired
	SignInLogService signInLogService;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value={"/login"}, method=RequestMethod.GET)
	@Transactional
	public String login(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser){
		return "login";
	}
	
	@RequestMapping(value={"/login"}, method=RequestMethod.POST)
	@Transactional
	public String loginSave(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser){
		return "main";
	}
	
	@RequestMapping(value={"/registration"}, method=RequestMethod.GET)
	@Transactional
	public String registration(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser){
		logger.info("In Registration !!!!!!!!!!1");
		return "registration";
	}
	
	@RequestMapping(value={"/registration"}, method=RequestMethod.POST)
	@Transactional
	public String registrationSave(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser){
		logger.info(" Saving the Registration Details ");
		exchangeUser.setJoinDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		//projectUser.setJoinDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		userDao.save(exchangeUser);
		ExchangeUser user;
		user = userDao.findUser(exchangeUser.getEmail());
		
		logger.info("Email !!! "+exchangeUser.getEmail());
		logger.info("Retrieved User !!! "+user);
		logger.info("Retrieved User ID !!! "+user.getId());
		UserRole userRole = new UserRole();
		userRole.setAuthority("ROLE_USER");
		userRole.setUser(user);
		userRoleDao.save(userRole);
		
		//Generate Account Number
		
		Long currentSequence = userService.getLastAccountNo();
		String nextNo = "";
		
		for (ExchangeUser selectedUser : userService.getAllUsersWithoutAccountNo()) {
			currentSequence = currentSequence + 1;
			//Generate 
			//nextNo = currentSequence.toString();
			nextNo = String.format("%08d", currentSequence);
			nextNo = "ABX"+nextNo;
			
			selectedUser.setAccountSequence(currentSequence);
			selectedUser.setAccountNumber(nextNo);
			userService.update(selectedUser); 
			
		}
		
		return "login";
	}
	
	@RequestMapping(value={"/userlist"}, method=RequestMethod.GET)
	public String usersList(){
		return "userlist";
	}
	
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(@ModelAttribute("tradeStatus") TradeStatus tradeStatus,  ModelMap model) {
 
		model.addAttribute("error", "true");
		return "home";
 
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome(Principal principal, ModelMap model){
		logger.info("DDDDDDDDDDDDDDD The logged in guy is "+principal.getName());
		ExchangeUser user = userService.findUser(principal.getName());
		exchangeSystemSession.setUser(user);
		
		SignInLog signInLog = new SignInLog();
		signInLog.setNames(user.getFirstName()+" "+user.getLastName());
		signInLogService.add(signInLog);
		
		List<Symbol> listSymbol = symbolService.findAll();
		
		
		TradeStatus tradeStatus;

		if (exchangeSystemSession.getTradeStatus() == null){
			tradeStatus  = new TradeStatus();
			tradeStatus.setSymbol(symbolService.findSymbol("USD"));
			
		} else{
			tradeStatus = exchangeSystemSession.getTradeStatus();
		}
		
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
	
	@RequestMapping(value={"/logout"}, method=RequestMethod.GET)
	public String logout(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser, @ModelAttribute("tradeStatus") TradeStatus tradeStatus,  HttpSession session, ModelMap model) {
		model.remove(exchangeUser);
		//model.put("user", null);
		model.addAttribute("username", "");
		model.remove("logedInUser");
		model.addAttribute("tradeStatus", tradeStatus);
		
			
		SecurityContextHolder.getContext().setAuthentication(null);
		logger.info("LLLLLLLLLLLLLLL Initiate logout for "+session.getId());
		//Everything else doesnt seem to work, so I had to try this on 18/04/2012
		session.invalidate();
		//session.invalidate();
			return "redirect:/home";
 
	}
	

}
