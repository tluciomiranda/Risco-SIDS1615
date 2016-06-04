package main;

import communication.*;
import interfaces.Interfaces;

public class Main {
	
	public static HttpClnt cl;
	public static int userID = 12654;

	public static void main(String[] args) 
	{
		Interfaces intf = new Interfaces();
		
		cl = new HttpClnt();
		
		//cl.addInterface(intf);
		
		intf.controlaInterfaces();
	}
}