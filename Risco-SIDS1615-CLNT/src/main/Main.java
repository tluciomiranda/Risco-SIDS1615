package main;

import communication.*;
import interfaces.Interfaces;

public class Main {
	
	public static HttpClnt cl;
	public static int userID = 12654;

	public static void main(String[] args) 
	{
		if(args.length != 2)
		{
			System.out.println("Usage: <mediator ip> <mediator TCPPort>");
			return;
		}
		 
		Interfaces intf = new Interfaces();	
		
		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		
		cl = new HttpClnt(ip, port);
		
		intf.controlaInterfaces();
	}
}