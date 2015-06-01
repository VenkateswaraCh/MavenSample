package com.cloudwick.venkat.maven.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;
 
public class JdbcMaven {
 
  public static void main(String[] argv) {
 
	System.out.println("MySQL JDBC Connection Testing with Maven");
 
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Update your Driver Info In Dependency");
		e.printStackTrace();
		return;
	}
 
	System.out.println("Dependecies Found");
	Connection conn = null;
	java.sql.Statement stmt = null;
 
	try {
		File propFile = new File("/home/venki/work/sql/target/classes/db.properties");
		FileInputStream fs = new FileInputStream(propFile);
		Properties db = new Properties();
		db.load(fs);
		fs.close();
		conn = DriverManager.getConnection(db.getProperty("url"),db.getProperty("user"), db.getProperty("password"));
		  
		  System.out.println("Creating Connection Statement");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * from dev";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int age = rs.getInt("age");
	         String name = rs.getString("name");
	         System.out.print("Age: " + age);
	         System.out.print("Name: " + name);
	      }
	} catch (SQLException e) {
		System.out.println("Connection Failed! Check the log");
		e.printStackTrace();
		return;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
	if (conn != null) {
		System.out.println("Successfully Connected");
	} else {
		System.out.println("Connecton Failed");
	}
  }
}