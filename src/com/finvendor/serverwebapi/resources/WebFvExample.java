package com.finvendor.serverwebapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.finvendor.model.Roles;
import com.finvendor.server.example.ifc.IExampleService;
import com.finvendor.serverwebapi.exception.WebApiException;
import com.finvendor.serverwebapi.resources.ifc.WebFvExampleIfc;

@Controller
public class WebFvExample implements WebFvExampleIfc {

	@Autowired
	IExampleService exampleService;

	@Override
	public List<Roles> getRoles() throws WebApiException {
		try {
			return exampleService.getRoles(Roles.FETCH_ALL_ROLES);
		} catch (Exception e) {
			throw new WebApiException("REST API Error, cause: " + e.getMessage(), e);
		}
	}
}
