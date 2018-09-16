package com.finvendor.server.example.dao.impl;

import com.finvendor.model.Example;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.example.dao.IExampleDao;
import com.finvendor.server.example.staticpojo.ExamplePojo;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleDaoImpl extends GenericDao<Example> implements IExampleDao {
	@Autowired
	private ICommonDao commonDao;

	@Override
	public void updateExample(ExamplePojo pojo) {
		String sql = "update example set name=? where id=?";
		SQLQuery sqlQuery = commonDao.getNativeQuery(sql,
				new String[] { pojo.getName(), String.valueOf(pojo.getId()) });
		int executeUpdate = sqlQuery.executeUpdate();
		System.out.println("Query Update Status:" + executeUpdate);
	}
}
