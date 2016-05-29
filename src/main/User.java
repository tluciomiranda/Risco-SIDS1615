package main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import com.sun.net.httpserver.*;

public class User extends Thread implements HttpHandler
{
	private Queue<HttpExchange> opers = new LinkedList<HttpExchange>();
	
	public void handle(HttpExchange t) throws IOException 
    {
    	opers.add(t);
    }

	@Override
	public void run()
	{
		while(true)
		{
			HttpExchange exc = opers.poll();
			
			if(exc != null)
			{
				String query = exc.getRequestURI().getQuery();
		    	
		    	if(query != null)
		    	{
		    		Map <String,String> params = Main.http.queryToMap(exc.getRequestURI().getQuery());
			        
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
