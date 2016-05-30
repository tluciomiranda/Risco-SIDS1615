package main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.*;

public class RoomManager implements HttpHandler 
{
	public RoomManager()
	{
		
	}
	
    @Override
    public void handle(HttpExchange t) throws IOException 
    {
    	String query = t.getRequestURI().getQuery();
    	
    	if(query != null)
    	{
    		Map <String,String> params = Main.http.queryToMap(t.getRequestURI().getQuery());
	        
	        switch (params.get("action"))
	        {
	        	case "create":
	        		doCreateRoom(params, t);
	        		break;
	        	case "join":
	        		doJoinRoom(params, t);
	        		break;
	        }	
	    }
    	else
    	{
            String response = "Invalid request.";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();		
		}
    }
    
    private void doCreateRoom(Map <String, String> params, HttpExchange exc) throws IOException
    {
    	String type = params.get("type");
    	String userID = params.get("user");
    	String maxPlayers = params.get("maxplayers");
    	
    	if(type.equals("private"))
    	{
    		String pw = params.get("password");
    		
    		//criar sala privada
    		
    		String response = "reposta";
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
    	}
    	else
    	{
    		//criar sala publica
    		
    		String response = "reposta";
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
    	}
    }
    
    private void doJoinRoom(Map <String, String> params, HttpExchange exc) throws IOException
    {
    	String type = params.get("type");
    	String userID = params.get("user");
    	String roomID = params.get("id");
    	
    	if(type.equals("private"))
    	{
    		String pw = params.get("password");
    		
    		//join do user na sala depois de confirmar pass
    		
    		String response = "reposta";
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
    	}
    	else
    	{
    		// join do user na sala publica
    		
    		String response = "reposta";
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
    	}
    }
}