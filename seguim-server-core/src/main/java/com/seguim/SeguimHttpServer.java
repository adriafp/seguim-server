package com.seguim;

import com.seguim.controllers.AbstractController;
import com.seguim.util.AnnotationManager;
import com.seguim.util.PerformanceManager;
import com.seguim.util.ServerProperties;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Date: 11/02/15.
 * Time: 18:13
 */
public class SeguimHttpServer {

	private final static String SERVER_STARTED = "Server started";

	/**
	 * This method starts an instance of an HTTP SERVER of java 1.7 and adds all classes with Controller annotation that finds
	 * in the package "packageToScanControllers" param as contexts in the HttpServer.
	 *
	 * @param port Port where the server will listen for new connections.
	 * @param packageToScanControllers Package where the server will scan for classes with Controller annotation
	 * @throws Exception
	 */
	public static void start(int port, String packageToScanControllers) throws Exception {
		PerformanceManager.start(SERVER_STARTED);

		ServerProperties.load("httpserver.properties");

		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		List<Class> classList = AnnotationManager.getClassesWithAnnotation(packageToScanControllers, Controller.class);

		for(Class c : classList) {
			Controller controller = (Controller) c.getAnnotation(Controller.class);
			String mapping = controller.value();
			AbstractController abstractController = (AbstractController) c.newInstance();
			abstractController.setMethods(controller.methods());
			abstractController.setSubcontext(mapping);
			server.createContext(mapping, abstractController);
		}

		server.setExecutor(null); // creates a default executor
		server.start();
		PerformanceManager.stop(SERVER_STARTED);
	}
}
