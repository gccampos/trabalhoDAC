package com.trabalhoDAC.trabalhoDAC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServePaginasController {

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mav = new ModelAndView("cadastro");
		return mav;
	}
}
