package main;

import java.util.Random;

import communication.*;

public class Main 
{
	public static HttpSvr http;
	public static Random randomGenerator = new Random();

	public static void main(String[] args) 
	{
		//if(args[0])
		//inicia server HTTP
		http = new HttpSvr(80);
		http.initServer();
		
		Room jogo = new Room("private");
	}
}
