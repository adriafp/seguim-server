package com.seguim;

/**
 * Date: 11/12/14.
 * Time: 16:12
 */
public class Main {

	public static void main(String[] args) throws Exception {
		SeguimHttpServer.start(8000,"com.seguim.controllers");
	}

}
