package communication;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.*;

import game.Room;

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
    		Map<String, String> params = new HashMap<String, String>();
    	    
    	    for (String param : query.split("&")) 
    	    {
    	        String pair[] = param.split("=");
    	        
    	        if(pair.length > 1)
    	        {
    	        	params.put(pair[0], pair[1]);
    	        }
    	        else
    	        {
    	        	params.put(pair[0], "");
    	        }
    	    }
    		
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
    	String maxPlayers = params.get("maxplayers");
    	
		System.out.println("room creation request");
    	
    	if(type.equals("private"))
    	{
    		System.out.println("private room creation");
    		//criar sala privada
    		
    		String pw = params.get("password");
    		
    		long id = new Date().getTime();    				
    		Room r1 = new Room(id, "privada", pw, Integer.parseInt(maxPlayers));
    		
    		rooms.add(r1);
    		
    		this.http.addContext("/room/" + id, r1);
    		
    		System.out.println("room creation OK");
    		
    		String response = "ID=" + id;
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
 	        
 	       System.out.println("RESPONSE SENT");
    	}
    	else
    	{
    		System.out.println("public room creation");
    		//criar sala publica
    		
    		long id = new Date().getTime();    				
    		Room r1 = new Room(id, "publica", Integer.parseInt(maxPlayers));
    		
    		rooms.add(r1);
    		
    		http.addContext("/room/" + id, r1);
    		
    		System.out.println("room creation OK");
    		
    		String response = "ID=" + id;
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
 	        
 	        System.out.println("RESPONSE SENT");
    	}
    }
    
    public void getAllPublicRooms(Map <String, String> params, HttpExchange exc) throws IOException
    {
    	System.out.println("public room listing request");
    	String response = "";
    	
    	if(rooms.size() == 0)
    	{
	    	for(int i = 0; i < rooms.size(); i++)
			{
				long id = rooms.get(i).getId();
				int currentP = rooms.get(i).getGame().getNrPlayers();
				int maxP = rooms.get(i).getMaxPlayers();
				
				response = response + id + ":" + currentP + ":" + maxP + ";";
			
    	
		    	exc.sendResponseHeaders(200, response.length());
		        OutputStream os = exc.getResponseBody();
		        os.write(response.getBytes());
		        os.close();
		        
		        System.out.println("RESPONSE SENT");
			}
    	}
    	else
    	{
    		response = "NOK";
			     	
	    	exc.sendResponseHeaders(200, response.length());
	        OutputStream os = exc.getResponseBody();
	        os.write(response.getBytes());
	        os.close();
	        
	        System.out.println("RESPONSE SENT");
    	}
    }
    
    // acho que isto nao fica aqui...
    private void doJoinRoom(Map <String, String> params, HttpExchange exc) throws IOException
    {
    	System.out.println("join room request");
    	
    	String type = params.get("type");
    	String userID = params.get("user");
    	String roomID = params.get("id");	
		String response = "";
    	
    	if(type.equals("private"))
    	{
    		String pw = params.get("password");
    		
    		//join do user na sala depois de confirmar pass
    		
    		for(int i = 0; i < rooms.size(); i++)
			{
				long id = rooms.get(i).getId();	
				
				if(id == Long.parseLong(roomID))
				{
					if(rooms.get(i).checkPassword(pw))
					{
						response = "OK";
						System.out.println("Join room " + roomID + " OK");
					}
						
					else
					{
						response = "NOK";
						System.out.println("Join room " + roomID + " NOT OK");
					}
				}
			}	
			
	    	exc.sendResponseHeaders(200, response.length());
	        OutputStream os = exc.getResponseBody();
	        os.write(response.getBytes());
	        os.close();
	        
 	       	System.out.println("RESPONSE SENT");
    	}
    	else
    	{
    		// join do user na sala publica
    		
    		response = "JOIN ROOM: OK";
 	        exc.sendResponseHeaders(200, response.length());
 	        OutputStream os = exc.getResponseBody();
 	        os.write(response.getBytes());
 	        os.close();
 	        
 	       System.out.println("RESPONSE SENT");
    	}
    }
}