package com.seguim.util;

import java.io.*;

/**
 * Date: 12/02/15.
 * Time: 15:08
 */
public class IOUtils {

	/**
	 * Reads from an inputStream and returns it as String.
	 * @param inputStream InputStream
	 * @return String read from the given input stream.
	 */
	public static String read(InputStream inputStream) {
		String json = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = in.readLine()) != null) {
				if(json==null) {
					json = "";
				}
				json += line;
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		return json;
	}

	/**
	 * Write the given text to the outputStream.
	 * @param outputStream OutputStream
	 * @param text Text to write in the output stream.
	 */
	public static void write(OutputStream outputStream, String text) {
		try {
			outputStream.write(text.getBytes());
			outputStream.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

}
