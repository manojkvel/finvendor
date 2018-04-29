package com.finvendor.server.example.dao.impl;

import org.springframework.stereotype.Repository;

import com.finvendor.model.Example;
import com.finvendor.server.common.dao.infra.GenericDao;

@Repository
public class ExampleDaoImpl extends GenericDao<Example> {

	public void hello() {
		System.out.println("Hello Sir...");
	}

}
