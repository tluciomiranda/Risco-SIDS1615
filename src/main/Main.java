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
		http.initServer();
		
		Room jogo = new Room("private");
	}
}
