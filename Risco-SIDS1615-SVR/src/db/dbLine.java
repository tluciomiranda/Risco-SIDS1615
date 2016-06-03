package db;

import java.io.Serializable;

public class dbLine implements Serializable{
	
	private static final long serialVersionUID = 2L;
	private int rowid;
	private String username;
	private String username2;
	private String email;
	private String password;
	private String updatetime;
	
	public dbLine(int row, String u, String u2, String e, String p, String t){
		this.rowid = row;
		this.username = u;
		this.username2 = u2;
		this.email = e;
		this.password = p;
		this.updatetime = t;
	}
	
	public int getRow(){
		return rowid;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getUsername2(){
		return this.username2;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getUpdateTime(){
		return this.updatetime;
	}

}
