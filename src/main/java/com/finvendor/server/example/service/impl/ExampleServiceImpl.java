//package com.finvendor.server.example.service.impl;
//
//import com.finvendor.model.Example;
//import com.finvendor.server.example.dao.impl.ExampleDaoImpl;
//import com.finvendor.server.example.service.IExampleService;
//import com.finvendor.server.example.staticpojo.ExampleDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ExampleServiceImpl implements IExampleService {
//
//	@Autowired
//	private ExampleDaoImpl exampleDao;
//
//
//	@Override
//	@Transactional(readOnly = false)
//	public void saveOrUpdateExample1(Example class1) {
//		exampleDao.saveOrUpdate(class1);
//		exampleDao.flush();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public List<ExampleDto> findAllExample() {
//		List<Example> allEntity = exampleDao.findAll();
//		List<ExampleDto> exmaple1PojoList = new ArrayList<>();
//		for (Example e : allEntity) {
//			ExampleDto example1Pojo = new ExampleDto();
//			example1Pojo.setId(e.getId());
//			example1Pojo.setName(e.getName());
//			exmaple1PojoList.add(example1Pojo);
//		}
//		return exmaple1PojoList;
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Example findExampleById(Serializable id) {
//		return null;
//	}
//
//
//
//	@Override
//	@Transactional(readOnly = false)
//	public void updateExample(ExampleDto pojo) {
//		exampleDao.updateExample(pojo);
//	}
//
//}