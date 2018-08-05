package com.finvendor.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author ayush
 * @since 03-Feb-2018
 */
public class JsonUtil {
	public static Object createObjectFromJson(String jsonStr) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(jsonStr, Object.class);
		return object;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object createObjectFromJson(String jsonStr, Class clazz, boolean skipUnkownProperties) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (skipUnkownProperties) {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		Object object = mapper.readValue(jsonStr, clazz);
		return object;
	}

	public static String createJsonFromObject(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(object);
	}

	public static ObjectNode getAsObjectNode(String jsonStr) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		return (ObjectNode) root;
	}

	public static String getValue(String jsonStr, String fieldName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		JsonNode node = root.findValue(fieldName);
		return node != null ? node.asText() : "";
	}
	public static JsonNode getJsonNode(String jsonStr, String nodeName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		JsonNode node = root.findValue(nodeName);
		return node;
	}

	public static String getJsonNodeString(String jsonStr, String nodeName) throws IOException {
		JsonNode node = getJsonNode(jsonStr, nodeName);
		return node != null ? node.toString() : "";
	}

	public static String removeNode(String jsonStr, String nodeName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		ObjectNode node = (ObjectNode) root;
		node.remove(nodeName);
		return node.toString();
	}

	public static String addNode(String jsonStr, String nodeName, Map<String,String> data) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		ObjectNode newNode = ((ObjectNode) root).putObject(nodeName);
		for (Map.Entry<String, String> entry : data.entrySet()) {
			newNode.put(entry.getKey(), entry.getValue());
		}
		return root.toString();
	}


	public static String addProperty(String jsonStr, String parentName, Map<String,String> data) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		ObjectNode parent = (ObjectNode) root;
		if (parentName != null) {
			parent = (ObjectNode) root.get(parentName);
		}
		for (Map.Entry<String, String> entry : data.entrySet()) {
			parent.put(entry.getKey(), entry.getValue());
		}
		return root.toString();
	}

	public static Map<String, Object> createParamsMapFromJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> paramsMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
		});

		return paramsMap;
	}

	public static String createJsonFromParamsMap(Map<String, Object> paramsMap) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = "";
		jsonStr = mapper.writeValueAsString(paramsMap);
		return jsonStr;
	}

	public static Integer getValueAsInt(String json, String paramName) {
		if (!json.equals("")) {
			if (paramName != null && !paramName.equals("")) {
				String value = null;
				try {
					value = getValue(json, paramName);
					if (!value.equals(""))
						return Integer.parseInt(value);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static List<String> getValueList(String jsonStr, String fieldName) throws IOException {
		List<String>  list = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonStr);
		JsonNode node = root.findValue(fieldName);
		Iterator<JsonNode> iter = node.elements();
		while (iter.hasNext()) {
			list.add(iter.next().asText());
		}
		return list;
	}
	
	public static Object convertJsonToPojo(String jsonStr,Class<?> classz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object readValue = mapper.readValue(jsonStr, classz);
		return readValue;
	}
	
	public static String addNodeInJsonArray(String jsonString,String key, String value) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectReader reader = mapper.reader();
		String newJson="";
		try {
			JsonNode node = reader.readTree(jsonString);
			ArrayNode arrayNode = (ArrayNode) node;
			ObjectNode counterNode = arrayNode.addObject();
			counterNode.put(key, value);
			newJson = node.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newJson;
	}
}