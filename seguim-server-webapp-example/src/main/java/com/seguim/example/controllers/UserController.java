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
		
		Map userMap = (Map) data.get("user");
		int id = (int) userMap.get("id");
		String name = (String) userMap.get("name");
		String surname = (String) userMap.get("surname");
		String email = (String) userMap.get("email");

		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);

		boolean result = userService.saveOrUpdate(user);

		Map<String, String> map = new HashMap<>();
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
