package com.spring.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.mvc.controller.model.ApplicationResponse;
import com.spring.web.mvc.controller.model.Login;
import com.spring.web.mvc.service.CarService;

@Controller
public class AuthController {

	@Autowired
	private CarService carService;
	
	@GetMapping("/auth")
	public String showLoginPage(){
		return "login"; //welcome.jsp
	}
	
	@PostMapping("/jauth")
	@ResponseBody public ApplicationResponse jauthUser(@ModelAttribute Login  login){
		String result=carService.validateUser(login);
		ApplicationResponse applicationResponse=new ApplicationResponse();
		applicationResponse.setStatus(result);
		applicationResponse.setMessage("User authentication!");
		//@ResponseBody - do not go to view resolver and convert object into JSON
		return applicationResponse; //login.jsp
	}
	
	@PostMapping("/auth")
	public String authUser(@ModelAttribute Login  login,Model model){
		String result=carService.validateUser(login);
		model.addAttribute("imageName",result);
		return "login"; //welcome.jsp
	}

}
