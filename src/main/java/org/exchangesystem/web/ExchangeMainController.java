package org.exchangesystem.web;

import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.model.ExchangeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	/***
	 *The default page is here 
	 **/
	@RequestMapping(value={"/", "/main", "/exchangesystem", "/exchangesystem/", ""}, method=RequestMethod.GET)
	public String main(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser, ModelMap model) {
		model.addAttribute("user", exchangeSystemSession.getUser());
		return "main";
	}
	
	

}
