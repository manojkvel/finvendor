package com.finvendor.serverwebapi.resources.ifc;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finvendor.model.Roles;
import com.finvendor.serverwebapi.resources.exception.RestApiException;

/**
 * This interface is for quick testing at web level
 * 
 * @author ayush
 */
@RequestMapping(value=RestApiUriConstants.BASE_URI)
public interface IExampleRestApi {

	@RequestMapping(value=RestApiUriConstants.VENDORS, method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	List<Roles> getRoles() throws RestApiException;
}
