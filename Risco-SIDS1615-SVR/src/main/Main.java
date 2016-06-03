package main;
import communication.*;


public class Main {
	
	 public static void main(String args[]) { 
		 
		 if(args.length != 4){
			System.out.println("Usage: <localmachine ip> <TCPPort><mediator ip><mediator TCPPort>");
			return;
		}
			
			
		int port = Integer.parseInt(args[1]);
		String ip = args[0];
		int mediatorPort = Integer.parseInt(args[3]);
		String mediatorIp = args[2];
		
		
		 Server sv = new Server(ip,port);
		 sv.go(mediatorIp,mediatorPort);
		 
	 }

}
