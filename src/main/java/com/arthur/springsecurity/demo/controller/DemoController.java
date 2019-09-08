package com.arthur.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	@GetMapping("/teachers")
	public String showLeaders() {
		return "teachers";
	}
	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}
}
