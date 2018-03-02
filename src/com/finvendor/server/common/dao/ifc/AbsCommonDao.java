package com.finvendor.server.common.dao.ifc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
		createJsonFromParamsMap = JsonUtil.createJsonFromObject(map);
		return createJsonFromParamsMap;
	}

	protected List<?> selectAll(Class<?> claaz) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(claaz);
		List<?> list = criteria.list();
		return list;
	}

	protected String transformToJson(List<?> list, Class<?> claaz, String[] cols) throws RuntimeException {
		List<String> ll = new ArrayList<>();
		List<Object> cache = new ArrayList<>();
		try {
			Class<?> noparams[] = {};
			for (int i = 0; i < list.size(); i++) {
				Object object = list.get(i);
				Map<String, Object> data = new LinkedHashMap<>();
				for (String s : cols) {
					Method method = claaz.getDeclaredMethod("get" + StringUtil.toSentenseCase(s), noparams);
					Object value = method.invoke(object, null);
					if (value != null) {
						if (!cache.contains(value)) {
							data.put(s, value);
							cache.add(value);
						}
					}
				}
				if (data.size() > 0) {
					String json = JsonUtil.createJsonFromParamsMap(data);
					ll.add(json);
				}
			}
			return Arrays.toString(ll.toArray(new String[1]));
		} catch (IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws IOException {
		// List<String> ll = new ArrayList<>();
		// Map<String, Object> data = new LinkedHashMap<>();
		// data.put("mcap_name", "xxxx");
		// String json = JsonUtil.createJsonFromParamsMap(data);
		// ll.add(json);
		// String string = Arrays.toString(ll.toArray(new String[1]));
		// System.out.println(string);
		String jsonString = "[{\"mcap_name\":\"Large Cap: > $5Bn\"}]";
		String newJson=JsonUtil.addNodeInJsonArray(jsonString,"all","All");
		System.out.println(newJson);
	}

}
