package com.finvendor.server.example.ifc;

import java.util.List;

import com.finvendor.model.Roles;

/**
 * This interface is for quick testing at service level
 * 
 * @author ayush
 */
public interface IExampleService {

	List<Roles> getRoles(String sql);
}
