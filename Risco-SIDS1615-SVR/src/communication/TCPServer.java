package communication;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import db.Db;
import utils.Message;

public class TCPServer extends Thread{
	
	private Db db;
	private int tcpPort;
	private String localIp;
	
	public TCPServer(Db db, String localIp, int tcpPort){
		this.db = db;
		this.tcpPort = tcpPort;
		this.localIp = localIp;
	}
	
	public void run(){
		
		System.out.println(this.localIp);
		tcpHandler th= new tcpHandler(this.db, this.localIp);
		th.start();
		
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
}
