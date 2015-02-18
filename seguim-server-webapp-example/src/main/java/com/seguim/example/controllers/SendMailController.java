package com.seguim.example.controllers;

import com.seguim.example.Controller;
import com.seguim.util.SendMailSSL;
import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 12/12/14.
 * Time: 14:21
 */
@Controller("/sendMail")
public class SendMailController extends AbstractController {

	@Override
	public Map get(Map data, HttpExchange t) {
		System.out.println(" data = " + data);
		SendMailSSL.sendGMail("from@gmail.com","to@mail.com","subject","text");
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "value");
		return map;
	}

}
