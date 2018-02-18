package com.finvendor.server.common.dao.ifc;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbsCommonDao implements ICommonDao {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public AbsCommonDao() {
	}

}
