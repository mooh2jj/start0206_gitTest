package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	// http://localhost:8024/usr/home/main
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		System.out.println("Hello1234");
		return "hi1234123222";
	}
}
