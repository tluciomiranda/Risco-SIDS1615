package utils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.dbLine;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int value;
	public String id;
	public ArrayList<dbLine> rs = new ArrayList<dbLine>();
	String senderIp;
	String receiverIp;
	
	public Message(int v, String s){
		this.value = v;
		this.id = s;
		this.senderIp = "NULL";
		
	}
	
	public ArrayList<dbLine> getRs(){
		return this.rs;
	}
	
	public void addDb(ArrayList<dbLine> dbl){
		for(int i = 0; i < dbl.size();++i){
			rs.add(dbl.get(i));
		}
	}
	
	public void addSenderIp(String Ip){
		this.senderIp = Ip;
	}
	
	public String getSenderIp(){
		return this.senderIp;
	}
	
	public void addReceiverIp(String Ip){
		this.receiverIp = Ip;
	}
	
	public String getReceiverIp(){
		return this.receiverIp;
	}
	
	
}