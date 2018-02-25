package com.finvendor.server.common.dao.ifc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.common.util.StringUtil;

public abstract class AbsCommonDao implements ICommonDao {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public AbsCommonDao() {
	}
	
	protected String transformToJson(String key, Set<List<String>> results) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		map.put(key, results);
		String createJsonFromParamsMap = "";
		createJsonFromParamsMap = JsonUtil.createJsonFromParamsMap(map);
		return createJsonFromParamsMap;
	}
	
	protected List<?> findAll(Class<?> claaz) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(claaz);
		List<?> list = criteria.list();
		return list;
	}
	
	//TODO need to think more in this
	protected Set<List<String>> fetchTuples(List<?> list, Class<?> claaz, String[] cols) throws RuntimeException{
		Set<List<String>> results = new HashSet<>();
		try {
			Class<?> noparams[] = {};
			for (int i = 0; i < list.size(); i++) {
				Object object = list.get(i);
				
				List<String> ll = new ArrayList<>();
				for (String s : cols) {
					Method method = claaz.getDeclaredMethod("get" + StringUtil.toSentenseCase(s), noparams);
					Object value = method.invoke(object, null);
					if (value != null) {
						// Below we support 2 types only
						if (value instanceof Integer) {
							Integer id = (Integer) value;
							ll.add(String.valueOf(id));
						} else if (value instanceof String) {
							ll.add((String) value);
						}
					}
				}
				
				if (ll.size() > 0) {
					results.add(ll);
				}
			}
		} catch (IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return results;
	}
}
