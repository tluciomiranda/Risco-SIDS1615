package game;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.*;

import communication.HttpSvr;
import main.Main;

public class RoomManager implements HttpHandler 
{
	private ArrayList<Room> rooms;
	private HttpSvr http;
	
	public RoomManager(HttpSvr http)
	{
		this.http = http;
	}
	
    @Override
    public void handle(HttpExchange t) throws IOException 
    {
    	String query = t.getRequestURI().getQuery();
    	
    	if(query != null)
    	{
    		Map <String,String> params = this.http.queryToMap(t.getRequestURI().getQuery());
	        
	        switch (params.get("action"))
	        {
	        	case "create":
	        		doCreateRoom(params, t);
	        		break;
	        	case "join":
	        		doJoinRoom(params, t);
	        		break;
	        	case "getAvailable":
	        		getAllPublicRooms(params, t);
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
    		//criar sala privada
    		
    		String pw = params.get("password");
    		
    		long id = new Date().getTime();    				
    		Room r1 = new Room(id, "privada", pw, Integer.parseInt(maxPlayers), Integer.parseInt(userID));
    		
    		rooms.add(r1);
    		
    		this.http.addContext("/room/" + id, r1);
    		
    		String response = "ID=" + id;
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
    	}
    	else
    	{
    		//criar sala publica
    		
    		long id = new Date().getTime();    				
    		Room r1 = new Room(id, "publica", Integer.parseInt(maxPlayers), Integer.parseInt(userID));
    		
    		rooms.add(r1);
    		
    		this.http.addContext("/room/" + id, r1);
    		
    		String response = "ID=" + id;
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
    	}
    }
    
    public void getAllPublicRooms(Map <String, String> params, HttpExchange exc) throws IOException
    {
    	String userID = params.get("user");
    	String response = "";
    	
    	for(int i = 0; i < rooms.size(); i++)
		{
			long id = rooms.get(i).getId();
			int currentP = rooms.get(i).getGame().getNrPlayers();
			int maxP = rooms.get(i).getMaxPlayers();
			
			response = response + id + ":" + currentP + ":" + maxP + ";";
		}
    	
    	exc.sendResponseHeaders(200, response.length());
        OutputStream os = exc.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    // acho que isto nao fica aqui...
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