package communication;

import java.io.*;
import java.net.*;

import interfaces.Interfaces;

public class HttpClnt 
{
	private Interfaces inter;
	
	public String httpReq(String request) throws IOException
	{
		String response = "";
		URL url = new URL("http://192.168.0.162:8083" + request);
		URLConnection conn = url.openConnection();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
		String inputLine;
		
		while ((inputLine = rd.readLine()) != null)
		{
			response = inputLine;

			System.out.println(response);			
		}
		
		return response;
	}
	
	public void addInterface(Interfaces inter){
		this.inter= inter;
	}
}
