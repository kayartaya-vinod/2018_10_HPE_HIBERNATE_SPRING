package com.hpe.training.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloService {

	@Autowired(required = false)
	private GreetingService greetingService;

	public HelloService() {
		System.out.println("HelloService object created!");
		System.out.println("In HelloService constructor, greetingService is " + greetingService);
	}

	@PostConstruct
	public void init() {
		System.out.println("In HelloService.init(), greetingService is " + greetingService);
	}

	public void setGreetingService(GreetingService greetingService) {
		this.greetingService = greetingService;
	}
}
