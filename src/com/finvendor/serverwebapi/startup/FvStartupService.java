package com.finvendor.serverwebapi.startup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class FvStartupService {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void initIt() throws Exception {
		System.out.println("*******"+message);
	}

	@PreDestroy
	public void cleanUp() throws Exception {
		System.out.println("Spring Container is destroy! Customer clean up");
	}
}