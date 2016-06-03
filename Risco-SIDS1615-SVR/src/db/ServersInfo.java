package db;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServersInfo {
	private ArrayList<ServerInfo> servers = new ArrayList<ServerInfo>();
	private Date lastUpdate;
	private DateFormat dateFormat;
	
	public ServersInfo(){
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		lastUpdate = new Date();
	}
	
	public ArrayList<ServerInfo> getServers(){
		return this.servers;
	}
	
	public void addServerInfo(ServerInfo si){
		servers.add(si);
	}
	
	public void replaceServersInfo(ArrayList<ServerInfo> svsi){
		this.servers = svsi;
	}
	
	
}
