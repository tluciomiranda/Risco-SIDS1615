package main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import db.*;
import utils.Utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.net.httpserver.*;

public class User extends Thread implements HttpHandler
{
	private volatile LinkedBlockingQueue<HttpExchange> opers = new LinkedBlockingQueue<HttpExchange>();
	public Db db;
	
	public void handle(HttpExchange t) throws IOException 
    {
		String query = t.getRequestURI().getQuery();
		System.out.println("got query   " + query);
    	if(query != null)
    	{
    		Map <String,String> params = Main.http.queryToMap(query);
	        
	        switch (params.get("action"))
	        {
	        	case "login":
	        		doLogin(params, t);
	        		//loop = false;
	        		break;
	        	case "registar":
	        		
	        		break;
	        	case "logout":
	        		
	        		break;
	        }
	    }
    	else  // resposta feia
    	{	
    		try 
    		{
	            String response = "Invalid request.";
	            
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
    		}
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
		}
    }
	
	public void doLogin(Map <String, String> params, HttpExchange exc)
	{
		db = new Db();
		db.startDb();
		
		String username = params.get("username");
		String password = Utils.encrypt(params.get("password"));
		
		System.out.println("beforee loginUser");
		boolean man = db.loginUser(username, password);
		System.out.println("After loginUser");
		if(man)		
		{
			try 
    		{
				System.out.println("RESPONDE OK");
    			String response = "OK";
    			
				exc.sendResponseHeaders(200, response.length());						
		        OutputStream os = exc.getResponseBody();
		        os.write(response.getBytes());
		        os.close();
		        
	        }
    		catch (IOException e)
    		{
				e.printStackTrace();
			}			
		}
		else
		{
			try 
    		{
				System.out.println("RESPONDE NOK");
    			String response = "NOK";
    			
				exc.sendResponseHeaders(200, response.length());
		        OutputStream os = exc.getResponseBody();
		        os.write(response.getBytes());
		        os.close();
	        }
    		catch (IOException e)
    		{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run()
	{
		boolean loop =true;
		/*
		//while(loop){
			//System.out.println("while");
			
		
			HttpExchange exc = null; 

			if(!opers.isEmpty())
			{
				try {System.out.println("try exc");
					exc = opers.take(); //System.out.println(exc.);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}		*/
		
		
		
		
		
		
		
	}
}
