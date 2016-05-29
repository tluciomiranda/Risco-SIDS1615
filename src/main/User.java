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
			try 
			{System.out.println("put prod");
				opers.put(t);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			opers.notify();
	
    }
	
	public void doLogin(Map <String,String> params, HttpExchange exc){
		String username = params.get("username");
		String password = Utils.encrypt(params.get("password"));
		
		System.out.println("beforee loginUser");
		boolean man = db.loginUser(username, password);
		System.out.println("After loginUser");
		if(man){
			try 
    		{
				System.out.println("RESPONDE");
    			String response = "This is the response from user class";			        
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
		else{
			System.out.println("NAO EXISTE NAO RESPONDE");
		}
		
		
		
		
	}

	@Override
	public void run()
	{
		db = new Db();
		db.startDb();
		boolean loop =true;
		
		while(loop){
			//System.out.println("while");
			
		
			HttpExchange exc = null; 

			if(!opers.isEmpty())
			{
				try {
					exc = opers.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String query = exc.getRequestURI().getQuery();
		    	System.out.println("got query   " + query);
		    	if(query != null)
		    	{
		    		Map <String,String> params = Main.http.queryToMap(query);
			        
			        switch (params.get("action"))
			        {
			        	case "login":
			        		doLogin(params,exc);
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
		}		
	}
}
