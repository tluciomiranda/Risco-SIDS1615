package communication;

import java.io.*;
import java.net.*;

public class HttpClnt 
{
	private String ip;
	private int port;
	
	public HttpClnt(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}
	
	public String httpReq(String request) throws IOException
	{
		String response = "";
		URL url = new URL("http://" + ip + ":" + port + request);
		URLConnection conn = url.openConnection();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
		String inputLine;
		
		while ((inputLine = rd.readLine()) != null)
		{
			response = inputLine;

			System.out.println("FROM SERVER: " + response);			
		}
		
		return response;
	}
}
