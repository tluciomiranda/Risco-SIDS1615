package main;
import communication.*;


public class Main {
	
	
	
	
	
	 public static void main(String args[]) { 
		 
		 if(args.length != 2){
			System.out.println("Usage: <localmachine ip> <TCPPort>");
			return;
		}
			
			
		int port = Integer.parseInt(args[1]);
		String ip = args[0];
		
		 Server sv = new Server(ip,port);
		 sv.go();
		 
	 }

}
