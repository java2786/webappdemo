package com.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NamesDatabase {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// load driver
		List<String> names = findDbNames();
		System.out.println(names);
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/cu";
		String user = "root";
		String password = "root";
		con = DriverManager.getConnection(url,user,password);
		if(con!=null) {
			System.out.println("Connection established...");
		}
		return con;
	}
	
	public static List<String> findDbNames() throws ClassNotFoundException, SQLException{
		List<String> names = new ArrayList<String>();
		Connection con = getConnection();
		String sql = "select * from student";
		PreparedStatement psmt = con.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		while(rs.next()) {
			names.add(rs.getString("name"));
		}
		con.close();
		return names;
	}
}
