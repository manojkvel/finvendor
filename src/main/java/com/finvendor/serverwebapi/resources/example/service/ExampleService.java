package com.finvendor.serverwebapi.resources.example.service;

import com.finvendor.model.Example;
import com.finvendor.serverwebapi.resources.example.dao.ExampleDao;
import com.finvendor.serverwebapi.resources.example.dto.ExampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExampleService {
	
	@Autowired
	private ExampleDao exampleDao;


	public void saveOrUpdateExample1(Example class1) {
		exampleDao.saveOrUpdate(class1);
		exampleDao.flush();
	}

	public List<ExampleDto> findAllExample() {
		List<Example> allEntity = exampleDao.findAll();
		List<ExampleDto> exmaple1PojoList = new ArrayList<>();
		for (Example e : allEntity) {
			ExampleDto example1Pojo = new ExampleDto();
			example1Pojo.setId(e.getId());
			example1Pojo.setName(e.getName());
			exmaple1PojoList.add(example1Pojo);
		}
		return exmaple1PojoList;
	}

	public Example findExampleById(Serializable id) {
		return null;
	}

	public void updateExample(ExampleDto pojo) {
		Example exampleEntity=new Example();
		exampleEntity.setId(pojo.getId());
		exampleEntity.setName(pojo.getName());
		exampleDao.saveOrUpdate(exampleEntity);
	}
}