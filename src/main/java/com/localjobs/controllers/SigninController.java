package com.localjobs.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.localjobs.utils.SecurityUtils;

@Controller
public class SigninController {

	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signin() {
		String username = SecurityUtils.getCurrentLoggedInUsername();
		if(StringUtils.isNotBlank(username)){
			return "redirect:/home";
		}
		return "signin";
		
	}
}