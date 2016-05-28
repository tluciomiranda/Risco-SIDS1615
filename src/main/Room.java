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
		this.id = new Date().getTime();
		System.out.println(this.id);
		// atribui tipo (privado ou publico)
		this.setType(type);
		
		// atribuir contexto ao servidor HTTP
		Main.http.addContext(Long.toString(this.id), this);
	}
	
    @Override
    public void handle(HttpExchange t) throws IOException 
    {
        String response = "This is the response from room " + this.id;
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}