package org.exchangesystem.web;

import org.springframework.stereotype.Controller;

@Controller
public class PersonController {

//	@Autowired
//	private SessionFactory sessionFactory;
//
//	@RequestMapping(value="/addPerson.htm", method=RequestMethod.POST)
//	public ModelAndView addPerson(Person p, Errors errors)
//	{
//		if (errors.hasErrors())
//		{
//			ModelAndView mav = new ModelAndView("addPerson");
//			mav.addObject("errors", errors);
//			return mav;
//		}
//		
//		sessionFactory.getCurrentSession().save(p);	
//		return new ModelAndView("redirect:/allPersons.htm");
//	}
//
//	@RequestMapping(value="/addPerson.htm")
//	public String addPerson()
//	{
//		return "addPerson";
//	}
//
//	
//	
//	@RequestMapping("/allPersons.htm")
//	public ModelAndView allPersons()
//	{
//		List<Person> persons = sessionFactory.getCurrentSession().
//			createQuery("FROM Person").list();
//		
//		ModelAndView mav = new ModelAndView("allPersons");
//		mav.addObject("persons", persons);
//		return mav;
//	}
	
}
