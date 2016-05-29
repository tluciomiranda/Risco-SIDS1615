package communication;

import java.io.*;
import java.net.*;

public class HttpClnt 
{
	public void httpReq() throws IOException
	{
		URL url = new URL("http://192.168.1.65/login");
		URLConnection conn = url.openConnection();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	
		String inputLine;
		
		while ((inputLine = rd.readLine()) != null)
		{
			System.out.println(inputLine);
		}
	}
}
