package org.exchangesystem.web;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.exchangesystem.dao.ExchangeUserDao;
import org.exchangesystem.dao.UserRoleDao;
import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.SignInLog;
import org.exchangesystem.model.UserRole;
import org.exchangesystem.service.ExchangeUserService;
import org.exchangesystem.service.SignInLogService;
import org.exchangesystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
	UserRoleDao userRoleDao;
	@Autowired
	ExchangeUserDao userDao;
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	@Autowired
	SignInLogService signInLogService;
	
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
		return "login";
	}
	
	@RequestMapping(value={"/userlist"}, method=RequestMethod.GET)
	public String usersList(){
		return "userlist";
	}
	
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome(Principal principal, ModelMap model){
		logger.info("DDDDDDDDDDDDDDD The logged in guy is "+principal.getName());
		ExchangeUser user = userService.findUser(principal.getName());
		exchangeSystemSession.setUser(user);
		
		SignInLog signInLog = new SignInLog();
		signInLog.setNames(user.getFirstName()+" "+user.getLastName());
		signInLogService.add(signInLog);
		
		model.addAttribute("user", exchangeSystemSession.getUser());
		return "main";
	}
	

}
