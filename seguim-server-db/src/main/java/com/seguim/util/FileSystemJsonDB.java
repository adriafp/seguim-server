package com.seguim.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 30/12/14.
 * Time: 14:59
 */
public class FileSystemJsonDB implements JsonDB {

	private SmileFactory f = new SmileFactory();
	private ObjectMapper objectMapper = new ObjectMapper(f);

	private String path;

	public FileSystemJsonDB() {

	}
	public FileSystemJsonDB(String _path) throws Exception {
		if(_path==null) {
			_path = System.getProperty("java.io.tmpdir");
			System.out.println("path = " + _path);
		}
		File path = new File(_path);
		if(!path.exists()) {
			throw new Exception("Folder: [" + getPath() + "] doesn't exists.");
		}
		setPath(_path);
	}

	public synchronized int saveOrUpdate(Object object, Class _class) {
		int result = -1;
		try {
			File folder = new File(getPath(), _class.getName());
			if(!folder.exists()) {
				if(!folder.mkdir()) {
					throw new Exception("Folder ["+ folder + "] can not be created.");
				}
			}
			int id = (Integer) object.getClass().getMethod("getId").invoke(object);
			if(id<=0) {
				id = folder.list().length + 1;
			}
			String path = _class.getName()+"/"+id+".json";
			File file = new File(getPath(), path);
			objectMapper.writeValue(file, object);
			result = id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public synchronized <T> T findBy(String key, String value, Class<T> _class) {
		try {
			String path = FileSystemJsonDB.class.getResource(".").getPath()+_class.getName()+".json";
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
			File folder = new File(getPath(), _class.getName());
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
			File folder = new File(getPath(), _class.getName());
			for(String name : folder.list() ) {
				File file = new File(folder, name);
				JsonNode jsonNode = objectMapper.readTree(file);
				JsonNode node = jsonNode.findPath(key);
				if (node.textValue().toUpperCase().contains(value.toUpperCase())) {
					list.add(objectMapper.readValue(file, _class));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
