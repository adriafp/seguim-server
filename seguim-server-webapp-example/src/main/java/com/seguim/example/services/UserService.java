package com.seguim.example.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.seguim.example.domain.User;
import com.seguim.util.JsonDB;
import com.seguim.util.JsonDBFactory;

/**
 * Date: 13/02/15.
 * Time: 13:43
 */
public class UserService {

	JsonDB jsonDB;

	public UserService() {
		try {
			jsonDB = JsonDBFactory.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveOrUpdate(User user) {
		return jsonDB.saveOrUpdate(user, User.class);
	}

	public User get(int id) {
		return jsonDB.get(id, User.class);
	}
}
