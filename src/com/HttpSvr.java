package com;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

class HttpSvr {
	
	private int port;
	private HttpServer svr;
	
	public static void main(String argv[]) throws Exception  
	{
	    HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
	    
	    server.createContext("/test", new MyHandler());
	    
	    server.start();
	}
	
	public void initServer(int port)
	{		
		try 
		{
			this.svr = HttpServer.create(new InetSocketAddress(port), 0);
			svr.setExecutor(null);
		    
			svr.start();
		} 
		catch (IOException e) 
		{
			//criar isto
			e.printStackTrace();
		}
	}
	
	public HttpServer addContext(String cont)
	{
		HttpServer server = null;
		
		try 
		{
			server = HttpServer.create(new InetSocketAddress(port), 0);
		    server.setExecutor(null);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return server;
	}
}


class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
