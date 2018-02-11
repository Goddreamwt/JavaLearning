package com.imooc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBUtil {

	// imooc为数据库名称
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/imooc";
	private static final String NAME = "root";
	private static final String PASSWORD = "root";

	private static  Connection conn =null;
	
	static {
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");

			// 2.获得数据库的连接
		    conn = DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return conn;
	}
		
}
