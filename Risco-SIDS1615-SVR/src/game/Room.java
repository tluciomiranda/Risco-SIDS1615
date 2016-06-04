package game;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Room implements HttpHandler 
{
	private long id;
	private String password;
	private String type;
	private Game game;
	private int maxPlayers;

	public Room(long id, String type, int maxPlayers)
	{
		this.id = id;
		this.type = type;
		this.maxPlayers = maxPlayers;
	}

	public Room(long id, String type, String password, int maxPlayers)
	{
		this.id = id;
		this.type = type;
		this.password = password;
		this.maxPlayers = maxPlayers;
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
	
	public String getType()
	{
		return type;
	}
	
	public int getMaxPlayers()
	{
		return maxPlayers;
	}
	
	public Game getGame()
	{
		return game;
	}

	public boolean checkPassword(String pw) 
	{
		if(pw.equals(password))
			return true;
		
		return false;
	}
}
