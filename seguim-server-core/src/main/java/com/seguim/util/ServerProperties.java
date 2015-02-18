package com.seguim.util;

import java.util.Properties;

/**
 * Date: 12/02/15.
 * Time: 16:23
 */
public class ServerProperties {
	private static final Properties props = new Properties();

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static void load(String file) {
		try {
			props.load(ServerProperties.class.getResourceAsStream("/"+file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
