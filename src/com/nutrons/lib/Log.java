package com.nutrons.lib;

/**
 * 
 * @author Camilo Gonzalez
 *
 */
public class Log {
	
	/**
	 * logs debug event
	 * @param TAG name of object being logged
	 * @param data data of object being logged
	 */
	public static void d(String TAG, String data) {
		System.out.println("<DEBUG: " + TAG + " : " + data + " >");
	}
	
	/**
	 * logs error event
	 * @param TAG name of object being logged
	 * @param data data of object being logged
	 */
	public static void e(String TAG, String data) {
		System.out.println("<ERROR!!: " + TAG + " : " + data + " >");
	}
	
	/**
	 * logs info event
	 * @param TAG name of object being logged
	 * @param data data of object being logged
	 */
	public static void i(String TAG, String data) {
		System.out.println("<INFO: " + TAG + " : " + data + " >");
	}
	
	/**
	 * logs verbose. Good for general printing
	 * @param TAG name of object being logged
	 * @param data data of object being logged
	 */
	public static void v(String TAG, String data) {
		System.out.println("< " + TAG + " : " + data + " >");
	}
}
