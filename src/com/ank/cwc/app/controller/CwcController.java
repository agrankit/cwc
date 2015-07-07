package com.ank.cwc.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ank.cwc.app.model.User;

@Controller
@RequestMapping("/login")
public class CwcController {

	private static Logger logger = Logger.getLogger(CwcController.class);
	
	@ModelAttribute("user")
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public User login(HttpServletRequest req)
	{
		User user = new User();
		user.setName("1");
		user.setPassword("123");
		user.setEmail("abs@gmail.com");
		logger.debug(user);
		return user;
		
	}
	
	
	@RequestMapping(value="/addmtch",method=RequestMethod.GET)
	public String getAddMatchesPage(ModelMap map)
	{
		return "addmatch";
	}
}
