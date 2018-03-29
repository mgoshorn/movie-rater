package com.revature.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static ConnectionUtil connectionUtil = new ConnectionUtil();
	private Properties properties;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ConnectionUtil() {
		InputStream is = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
		properties = new Properties();
		try {
			properties.load(is);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}
	
	public Connection getConnection() throws SQLException {
	
		return DriverManager.getConnection(
				properties.getProperty("url"), 
				properties.getProperty("user"), 
				properties.getProperty("password"));
	}
	
	
}
