package com.finvendor.serverwebapi.resources.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.finvendor.model.Example;
import com.finvendor.server.example.ifc.IExampleService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.common.WebExampleIfc;

@Controller
public class WebExample implements WebExampleIfc {

	@Autowired
	IExampleService exampleService;

	@Override
	public long createExample() throws WebApiException {
		try {
			Example exmpl=new Example();
			exmpl.setName("Example-1");
			return exampleService.createNewExample(exmpl);
		} catch (Exception e) {
			throw new WebApiException("REST API Error, cause: " + e.getMessage(), e);
		}
	}
}
