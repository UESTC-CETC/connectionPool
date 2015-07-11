package com.cetc.pooltext;

import java.util.ResourceBundle;

public class ResourceManager {
	private static ResourceBundle rb;
	static{
		rb = ResourceBundle.getBundle("resource");
	}
	public static String getUserName(){
		return rb.getString("connect.username");
	}
	public static String getPassword(){
		return rb.getString("connect.password");
	}
	public static String getUrl(){
		return rb.getString("connect.url");
	}
	public static String getSize(){
		return rb.getString("connect.poolSize");
	}
	public static String getDriverClass(){		
		return rb.getString("connect.driverClass");
	}
}
