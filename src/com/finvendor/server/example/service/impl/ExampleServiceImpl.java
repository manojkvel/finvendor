package com.finvendor.server.example.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.model.Example;
import com.finvendor.server.example.dao.impl.ExampleDaoImpl;
import com.finvendor.server.example.service.IExampleService;
import com.finvendor.server.example.staticpojo.ExamplePojo;

@Service
public class ExampleServiceImpl implements IExampleService {
	
	@Autowired
	private ExampleDaoImpl exampleDao;


	@Override
	@Transactional(readOnly = false)
	public void saveOrUpdateExample1(Example class1) {
		exampleDao.saveOrUpdate(class1);
		exampleDao.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExamplePojo> findAllExample() {
		exampleDao.hello();
		List<Example> allEntity = exampleDao.findAll();
		List<ExamplePojo> exmaple1PojoList = new ArrayList<>();
		for (Example e : allEntity) {
			ExamplePojo example1Pojo = new ExamplePojo();
			example1Pojo.setId(e.getId());
			example1Pojo.setName(e.getName());
			exmaple1PojoList.add(example1Pojo);
		}
		return exmaple1PojoList;
	}

	@Override
	@Transactional(readOnly = true)
	public Example findExampleById(Serializable id) {
		return null;
	}

	@Override
	public void hello() {
		exampleDao.hello();
		
	}

	@Override
	@Transactional(readOnly = false)
	public void updateExample(ExamplePojo pojo) {
		exampleDao.updateExample(pojo);
	}

}