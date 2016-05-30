package main;

import communication.*;
import db.Db;
import utils.Utils;

public class Main
{
	public static HttpSvr http;
	public static Db db;
	
	public static void main(String[] args) 
	{
		RoomManager manager = new RoomManager(); 
		User userThread = new User();
		
		http = new HttpSvr(8083);		
		http.initServer();
		
		db = new Db();
		db.startDb();
		
		// atribuir contexto ao servidor HTTP
		http.addContext("/game", manager);
		http.addContext("/user", userThread);
		
	}

}
