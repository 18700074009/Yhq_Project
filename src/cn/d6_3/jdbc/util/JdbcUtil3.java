package cn.d6_3.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil3 {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static{
		try {
			Properties pro = new Properties();
			InputStream inputStream = JdbcUtil3.class.getResourceAsStream("/jdbc.properties");
			pro.load(inputStream);
			driver = pro.getProperty("mysql.driver");
			url = pro.getProperty("mysql.url");
			user = pro.getProperty("mysql.user");
			password = pro.getProperty("mysql.password");
			//System.out.println(driver);
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		//Class.forName();
		//String url = "jdbc:mysql://localhost:3306/db1707";
		Connection conn = null;
		try {
			 conn =DriverManager.getConnection(url,user,password);
			 return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return conn;
	}
	
	public static void close(Connection conn, Statement state){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn,Statement state,ResultSet resultSet){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
