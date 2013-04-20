package org.exchangesystem.web;

import org.exchangesystem.model.ExchangeUser;
import org.springframework.stereotype.Controller;
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
	
	/***
	 *The default page is here 
	 **/
	@RequestMapping(value={"/", "/main"}, method=RequestMethod.GET)
	public String main(@ModelAttribute("exchangeUser") ExchangeUser exchangeUser) {
		return "main";
	}
	
	

}
