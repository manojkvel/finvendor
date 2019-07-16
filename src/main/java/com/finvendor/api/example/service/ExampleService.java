package com.finvendor.api.example.service;

import com.finvendor.api.example.dao.ExampleDao;
import com.finvendor.api.example.dto.ExampleRequestDto;
import com.finvendor.model.Example;
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

	public List<ExampleRequestDto> findAllExample() {
		List<Example> allEntity = exampleDao.findAll();
		List<ExampleRequestDto> exmaple1PojoList = new ArrayList<>();
		for (Example e : allEntity) {
			ExampleRequestDto example1Pojo = new ExampleRequestDto();
			example1Pojo.setId(e.getId());
			example1Pojo.setName(e.getName());
			example1Pojo.setPhone(e.getPhone());
			exmaple1PojoList.add(example1Pojo);
		}
		return exmaple1PojoList;
	}

	public Example findExampleById(Serializable id) {
		return null;
	}

	public void updateExample(ExampleRequestDto pojo) {
		Example exampleEntity=new Example();
		exampleEntity.setId(pojo.getId());
		exampleEntity.setName(pojo.getName());
		exampleEntity.setPhone(pojo.getPhone());
		exampleDao.saveOrUpdate(exampleEntity);
	}
}