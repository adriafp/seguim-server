package com.seguim.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

import java.io.File;
import java.util.*;

/**
 * Date: 30/12/14.
 * Time: 14:59
 */
public class MemoryJsonDB implements JsonDB {

	private SmileFactory f = new SmileFactory();
	private ObjectMapper objectMapper = new ObjectMapper(f);

	private Map<String, Map> memory;

	String PATH ="";

	public MemoryJsonDB() throws Exception {
	    memory = new TreeMap<String, Map>();
	}

	public synchronized int saveOrUpdate(Object object, Class _class) {
		int result = -1;
		try {
			Map<Integer, byte[]> folder = memory.get(_class.getName());
			if(folder == null) {
				folder = new HashMap<Integer, byte[]>();
				memory.put(_class.getName(), folder);
			}
			int id = (Integer) object.getClass().getMethod("getId").invoke(object);
			if(id<=0) {
				id = folder.size() + 1;
			}
			folder.put(id, objectMapper.writeValueAsBytes(object));
			result = id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public synchronized <T> T findBy(String key, String value, Class<T> _class) {
		try {
			String path = MemoryJsonDB.class.getResource(".").getPath()+_class.getName()+".json";
			File file = new File(path);

			JsonNode jsonNode = objectMapper.readTree(file);
			JsonNode node = jsonNode.findPath(key);
			if(node.textValue().contains(value)) {
				return objectMapper.readValue(file, _class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized <T> T get(int id, Class<T> _class) {
		try {
			File folder = new File(PATH, _class.getName());
			File file = new File(folder, id + ".json");
			return objectMapper.readValue(file, _class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized <T> List<T> list(String key, String value, Class<T> _class) {

		List<T> list = new ArrayList<T>();
		try {
			Map<Integer, byte[]> folder = memory.get(_class.getName());
			for(byte[] json : folder.values()) {
				JsonNode jsonNode = objectMapper.readTree(json);
				JsonNode node = jsonNode.findPath(key);
				if (node.textValue().toUpperCase().contains(value.toUpperCase())) {
					list.add(objectMapper.readValue(json, _class));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
