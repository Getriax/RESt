package com.demo.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class GreetingController {
	private static final String template = "Hello, %s!";
	private static AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/home")
	public String greeter() {
		return "index";//new Greeter(counter.incrementAndGet(), String.format(template, name));
	}
}
