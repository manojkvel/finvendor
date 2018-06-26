package com.finvendor.serverwebapi.resources.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.finvendor.model.Example;
import com.finvendor.server.example.service.IExampleService;
import com.finvendor.server.example.staticpojo.ExamplePojo;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.example.IWebExample;

/**
 * 
 * @author ayush on April 30, 2018
 */
@Controller
public class WebExampleImpl implements IWebExample {

	@Autowired
	IExampleService exampleService;

	@Override
	public void saveExample(@RequestBody ExamplePojo example1Pojo) throws WebApiException {
		Example example1Entity = new Example();
		example1Entity.setName(example1Pojo.getName());
		exampleService.saveOrUpdateExample1(example1Entity);
	}

	@Override
	public List<ExamplePojo> findAllExample() throws WebApiException {
		return exampleService.findAllExample();
	}

	@Override
	public void updateExample(@RequestBody ExamplePojo examplePojo) throws WebApiException {
		exampleService.updateExample(examplePojo);
		
	}
}
