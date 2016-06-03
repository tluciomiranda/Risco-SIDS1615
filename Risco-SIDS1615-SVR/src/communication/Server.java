package communication;

import db.Db;
import game.UserManager;
import utils.Utils;



public class Server {
	
	public static Db db;
	
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
		 
		 db = new Db();
		 db.startDb();	  
		 
		
		 UserManager umanager = new UserManager(http);		 
		 RoomManager rmanager = new RoomManager(http);
		 http = new HttpSvr(httpPort);
		 http.initServer();
		
		
		 // atribuir contexto ao servidor HTTP
		 http.addContext("/game", rmanager);
		 http.addContext("/user", umanager);
		 
		 	 
		 TCPServer tcpServer = new TCPServer(this.db, this.ip,this.tcpPort);
		 tcpServer.addMediator(mediatorIp,mediatorPort);
		 tcpServer.setServerInfo();
		 
		 tcpServer.start();
		 
	 }
	 
}
