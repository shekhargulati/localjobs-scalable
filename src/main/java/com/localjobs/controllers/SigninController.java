package com.localjobs.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.localjobs.utils.SecurityUtils;

@Controller
public class SigninController {

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void signin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username = SecurityUtils.getCurrentLoggedInUsername();

		System.out.println("Username .. " + username);
		if (StringUtils.isNotBlank(username) && !StringUtils.equals(username, "anonymousUser")) {
			response.sendRedirect("/home");
		}

	}
}