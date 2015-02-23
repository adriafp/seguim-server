package com.seguim.controllers;

import com.seguim.Constants;
import com.seguim.util.IOUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Arrays;

/**
 * Date: 12/02/15.
 * Time: 11:00
 */
public class MethodAwareController extends Constants {

	private String[] methods;

	public String[] getMethods() {
		return methods;
	}

	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	/**
	 * Method to validate if the current request method is one of the defined in the Controller annotation
	 * @param t HttpExchange
	 * @return true | false
	 */
	protected boolean isMethodValid(HttpExchange t) {
		if (!Arrays.asList(getMethods()).contains(t.getRequestMethod())) {
			requestMethodNotImplemented(t);
			return false;
		}
		return true;
	}

	/**
	 * Write in the request output stream an HTTP error saying that the request method is not implemented by this controller.
	 *
	 * @param t HttpExchange
	 */
	protected void requestMethodNotImplemented(HttpExchange t) {
		try {
			t.sendResponseHeaders(HTTP_CODE_NOT_IMPLEMENTED, HTTP_CODE_NOT_IMPLEMENTED_RESPONSE.length());
			IOUtils.write(t.getResponseBody(), HTTP_CODE_NOT_IMPLEMENTED_RESPONSE);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
}
