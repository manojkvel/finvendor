package com.finvendor.server.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.model.Example;
import com.finvendor.server.example.ifc.IExampleService;

@Service
@Transactional(readOnly = true)
public class ExampleService implements IExampleService {

	@Autowired
	private ExampleDaoImpl dao;

	@Override

	public long createNewExample(Example example) {
		Long save = (Long) dao.save(example);
		return save;
	}
}
