package com.niit.frontend.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.musicstorebackend.dao.IProductDAO;
import com.niit.musicstorebackend.dao.IUserDAO;
import com.niit.musicstorebackend.model.User;

@Controller
public class HomeController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	IUserDAO userDAO;

	@Autowired
	User user;
	@Autowired
	IProductDAO productDAO;

	@ModelAttribute
	public User returnObject() {
		return new User();
	}

	@RequestMapping("/")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("Welcome");
		return mv;
	}

	@RequestMapping("/{id}/Welcome")
	public ModelAndView showWelcome() {
		ModelAndView mv = new ModelAndView("Welcome");
		return mv;
	}

	@RequestMapping("/Login")
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}

	@RequestMapping("/Register")
	public ModelAndView showRegister(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView("registration");
		return mv;
	}

	@RequestMapping("/Home")
	public ModelAndView showIndex() {
		ModelAndView mv = new ModelAndView("Home");
		return mv;
	}

	@RequestMapping("/MusicStore")
	public ModelAndView showMain() {
		ModelAndView mv = new ModelAndView("MusicStore");
		return mv;
	}

	@RequestMapping("/chkLogin")
	public ModelAndView chkLogin(@RequestParam("txtEmail") String mail, @RequestParam("txtPass") String pass) {
		ModelAndView mv;

		if (mail.equals("user1@gmail.com") && pass.equals("user1")) {
			mv = new ModelAndView("Home");
			mv.addObject("loggedInUser", "user");
			return mv;
		} else if (mail.equals("admin@gmail.com") && pass.equals("admin")) {
			mv = new ModelAndView("AdminHome");
			mv.addObject("loggedInUser", "admin");
			return mv;
		} else {
			mv = new ModelAndView("Login");

			return mv;
		}

	}

	@RequestMapping("/{id}/viewDetails")
	public String showDetails(@PathVariable Integer id, ModelMap model) {

		model.addAttribute("product", productDAO.get(id));

		return "viewDetails";

	}

	@RequestMapping("/{id}/Header")
	public ModelAndView showHeader() {
		ModelAndView mv = new ModelAndView("Header");
		return mv;
	}

	@RequestMapping("/Signout")
	public ModelAndView showLogout() {
		ModelAndView mv = new ModelAndView("Welcome");
		return mv;
	}

	@RequestMapping("/{id}/perform_logout")
	public ModelAndView showLogout(HttpServletRequest request, WebRequest req, SessionStatus status) {
		System.out.println("logout");
		ModelAndView mv = new ModelAndView("Welcome");
		//session.invalidate();
		//session = request.getSession(true);
		// Category category=new Category();
		status.setComplete();
	    req.removeAttribute("loggedInUser", WebRequest.SCOPE_SESSION);
		mv.addObject("logOutMessage", "u hv successfully logged out..");
		mv.addObject("loggedOut", "true");

		return mv;
	}
	/* security check for login */

	@RequestMapping(value = "/login_session_attributes")
	/* getting values from textbox */

	public String login_session_attributes(HttpSession session, Model model,
			@RequestParam(value = "username") String id) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		System.out.println("inside security check");

		session.setAttribute("name", name);
		System.out.println(name);

		user = userDAO.get(id);
		
		 int x = user.getUserid(); 
		 
		 session.setAttribute("email", user.getEmailid());
		 
		session.setAttribute("loggedInUser", user.getUsername());

		
		  System.out.println("x value is"+x);
		  
		  session.setAttribute("loggedInUserID", x);
		 

		session.setAttribute("LoggedIn", "true");

		@SuppressWarnings("unchecked")
		/* getting values from database */
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		String role = "ROLE_USER";
		for (GrantedAuthority authority : authorities) {
			/* comparing both the values from txtbox and database */
			if (authority.getAuthority().equals(role)) {
				System.out.println(role);
				model.addAttribute("userList", userDAO.getAllUsers());
				System.out.println("user logged");
				return "Welcome";
			} else {
				session.setAttribute("isAdmin", "true");
			}
		}
		System.out.println("admin logged");
		model.addAttribute("userList", userDAO.getAllUsers());
		return "AdminHome";

	}

	@RequestMapping(value = "/addus", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletRequest request,
			HttpSession session,Model model) {
		System.out.println(user.getCpassword());
		System.out.println(user.getPassword());

		if(result.hasErrors()){
			
			return "registration";	
		}
		else
		{
		user.setEnabled("true");
		user.setRole("ROLE_USER");
			
		if (user.getCpassword().equals(user.getPassword())) {

			userDAO.addUser(user);
		session = request.getSession(false);
		session.setAttribute("email", user.getEmailid());
		session.setAttribute("loggedInUser", user.getUsername());
		String email1 = (String) session.getAttribute("email");
		String usernaam = (String) session.getAttribute("loggedInUser");

		// String email1="sandhyasri_a@yahoo.com";
		String recipientAddress = email1;
		String subject = "User Registration";
		String message = "User Registered successfully\n" + "The Details ..r.." + "\n User Name:" + usernaam
				+ "\n Phone no:" + user.getPhone();
		// String message="User Registered successfully";
		// prints on console
		System.out.println("To:" + recipientAddress);
		System.out.println("Subject:" + subject);
		System.out.println("Message:" + message);

		// creats a simple e-mail obj.

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);

		// sends the e-mail
		mailSender.send(email);
		
		MultipartFile image =user.getImg();
		Path path;// belong to nio package
		path = Paths.get("C:/Users/Sandhya/git/musicstorefront/src/main/webapp/pics/" + user.getUsername() + ".jpg");
		System.out.println("Path=" + path);
		System.out.println("File name" + user.getImg().getOriginalFilename());
		if (image != null && !image.isEmpty()) {
			try {
				image.transferTo(new File(path.toString()));
				System.out.println("Image Saved in:" + path.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Image not saved");

			}

		}
		return "Login";
		}
		else
		{
			model.addAttribute("errMsg", "Both the passwords are not matching");
			return "registration";
		}
	}
		
	}

	@RequestMapping("{id}/Profile")
	public String showProfile(@PathVariable Integer id, ModelMap model) {

		model.addAttribute("user", userDAO.getbyid(id));

		return "Profile";
	}

}
