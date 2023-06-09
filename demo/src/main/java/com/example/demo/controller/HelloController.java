package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HelloService;

@RestController
@RequestMapping("/clients")
public class HelloController {
	
	private final HelloService helloService;
	
	public HelloController(HelloService helloService){
		this.helloService = helloService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String hello() {
		return helloService.hello();
	}
	
	@GetMapping("/bonjour")
	public String bonjour(@RequestParam(defaultValue = "") String name) {
		return helloService.bonjour(name);
	}

}
