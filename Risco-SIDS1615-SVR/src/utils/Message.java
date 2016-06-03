package utils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.ServerInfo;
import db.dbLine;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int value;
	public String id;
	public String header;
	public String lastDB;
	public ServerInfo si;
	
	public ArrayList<dbLine> rs = new ArrayList<dbLine>();
	public ArrayList<ServerInfo> serversArray = new ArrayList<ServerInfo>();
	String senderIp;
	String receiverIp;
	int senderTcpPort;
	int receiverTcpPort;
	
	public Message(int v, String s){
		this.value = v;
		this.id = s;
		this.senderIp = "NULL";
		
	}
	
	public Message(String s){
		this.header = s;
		
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
	
	public void addLastDb(String bd){
		this.lastDB = bd;
	}
	
	public String getLastdb(){
		return this.lastDB;
	}
	
	public String getHeader(){
		return this.header;
	}
	
	public void addServerInfo(ServerInfo si){
		this.si = si;
	}
	
	public ServerInfo getServerInfo(){
		return this.si;
	}
	
	public void addServersInfo(ArrayList<ServerInfo> svsi){
		this.serversArray = svsi;
	}
	
	public ArrayList<ServerInfo> getServersInfo(){
		return this.serversArray;
	}
	
	public void addReceiverTcpPort(int tcp){
		this.receiverTcpPort = tcp;
	}
	
	public int getReceiverTcpPort(){
		return this.receiverTcpPort;
	}
	
	public void addSenderTcpPort(int tcp){
		this.senderTcpPort = tcp;
	}
	
	public int getSenderTcpPort(){
		return this.senderTcpPort;
	}
	
	public void setComunication(String senderIp, int senderTcp, String receiverIp, int receiverTcp){
		this.senderIp = senderIp;
		this.senderTcpPort = senderTcp;
		this.receiverIp = receiverIp;
		this.receiverTcpPort = receiverTcp;
	}
	
	
	
	
}