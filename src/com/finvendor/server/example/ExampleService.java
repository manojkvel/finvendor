package com.finvendor.server.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finvendor.common.dao.ifc.ICommonDao;
import com.finvendor.model.Roles;
import com.finvendor.server.example.ifc.IExampleService;

@Service
public class ExampleService implements IExampleService {

	@Autowired
	private ICommonDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Roles> getRoles(String namedQueryname) {
		return dao.executeNamedQuery(namedQueryname);
	}
}
