package communication;
import java.io.*;
import java.net.*;
import java.util.*;

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
		this.svr.setExecutor(null);
		this.svr.start();
	}
	
	public void addContext(String cont, HttpHandler hand)
	{
		this.svr.createContext(cont, hand);
	}
}
