package communication;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import db.*;
import utils.*;
import protocols.*;

public class tcpHandler extends Thread {
	private Db db;
	private String localIp;
	
	private volatile Queue<Message> waitList= new LinkedList <Message>();
	
	public tcpHandler(Db db, String localIp){
		this.db =db;
		this.localIp = localIp;
	}
	
	public void addMessage(Message m){
		waitList.add(m);
	}
	
	
	public void run() {
		boolean running = true;
		while(running){
			
			if(!waitList.isEmpty()){
				System.out.println("NOT EMPTY");
				Message m = waitList.remove();
				if(m.value==1){
					new SaveDbProtocol(m, db).start();
				}
				if(m.value==2){
					String last = db.getLastRecordDate();
					Message reply = new Message(3,last);
					TcpSend ts = new TcpSend(reply);
					ts.start();
				}
				if(m.value==3){
					ArrayList<dbLine> records = db.getRecordsByDate(m.id);
					
					Message reply = new Message(1,"save");
					reply.addReceiverIp(m.getSenderIp());
					System.out.println("sender"+m.getSenderIp());
					reply.addSenderIp(this.localIp);
					m.addDb(records);
					TcpSend ts = new TcpSend(reply);
					ts.start();
					System.out.println("RECORDS SENT");
				}
				
				
			}
			
			
		}
		
	}
	
}
