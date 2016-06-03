package main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.net.httpserver.*;

public class User extends Thread implements HttpHandler
{
	private volatile LinkedBlockingQueue<HttpExchange> opers = new LinkedBlockingQueue<HttpExchange>();
		
	public void handle(HttpExchange t) throws IOException 
    {
		String query = t.getRequestURI().getQuery();

    	if(query != null)
    	{
    		Map <String,String> params = Main.http.queryToMap(query);
	        
	        switch (params.get("action"))
	        {
	        	case "login":
	        		doLogin(params, t);
	        		break;
	        	case "register":
	        		doRegister(params, t);
	        		break;
	        	case "logout":
	        		doLogout(params, t);
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
	
	private void doLogin(Map <String, String> params, HttpExchange exc)
	{
		String username = params.get("username");
		String password = params.get("password");

		int userid = Main.db.loginUser(username, password);
		
		if(userid > 0)		
		{
			try 
    		{
				System.out.println("RESPONDE OK");
    			String response = "" + userid;
    			
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
	
	private void doRegister(Map <String, String> params, HttpExchange exc)
	{
		String username = params.get("username");
		String password = params.get("password");
		String email = params.get("email");
		
		System.out.println("beforee registerUser");
		if(Main.db.registerUser(username, email, password))		
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
	
	private void doLogout(Map <String, String> params, HttpExchange exc)
	{
		
	}
}
