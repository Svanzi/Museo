package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MuseoController {
	
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Model model) {
		return "index.html";
	}

	@RequestMapping("/info")
	public String info() {
		return "info.html";
	}
	
	@RequestMapping("/adminHome")
	public String adminHome() {
		return "admin/home.html";
	}
	
	@RequestMapping("/utenteHome")
	public String utenteHome() {
		return "/user/home.html";
	}
	
}
