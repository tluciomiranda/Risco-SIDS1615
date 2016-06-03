package communication;

import db.Db;
import utils.Utils;



public class Server {
	
	private Db db;
	
	private String ip;
	private int tcpPort;
	private int httpPort;
	private String mediatorIp;
	private int mediatorTcpPort;
	boolean isMediator = false;
	
	public static HttpSvr http;
	
	public Server(String ip,int tcpPort, int httpPort){

		this.tcpPort = tcpPort;
		this.ip = ip;
		this.httpPort = httpPort;
	}
		
	 public void go(String mediatorIp,int mediatorPort) { 
		 System.out.println("Starting Server");
		 
		 this.db = new Db();
		 db.startDb();
		  
		 
		
		 this.http = new HttpSvr(this.httpPort);		
		 RoomManager manager = new RoomManager(this.http);
		 http.initServer();
		
		
		 // atribuir contexto ao servidor HTTP
		 http.addContext("/game", manager);
		 
		 
		 	 
		 TCPServer tcpServer = new TCPServer(this.db, this.ip,this.tcpPort);
		 tcpServer.addMediator(mediatorIp,mediatorPort);
		 tcpServer.setServerInfo();
		 
		 tcpServer.start();
		 
	 }
	 
}
