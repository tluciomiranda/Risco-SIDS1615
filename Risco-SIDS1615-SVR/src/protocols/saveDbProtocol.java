package protocols;

import java.util.ArrayList;

import db.*;
import utils.*;

public class saveDbProtocol extends Thread{
	
	private Message message;
	private Db db;
	
	public saveDbProtocol(Message m, Db db){
		this.db = db;
		this.message = m;
	}
	
	public void run(){
		
		ArrayList<dbLine> dbData = message.getRs();
		
		for(int i = 0; i < dbData.size();++i){
			
			dbLine dbl = dbData.get(i);
			 int rowid = dbl.getRow();
			 String username = dbl.getUsername();
			 String username2 = dbl.getUsername2();
			 String email = dbl.getEmail();
			 String password = dbl.getPassword();
			 String updatetime = dbl.getUpdateTime();
			 
			 if(db.exists(username)){
				 db.updateRecord(username, username2, email, password, updatetime);
			 }
			 else{
				 db.createRecord(username, username2, email, password, updatetime);
			 }
			
		}
		
		
		
		
	}

}
