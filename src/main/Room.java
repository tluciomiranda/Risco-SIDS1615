package main;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.*;

public class Room implements HttpHandler 
{
	private long id;
	private String type;
	
	public Room(String type)
	{
		// atribui id a room
		this.id = new Date().getTime();		System.out.println(this.id);
		
		// atribui tipo (privado ou publico)
		this.setType(type);		
	}
	
    @Override
    public void handle(HttpExchange t) throws IOException 
    {
    	String query = t.getRequestURI().getQuery();
    	
    	if(query != null)
    	{
    		Map <String,String> params = Main.http.queryToMap(t.getRequestURI().getQuery());
	        /*
	        switch (params.get("action"))
	        {
	        	case "login":
	        		String ret = loginHandler(params.get("user"), params.get("pw"));
	        		break;
	        	case "":
	        		
	        		break;
	        }*/
    		
	        // resposta
	        String response = "This is the response from room " + this.id;
	        t.sendResponseHeaders(200, response.length());
	        OutputStream os = t.getResponseBody();
	        os.write(response.getBytes());
	        os.close();    		
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getID() {
		return id;
	}
}