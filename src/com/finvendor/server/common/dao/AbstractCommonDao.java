package com.finvendor.server.common.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.finvendor.common.util.JsonUtil;

public abstract class AbstractCommonDao implements ICommonDao {

	@Autowired
	protected SessionFactory sessionFactory;

	public AbstractCommonDao() {
	}

	/**
	 * Generic Code- Donot change as it is sensitive
	 */
	@SuppressWarnings("unchecked")
	public String runSql(String sql, Map<String, Map<String, String>> columnNameMap, Object[] conditionValue,
			Map<String, Object> firstDefaultParamsMap, Map<String, Object> lastDefaultParamsMap, int colIndex)
					throws RuntimeException {
		try {
			SQLQuery query = getNativeQuery(sql, conditionValue);
			List<Object[]> rows = query.list();

			List<Map<String, Object>> listOfMap = new ArrayList<>();
			if (firstDefaultParamsMap != null) {
				listOfMap.add(firstDefaultParamsMap);
			}

			for (Object[] row : rows) {
				int index = colIndex;
				Map<String, Object> paramsMap = new LinkedHashMap<>();
				for (Entry<String, Map<String, String>> entry : columnNameMap.entrySet()) {
					String columnName = entry.getKey();
					Map<String, String> value = entry.getValue();
					String columnValue = row[index] != null ? row[index].toString() : "";
					String newValue = "";
					if (value != null) {
						newValue = value.get(columnValue);
						if (newValue == null) {
							paramsMap.put(columnName, columnValue);
						} else {
							paramsMap.put(columnName, newValue);
						}
					} else {
						paramsMap.put(columnName, columnValue);
					}
					index++;
				}
				listOfMap.add(paramsMap);
			}

			if (lastDefaultParamsMap != null) {
				listOfMap.add(lastDefaultParamsMap);
			}
			return JsonUtil.createJsonFromObject(listOfMap);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public SQLQuery getNativeQuery(String sql, Object[] conditionValue) {
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (conditionValue != null) {
			for (int i = 0; i < conditionValue.length; i++) {
				Object object = conditionValue[i];
				if (object instanceof String) {
					String stringTypeValue = (String) object;
					query.setString(i, stringTypeValue);
				} else {
					Integer integerTypeValue = (Integer) object;
					query.setInteger(i, integerTypeValue);
				}
			}
		}
		return query;
	}
}
