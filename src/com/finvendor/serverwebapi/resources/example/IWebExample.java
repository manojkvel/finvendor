package com.finvendor.serverwebapi.resources.example;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.server.example.staticpojo.ExamplePojo;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * This interface is for quick testing at web level
 * 
 * @author ayush on April 30, 2018
 */
@RequestMapping(value = WebUriConstants.BASE_URI)
public interface IWebExample {

	@RequestMapping(value = "/saveexample", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	void saveExample(ExamplePojo examplePojo) throws WebApiException;

	@RequestMapping(value = "/findallexample", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<ExamplePojo> findAllExample() throws WebApiException;

}
