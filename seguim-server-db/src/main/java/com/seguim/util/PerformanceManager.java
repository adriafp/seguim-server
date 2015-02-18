package com.seguim.util;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class prints the time in milliseconds between start(String name) and stop(String name) methods.
 * <p/>
 * Date: 30/12/14.
 * Time: 14:29
 */
public class PerformanceManager {

	private static boolean enabled = false;

	private static PrintStream out;

	private static Map<String, Long> timers;

	private static void init() {
		if (timers == null) {
			timers = new HashMap<String, Long>();
		}
		if (out == null) {
			out = System.out;
		}
	}

	public static void start(String name) {
		if (enabled) {
			init();
			timers.put(name, System.currentTimeMillis());
		}
	}

	public static void stop(String name) {
		if (enabled) {
			init();
			String result = "[" + name + " : " + (System.currentTimeMillis() - timers.get(name)) + " ms]";
			timers.remove(name);
			out.println(result);
		}
	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static void setEnabled(boolean enabled) {
		PerformanceManager.enabled = enabled;
	}

	public static PrintStream getOut() {
		return out;
	}

	public static void setOut(PrintStream out) {
		PerformanceManager.out = out;
	}
}
