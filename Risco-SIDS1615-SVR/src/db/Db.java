package db;
import java.io.File;
import java.sql.*;


public class Db {
	
	public Db(){
		
	};
	
	
	public void startDb(){
		System.out.println("Connecting to local database");
		File file = new File ("risk.db");

		if(file.exists()){
			System.out.println("This database name already exists");
		}
		else{
			
			System.out.println("Database doesn't exist");
			
			try {
				Connection c = null;
			    Statement stmt = null;
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:risk.db");
				
				stmt = c.createStatement();
				
				String sql = "CREATE TABLE USER " +
		                   "(ROWID INTEGER PRIMARY KEY," +
		                   " USERNAME TEXT UNIQUE NOT NULL, " + 
		                   " USERNAMELOWER TEXT UNIQUE NOT NULL, " + 
		                   " EMAIL TEXT UNIQUE NOT NULL, " + 
		                   " PASSWORD TEXT NOT NULL, " +
		                   "updatetime TEXT DEFAULT (strftime('%Y-%m-%d %H:%M:%S:%s','now', 'localtime')))"; 
				stmt.executeUpdate(sql);
			
				System.out.println("Database table created");
				
				sql = "CREATE TRIGGER update_user  BEFORE update ON USER " + 
								"begin " +
								"update USER set updatetime = strftime('%Y-%m-%d %H:%M:%S:%s','now', 'localtime') where ROWID = old.ROWID;"+
								"end";
				stmt.executeUpdate(sql);
				
				System.out.println("Database trigger created");
				
				
				stmt.close();
				c.close();
				
				
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Opened database successfully");
		}
	}
	
	//SELECT * FROM USER WHERE updatedatetime > (SELECT updatedatetime FROM USER ORDER BY updatedatetime asc limit 1);
	
	public boolean registerUser(String username, String email, String password){
		boolean value = false;
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "INSERT INTO USER (USERNAME, USERNAMELOWER,EMAIL,PASSWORD) " +
	                   "VALUES ('"+ username +"' , '" + username.toLowerCase() +"' , '"+ email.toLowerCase() + "' , '" + password +"');"; 
	      int rows = stmt.executeUpdate(sql);
	      
	      if(rows >0){
	    	  value = true;
	      }
	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return value;
	}
	
	public int loginUser(String username, String password){
		int value = 0;
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	     // System.out.println("BEFORE");
	      ResultSet rs = stmt.executeQuery( "SELECT ROWID AS CNT FROM USER WHERE USERNAMELOWER='"+username.toLowerCase()+"'"+
	    	      " AND PASSWORD='"+password+"';" );
	      
	     // System.out.println("AFTER");
	      if(rs.getInt("CNT")>0){
	    	  value = rs.getInt("CNT");
	    	  System.out.println("Login Successful");
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
		
		return value;
	}

}
