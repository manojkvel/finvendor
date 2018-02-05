package com.finvendor.common.dao.ifc;

import java.util.List;

import com.finvendor.model.Roles;

public interface ICommonDao {
	List<Roles> executeNamedQuery(String namedQueryname);
}
