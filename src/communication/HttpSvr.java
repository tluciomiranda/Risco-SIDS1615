package communication;
import main.*;


import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class HttpSvr {
	
	private int port;
	private HttpServer svr;

	public HttpSvr(int port)
	{
		this.port = port;
		
		try 
		{
			this.svr = HttpServer.create(new InetSocketAddress(this.port), 0);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void initServer()
	{		
		svr.setExecutor(null);
	    //svr.createContext("/jogo", new MyHandler());
	    //svr.createContext("/joga", new MyHandler2());
	    //svr.createContext("/joga/umjogo", new MyHandler3());
	    
		svr.start();
	}
	
	public void addContext(String cont, Room room)
	{
	    svr.createContext("/jogo/"+cont, room);
	}
}





class MyHandler2 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "This is the response2";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


class MyHandler3 implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "This is the response3";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
