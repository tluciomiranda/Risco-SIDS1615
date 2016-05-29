package main;

import java.util.*;

import communication.*;

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
		
		// atribuir contexto ao servidor HTTP
		http.addContext("/jogo/" + Long.toString(jogo.getID()), jogo);
		http.addContext("/user/", userThread);
		
		http.initServer();
		userThread.start();
	}

}
