package com.jung.basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private final BasicService svc;
	HelloController(BasicService svc){
		this.svc=svc;
	}
	;
	@GetMapping("/hello")
	public String hello() {
		return svc.getMessage("Test");
	}
}
