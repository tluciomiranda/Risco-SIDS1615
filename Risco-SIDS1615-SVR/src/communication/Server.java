package communication;

import db.Db;
import game.RoomManager;



public class Server {
	
	private Db db;
	private String ip;
	private int tcpPort;
	public static HttpSvr http;
	
	public Server(String ip,int tcpPort){

		this.tcpPort = tcpPort;
		this.ip = ip;
	}
		
	 public void go() { 
		 System.out.println("Starting Server");
		 
		 this.db = new Db();
		 db.startDb();
		  
		 
		
		 this.http = new HttpSvr(8083);		
		 RoomManager manager = new RoomManager(http);
		 http.initServer();
		
		
		 // atribuir contexto ao servidor HTTP
		 http.addContext("/game", manager);
		 
		 
		 	 
		 TCPServer tcpServer = new TCPServer(this.db, this.ip,this.tcpPort);
		 tcpServer.start();
		 
	 }
}
