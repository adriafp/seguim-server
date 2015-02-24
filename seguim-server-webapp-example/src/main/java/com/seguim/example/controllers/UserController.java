package com.seguim.example.controllers;

import com.seguim.Controller;
import com.seguim.controllers.AbstractController;
import com.seguim.example.domain.User;
import com.seguim.example.services.UserService;
import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 12/12/14.
 * Time: 14:21
 */
@Controller(value = "/user", methods = { "POST", "GET" })
public class UserController extends AbstractController {

	UserService userService = new UserService();

	@Override
	public Map post(Map data, HttpExchange t) {

		Map<String, Object> map = new HashMap<>();
		boolean result = false;
		
		try {
			int id = (int) (data.get("id") == null ? 0 : data.get("id"));
			String name = (String) data.get("name");
			String surname = (String) data.get("surname");
			String email = (String) data.get("email");
			if(name==null || surname == null || email ==null ) {
				throw new Exception("Name, surname or email can not be null.");
			}
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setSurname(surname);

			user.setEmail(email);
			id = userService.saveOrUpdate(user);
			user.setId(id);
			map.put("user", user);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e.getMessage());
		}
		
		map.put("success", Boolean.toString(result));
		return map;
	}

	@Override
	public Map get(Map data, HttpExchange t) {

		int id = Integer.parseInt((String) data.get("uriId"));
		User user = userService.get(id);
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		return map;
	}
}
