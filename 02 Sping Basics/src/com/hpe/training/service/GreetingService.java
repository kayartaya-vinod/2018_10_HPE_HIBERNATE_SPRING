package com.hpe.training.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {

	@Autowired(required = false)
	private HelloService helloService;
	
	public GreetingService() {
		System.out.println("GreetingService object created");
		System.out.println("In GreetingService constructor, helloService is " + helloService);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("In GreetingService.init(), helloService is " + helloService);
	}
	public void setHelloService(HelloService helloService) {
		this.helloService = helloService;
	}
}
