package com.finvendor.server.example.service;

import com.finvendor.model.Example;
import com.finvendor.server.example.staticpojo.ExamplePojo;

import java.io.Serializable;
import java.util.List;

/**
 * This interface is for quick testing at service level
 * 
 * @author ayush
 */
public interface IExampleService {
	void saveOrUpdateExample1(Example class1);
	Example findExampleById(Serializable id);
	List<ExamplePojo> findAllExample();
	void updateExample(ExamplePojo pojo);
}
