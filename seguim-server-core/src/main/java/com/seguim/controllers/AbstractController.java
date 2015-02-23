package com.seguim.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seguim.Controller;
import com.seguim.util.IOUtils;
import com.seguim.util.PerformanceManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 17/12/14.
 * Time: 12:53
 */
public abstract class AbstractController extends MethodAwareController implements HttpHandler {

	private static final String REQUEST = "Request";

	private String method;
	private String subcontext;

	//Jackson library to convert json to object and objects to json.
	private final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * This method handle the request of the controller and:
	 * - checks if method is valid
	 * - Read the input stream (JSON) and convert it to a Map
	 * - call the service method of the controller
	 * - gets the Map result of the service method and convert it to json
	 * - write the json in the output stream
	 *
	 * @param t HttpExchange
	 * @throws IOException
	 */
	public void handle(HttpExchange t) throws IOException {
		PerformanceManager.start(REQUEST);

		//Controller set what methods implements if requestMethod is not one of them an http error will be response it.
		if (isMethodValid(t)) {

			String json = IOUtils.read(t.getRequestBody());

			Map requestBodyMap = null;

			if (json != null && !json.equals("")) {
				requestBodyMap = objectMapper.readValue(json, Map.class);
			}

			Map responseBodyMap;

			String uriId = t.getRequestURI().getPath().substring(subcontext.length() + 1);
			if(requestBodyMap == null) {
				requestBodyMap = new HashMap();
			}

			requestBodyMap.put("uriId",uriId);

			switch (t.getRequestMethod()) {
			case "POST":
				responseBodyMap = post(requestBodyMap, t);
				break;
			case "PUT":
				responseBodyMap = put(requestBodyMap, t);
				break;
			case "DELETE":
				responseBodyMap = delete(requestBodyMap, t);
				break;
			case "GET":
			default:
				responseBodyMap = get(requestBodyMap, t);
			}

			StringWriter stringWriter = new StringWriter();
			objectMapper.writeValue(stringWriter, responseBodyMap);

			String response = stringWriter.toString();
			t.sendResponseHeaders(HTTP_CODE_OK, response.length());

			IOUtils.write(t.getResponseBody(), response);
		}

		PerformanceManager.stop(REQUEST);
	}

	/**
	 * This method is meant to be overridden.
	 * @param data
	 * @param t
	 * @return
	 */
	public Map get(Map data, HttpExchange t) {
		requestMethodNotImplemented(t);
		return null;
	}

	/**
	 * This method is meant to be overridden.
	 * @param data
	 * @param t
	 * @return
	 */
	public Map post(Map data, HttpExchange t) {
		requestMethodNotImplemented(t);
		return null;
	}

	/**
	 * This method is meant to be overridden.
	 * @param data
	 * @param t
	 * @return
	 */
	public Map put(Map data, HttpExchange t) {
		requestMethodNotImplemented(t);
		return null;
	}

	/**
	 * This method is meant to be overridden.
	 * @param data
	 * @param t
	 * @return
	 */
	public Map delete(Map data, HttpExchange t) {
		requestMethodNotImplemented(t);
		return null;
	}

	/**
	 *
	 * @return the current request method.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 *
	 * @param method the current request method.
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	public String getSubcontext() {
		return subcontext;
	}

	public void setSubcontext(String subcontext) {
		this.subcontext = subcontext;
	}
}
