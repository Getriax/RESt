package com.demo.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestCont {
	
	private final String txt = "Hello, %s";
	private final AtomicLong atomicLong = new AtomicLong();
	
	@RequestMapping(value = "/greet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Greeter greeter(@RequestParam(value="name", defaultValue="Word") String name) {
		return new Greeter(atomicLong.incrementAndGet(), String.format(txt, name));
	}
	
}
