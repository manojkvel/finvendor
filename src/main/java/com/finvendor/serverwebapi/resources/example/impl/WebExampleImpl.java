//package com.finvendor.serverwebapi.resources.example.impl;
//
//import com.finvendor.model.Example;
//import com.finvendor.server.example.service.IExampleService;
//import com.finvendor.server.example.staticpojo.ExampleDto;
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.example.Contact;
//import com.finvendor.serverwebapi.resources.example.IWebExample;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//
///**
// *
// * @author ayush on April 30, 2018
// */
//@Controller
//public class WebExampleImpl implements IWebExample {
//
//	@Autowired
//	IExampleService exampleService;
//
//	@Override
//	public void saveExample(@RequestBody ExampleDto example1Pojo) throws WebApiException {
//		final Example example1Entity = new Example();
//		example1Entity.setName(example1Pojo.getName());
//		exampleService.saveOrUpdateExample1(example1Entity);
//	}
//
//	@Override
//	public List<ExampleDto> findAllExample() throws WebApiException {
//		return exampleService.findAllExample();
//	}
//
//    @Override
//    public ExampleDto findExample() throws WebApiException {
//	    ExampleDto pojo=new ExampleDto();
//	    pojo.setId(1);
//	    pojo.setName("Dummy");
//        return pojo;
//    }
//
//    @Override
//	public void updateExample(@RequestBody ExampleDto examplePojo) throws WebApiException {
//		exampleService.updateExample(examplePojo);
//
//	}
//
//    public Contact contact(@PathVariable("id") Integer id) {
//        // mimics a call to a business service
//        return new Contact(1,"Test Firstname","Test Lastname");
//    }
//}
