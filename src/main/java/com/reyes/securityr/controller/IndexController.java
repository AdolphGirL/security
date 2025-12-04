package com.reyes.securityr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {
	
	/***
	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "Welcome to SecurityR!";
	}**/
	
	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login-error")
	public String loginError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", "帳號或密碼錯誤！");
		return "redirect:/login";
	}

	@GetMapping("/logout-success")
	public String logoutSuccess(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("logoutMessage", "已成功登出。");
		return "redirect:/login";
	}
	
}
