package main;

import communication.*;

public class Main {

	public static void main(String[] args) 
	{
		HttpSvr http = new HttpSvr(80);
		
		http.initServer();
		Room jogo = new Room();
		
		http.addContext("1", jogo);
	}
}
