package main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.BlockingQueue;

import com.sun.net.httpserver.*;

public class User extends Thread implements HttpHandler
{
	private volatile BlockingQueue<HttpExchange> opers = null;
	
	public void handle(HttpExchange t) throws IOException 
    {
		synchronized(opers)
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
    }

	@Override
	public void run()
	{
		while(true)
		{System.out.println("while");
			HttpExchange exc = null; 
			synchronized(opers)
			{
				while (opers.isEmpty())
				{
					try
					{System.out.println("wait cons");
						opers.wait();
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}	
				}	

				try {
					exc = opers.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(exc != null)
			{
				String query = exc.getRequestURI().getQuery();
		    	System.out.println("got query   " + query);
		    	if(query != null)
		    	{
		    		Map <String,String> params = Main.http.queryToMap(query);
			        
			        switch (params.get("action"))
			        {
			        	case "login":
			        			
			        		break;
			        	case "registar":
			        		
			        		break;
			        	case "logout":
			        		
			        		break;
			        }
			        
			        // resposta
		    		try 
		    		{
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
