package com.seguim.util;

import java.util.List;

/**
 * Date: 30/12/14.
 * Time: 14:59
 */
public interface JsonDB {

	public int saveOrUpdate(Object object, Class _class);

	public <T> T findBy(String key, String value, Class<T> _class);

	public <T> T get(int id, Class<T> _class);

	public <T> List<T> list(String key, String value, Class<T> _class);

}
