package com.finvendor.serverwebapi.resources.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.WebUriConstants;

/**
 * This interface is for quick testing at web level
 * 
 * @author ayush
 */
@RequestMapping(value=WebUriConstants.BASE_URI)
public interface IWebExample {

	@RequestMapping(value=WebUriConstants.VENDORS, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	long createExample() throws WebApiException;
}
