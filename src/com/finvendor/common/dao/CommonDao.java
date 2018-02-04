package com.finvendor.common.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.common.dao.ifc.ICommonDao;

@Repository
public class CommonDao implements ICommonDao {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean executeNativeQuery(String nativeSQL) {
		boolean runQueryStatus = false;
		if (sessionFactory != null) {
			runQueryStatus = true;
		}
		return runQueryStatus;
	}

}
