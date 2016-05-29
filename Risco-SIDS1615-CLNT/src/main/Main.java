package main;

import java.io.IOException;

import communication.*;

public class Main {

	public static void main(String[] args) 
	{
		HttpClnt cl = new HttpClnt();
		try 
		{
			cl.httpReq();
		}
		catch (IOException e)
		{
			
		}
	}
}
