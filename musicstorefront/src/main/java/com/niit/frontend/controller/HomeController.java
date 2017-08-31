package com.niit.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public ModelAndView showHome()
	{
		ModelAndView mv=new ModelAndView("Welcome");
		return mv;
	}

	@RequestMapping("/Login")
	public ModelAndView showLogin()
	{
		ModelAndView mv=new ModelAndView("Login");
		return mv;
	}
	@RequestMapping("/Register")
	public ModelAndView showRegister()
	{
		ModelAndView mv=new ModelAndView("registration");
		return mv;
	}
	@RequestMapping("/Home")
	public ModelAndView showIndex()
	{
		ModelAndView mv=new ModelAndView("Home");
		return mv;
	}
	@RequestMapping("/MusicStore")
	public ModelAndView showMain()
	{
		ModelAndView mv=new ModelAndView("MusicStore");
		return mv;
	}
	@RequestMapping("/chkLogin")
	public ModelAndView chkLogin(@RequestParam("txtEmail")String mail,@RequestParam("txtPass")String pass)
	{
		ModelAndView mv;
		if(mail.equals("user1@gmail.com")&&pass.equals("user1"))
		{
			mv=new ModelAndView("Home");
			mv.addObject("loggedInUser", "user");
			return mv;
		}
		else if(mail.equals("admin@gmail.com")&&pass.equals("admin"))
		{
			mv=new ModelAndView("AdminHome");
			mv.addObject("loggedInUser", "admin");
			return mv;
		}
		else
		{
			mv=new ModelAndView("Login");
			
			return mv;
		}
	}
	@RequestMapping("/AddToCart")
	public ModelAndView showCart()
	{
		ModelAndView mv=new ModelAndView("AddToCart");
		return mv;
	}
	@RequestMapping("/Cart")
	public ModelAndView showCartPage()
	{
		ModelAndView mv=new ModelAndView("Cart");
		return mv;
	}
}
