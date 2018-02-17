package com.finvendor.server.example;

import org.springframework.stereotype.Repository;

import com.finvendor.model.Example;
import com.finvendor.server.common.dao.ifc.AbsCrudDao;
import com.finvendor.server.example.ifc.IExampleDao;

@Repository
public class ExampleDaoImpl extends AbsCrudDao<Example> implements IExampleDao {

	
}
