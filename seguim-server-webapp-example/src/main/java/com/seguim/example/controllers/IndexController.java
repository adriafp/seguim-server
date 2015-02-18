package com.seguim.example.controllers;

import com.seguim.example.Controller;
import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 12/12/14.
 * Time: 14:21
 */
@Controller("/index")
public class IndexController extends AbstractController {

	@Override
	public Map get(Map data, HttpExchange t) {
		System.out.println("Called /index with GET method.");
		System.out.println(" data = " + data);
		Map<String, String> map = new HashMap<String, String>();
		map.put("Hello", "World!");
		return map;
	}
}
