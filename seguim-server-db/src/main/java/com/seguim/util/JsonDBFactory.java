package com.seguim.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Date: 13/02/15.
 * Time: 13:32
 */
public class JsonDBFactory {

	private static final String STORE_TYPE_MEMORY = "memory";
	private static final String STORE_TYPE_FILESYSTEM = "filesystem";

	private static JsonDB jsonDB;

	public static JsonDB getInstance() throws Exception {
		if(jsonDB==null) {

			Properties properties = new Properties();
			properties.load(JsonDBFactory.class.getResourceAsStream("/jsondb.properties"));
			String storeType = properties.getProperty("jsondb.storeType");
			if(storeType.equals(STORE_TYPE_FILESYSTEM)) {
				jsonDB = new FileSystemJsonDB(properties.getProperty("jsondb.filesystem.path"));
			} else if(storeType.equals(STORE_TYPE_MEMORY)) {
				jsonDB = new MemoryJsonDB();
			}
		}
		return jsonDB;
	}

}
