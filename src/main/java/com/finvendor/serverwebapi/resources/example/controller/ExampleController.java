package com.finvendor.serverwebapi.resources.example.controller;

import com.finvendor.model.Example;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import com.finvendor.serverwebapi.resources.example.dto.ExampleDto;
import com.finvendor.serverwebapi.resources.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 
 * @author ayush on April 30, 2018
 */
@Controller
@RequestMapping(value = WebUriConstants.BASE_URI)
public class ExampleController {

	@Autowired
	ExampleService exampleService;

	@RequestMapping(value = "/saveexample", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveExample(@RequestBody ExampleDto exampleDto) throws WebApiException {
		final Example example1Entity = new Example();
		example1Entity.setName(exampleDto.getName());
		exampleService.saveOrUpdateExample1(example1Entity);
	}

	@RequestMapping(value = "/findallexample", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ExampleDto> findAllExample() throws WebApiException {
		return exampleService.findAllExample();
	}

	@RequestMapping(value = "/findexample", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExampleDto findExample() throws WebApiException {
		ExampleDto pojo=new ExampleDto();
	    pojo.setId(1);
	    pojo.setName("Dummy");
        return pojo;
    }

	@RequestMapping(value = "/updateexample", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateExample(@RequestBody ExampleDto exampleDto) throws WebApiException {
		exampleService.updateExample(exampleDto);
		
	}
}
