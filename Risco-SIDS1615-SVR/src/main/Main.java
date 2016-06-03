package main;
import communication.*;


public class Main {
	
	 public static void main(String args[]) { 
		 
		 if(args.length != 5){
			System.out.println("Usage: <localmachine ip> <TCPPort> <mediator ip> <mediator TCPPort> <httpPort>");
			return;
		}
			
		int port = Integer.parseInt(args[1]);
		String ip = args[0];
		int mediatorPort = Integer.parseInt(args[3]);
		String mediatorIp = args[2];
		int httpPort = Integer.parseInt(args[4]);
		
		
		Server sv = new Server(ip,port,httpPort);
		sv.go(mediatorIp,mediatorPort);		 
	 }
}
