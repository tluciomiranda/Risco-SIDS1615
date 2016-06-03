package communication;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import db.Db;
import db.ServerInfo;
import db.ServersInfo;
import protocols.StartupProtocol;
import utils.*;

public class TCPServer extends Thread{
	
	private Db db;
	private ServerInfo si;
	private ServersInfo svsi;
	private int tcpPort;
	private String localIp;
	private String mediatorIp;
	private int mediatorTcpPort;
	private boolean isMediator = false;
	
	public TCPServer(Db db, String localIp, int tcpPort){
		this.db = db;
		this.tcpPort = tcpPort;
		this.localIp = localIp;
	}
	
	public void run(){
		
		System.out.println(this.localIp);
		tcpHandler th= new tcpHandler(this.db, this.localIp,this.si);
		th.start();
		
		if(!this.isMediator){
			StartupProtocol sp = new StartupProtocol(this.db, this.si);
			sp.start();
		}
		
		try {  
		    ServerSocket ss = new ServerSocket(this.tcpPort); 
		    Socket s; 
		    java.io.InputStream is;
		    ObjectInputStream ois;
		    
		    boolean running = true;
		    while(running){
		    	s = ss.accept();
		    	is = s.getInputStream();
			    ois = new ObjectInputStream(is);
			    Message to = (Message)ois.readObject();
			    
			    /*String ipsender = s.getRemoteSocketAddress().toString();
			    ipsender = ipsender.replace("/", "").split(":")[0];
			    to.addSenderIp(ipsender);*/
			    
			    th.addMessage(to);
			    
			    ois.close();
			    s.close();  
			    
		    } 
		    
		    ss.close();  
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();}  
		}
	
	public void addMediator(String mediatorIp, int mediatorPort){
		 this.mediatorIp = mediatorIp;
		 this.mediatorTcpPort = mediatorPort;
		 
		 if(mediatorIp.equals(this.localIp)){
			 this.isMediator = true;
			 Utils.So("I am the boss");
		 } 
	 }
	
	public void setServerInfo(){
		this.si = new ServerInfo(this.localIp, this.tcpPort,this.mediatorIp,this.mediatorTcpPort);
		this.svsi = new ServersInfo();
	}
}
