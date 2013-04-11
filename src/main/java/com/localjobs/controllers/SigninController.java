package com.localjobs.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.localjobs.utils.SecurityUtils;

@Controller
public class SigninController {

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin() throws Exception {
		String username = SecurityUtils.getCurrentLoggedInUsername();

		if (StringUtils.isNotBlank(username) && !StringUtils.equals(username, "anonymousUser")) {
			System.out.println("User with username logged in .. " + username);
			return "redirect:/home";
		}
		System.out.println("No user logged in ..." + username);
		return "signin";

	}
}