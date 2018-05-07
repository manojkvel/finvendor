package com.finvendor.server.example.service;

import java.io.Serializable;
import java.util.List;

import com.finvendor.model.Example;
import com.finvendor.server.example.staticpojo.ExamplePojo;

/**
 * This interface is for quick testing at service level
 * 
 * @author ayush
 */
public interface IExampleService {
	void hello();
	void saveOrUpdateExample1(Example class1);
	Example findExampleById(Serializable id);
	List<ExamplePojo> findAllExample();
}
