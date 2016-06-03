package game;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import main.Main;

public class Room implements HttpHandler 
{
	long id;
	String password;
	String type;
	Game game;
	int maxPlayers;
	int createdBy;

	public Room(long id, String type, int maxPlayers, int userID)
	{
		this.id = id;
		this.type = type;
		this.maxPlayers = maxPlayers;
		this.createdBy = userID;
	}

	public Room(long id, String type, String password, int maxPlayers, int userID)
	{
		this.id = id;
		this.type = type;
		this.password = password;
		this.maxPlayers = maxPlayers;
		this.createdBy = userID;
	}
	
	public void handle(HttpExchange t) throws IOException 
    {
		String query = t.getRequestURI().getQuery();

    	if(query != null)
    	{
    		
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
	
	public long getId()
	{
		return id;
	}
}
