package com.seguim.example;

import com.seguim.SeguimHttpServer;
import com.seguim.util.PerformanceManager;

/**
 * Date: 11/12/14.
 * Time: 16:12
 */
public class Main {


	public static void main(String[] args) throws Exception {
		PerformanceManager.setEnabled(true);
		SeguimHttpServer.start(8000,"com.seguim.example.controllers");
	}

}
