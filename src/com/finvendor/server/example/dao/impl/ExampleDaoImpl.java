package com.finvendor.server.example.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.Example;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.common.dao.infra.GenericDao;
import com.finvendor.server.example.dao.IExampleDao;
import com.finvendor.server.example.staticpojo.ExamplePojo;

@Repository
public class ExampleDaoImpl extends GenericDao<Example> implements IExampleDao {
	@Autowired
	private ICommonDao commonDao;
	public void hello() {
		System.out.println("Hello Sir...");
	}

	@Override
	public void updateExample(ExamplePojo pojo) {
		String sql="update example set name=? where id=?";
		SQLQuery sqlQuery = commonDao.getNativeQuery(sql, new String[] {pojo.getName(),String.valueOf(pojo.getId())});
		int executeUpdate = sqlQuery.executeUpdate();
		System.out.println("Query Update Status:"+executeUpdate);
	}
}
