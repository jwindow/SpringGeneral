package com.jung.basic;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicService.class); 
	
	public String getMessage(String msg) {
		LOGGER.debug("received message:{}",msg);
		return "Hello,"+msg+" Today is "+LocalDate.now();
	}
	
	
	public String logMessage(String msg) {
		LOGGER.debug("{}",msg);
		LOGGER.info("{}",msg);
		LOGGER.warn("{}",msg);
		LOGGER.error("{}",msg);
		LOGGER.trace("{}",msg);
		return "";
	}
}
