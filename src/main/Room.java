package main;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.*;

public class Room implements HttpHandler 
{
	private int id;
	private String type;
	
	public Room(String type)
	{
		// atribui id a room
		this.id = Main.randomGenerator.nextInt(1000000000);
		
		// atribui tipo (privado ou publico)
		this.setType(type);
		
		// atribuir contexto ao servidor HTTP
		Main.http.addContext(Integer.toString(this.id), this);
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