package communication;

import java.net.*;
import java.io.*;

public class TcpSend extends Thread{
	private Message message;
	
	public TcpSend(Message m){
		this.message = m;
	}
	
	public void run(){
		
		try{
			Socket s = new Socket();
			s.connect(new InetSocketAddress(this.message.getReceiverIp(),this.message.getReceiverTcpPort()), 10000);
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Message to = this.message;
			
			oos.writeObject(to);
			oos.flush();
			oos.close();
			os.close();
			s.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
}
