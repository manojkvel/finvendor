package com.finvendor.serverwebapi.resources.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.finvendor.model.Roles;
import com.finvendor.server.example.ifc.IExampleService;
import com.finvendor.serverwebapi.resources.exception.RestApiException;
import com.finvendor.serverwebapi.resources.ifc.IExampleRestApi;

@Controller
public class ExampleRestApi implements IExampleRestApi {

	@Autowired
	IExampleService exampleService;

	@Override
	public List<Roles> getRoles() throws RestApiException {
		try {
			return exampleService.getRoles(Roles.FETCH_ALL_ROLES);
		} catch (Exception e) {
			throw new RestApiException("REST API Error, cause: " + e.getMessage(), e);
		}
	}
}
