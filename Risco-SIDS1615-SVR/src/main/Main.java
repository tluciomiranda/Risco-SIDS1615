package main;

import communication.*;
import db.Db;
import utils.Utils;

public class Main
{
	public static HttpSvr http;

	public static void main(String[] args) 
	{	
		//if(args[0])
		//inicia server HTTP
		http = new HttpSvr(8083);
		
		User userThread = new User();
		Room jogo = new Room("private");
		
		Db db = new Db();
		db.startDb();
		// atribuir contexto ao servidor HTTP
		http.addContext("/jogo/" + Long.toString(jogo.getID()), jogo);
		http.addContext("/user", userThread);
		
		http.initServer();
		userThread.start();
	}

}
