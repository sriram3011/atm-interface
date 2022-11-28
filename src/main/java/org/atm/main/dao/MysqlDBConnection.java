package org.atm.main.dao;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
public class MysqlDBConnection {
	 
	static final String DB_URL="jdbc:mysql://localhost:3306/";
	static final String DB_USER="root";
	static final String DB_PASSWORD="root";
	static final String DB_NAME="atmdb";
	
	public static Connection dbconnect() throws ClassNotFoundException
	{
		Connection connection=null;
		
		try 
		{		
			connection = DriverManager.getConnection(DB_URL+DB_NAME, DB_USER, DB_PASSWORD);	
		} 
		catch (SQLException e) 
		{		
			System.out.println("Connection Error!!");
		}
		
		return connection;
	
	}

}