package db;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;



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
	      

	      stmt = c.createStatement();
	      
	      ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) AS CNT FROM USER WHERE USERNAMELOWER='"+username.toLowerCase()+"'"+
	    	      " AND PASSWORD='"+password+"';" );
	      
	      
	      if(rs.getInt("CNT")>0){
	    	  value = 1;
	    	  System.out.println("Login Successful");
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
		
		return value;
	}
	
	public String getLastRecordDate(){
		
		String result = "null";
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
	      c.setAutoCommit(false);
	      

	      stmt = c.createStatement();
	   
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM USER ORDER BY updatetime desc limit 1;" );
	      while(rs.next()){
	    	  result = rs.getString("updatetime");  
	      }
	      
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		
		return result;
	}
	
public ArrayList<dbLine> getRecordsByDate(String date){
	
	ArrayList<dbLine> result = new ArrayList<dbLine>();
		
	Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
      c.setAutoCommit(false);
      

      stmt = c.createStatement();
   
      ResultSet rs = stmt.executeQuery( "SELECT * FROM USER WHERE updatetime > '"+date+"';" );
      int i = 0;
      while(rs.next()){
    	  int rowid = rs.getInt("ROWID");
    	  String user = rs.getString("USERNAME");
    	  String user2 = rs.getString("USERNAMELOWER");
    	  String email = rs.getString("EMAIL");
    	  String password = rs.getString("Password");
    	  String updatetime = rs.getString("updatetime");
    	  
    	  
    	  
    	  dbLine dbl = new dbLine(rowid,user,user2,email,password,updatetime);
    	  result.add(dbl);
    	  ++i;
      }
      
      
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
		
		return result;
	}
	
	
	public boolean exists(String username){
		boolean value = false;
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	   
	      ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) AS CNT FROM USER WHERE USERNAMELOWER='"+
	      username.toLowerCase()+"';" );
	      
	      if(rs.getInt("CNT")>0){
	    	  value = true;
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
		return value;
	}
	
	
	public boolean updateRecord(String u, String u2, String e, String p, String time){
		boolean value = false;
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	   
	      stmt.executeUpdate( "UPDATE USER SET USERNAME= '"+u+"', USERNAMELOWER='"+u2+"',EMAIL='"+
	      e+"', PASSWORD='"+p+"', updatetime='"+time+"' WHERE USERNAME ='"+u+"';");
	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	      return true;
	    } catch ( Exception ex ) {
	      System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
	      System.exit(0);
	    }
		return value;
	}
	
	public boolean createRecord(String u, String u2, String e, String p, String time){
		boolean value = false;
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:risk.db");
	      c.setAutoCommit(false);

	      stmt = c.createStatement();
	   
	      stmt.executeUpdate( "INSERT INTO USER (USERNAME, USERNAMELOWER,EMAIL,PASSWORD,updatetime) values ('"+
	      u+"','"+u2+"','"+e+"','"+p+"','"+time+"');");
	      
	      
	      
	      stmt.close();
	      c.commit();
	      c.close();
	      return true;
	      
	    } catch ( Exception ex ) {
	      System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
	      ex.printStackTrace();
	      System.exit(0);
	    }
		return value;
	}

}
