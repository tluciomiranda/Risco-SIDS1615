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
	private ServerInfo si;
	private ServersInfo svsi = new ServersInfo();
	
	private volatile Queue<Message> waitList= new LinkedList <Message>();
	
	public tcpHandler(Db db, String localIp, ServerInfo si){
		this.db =db;
		this.localIp = localIp;
		this.si = si;
	}
	
	public void addMessage(Message m){
		waitList.add(m);
	}
	
	
	public void run() {
		boolean running = true;
		while(running){
			
			if(!waitList.isEmpty()){
				System.out.println("GOT MESSAGE");
				Message m = waitList.remove();
				if(m.getHeader().equals("POST newsrv")){
					
					Utils.So(m.getHeader());
					this.svsi.addServerInfo(m.getServerInfo());
					Message reply = new Message("POST srvsinfo");
					reply.addReceiverIp(m.getSenderIp());
					reply.addServersInfo(this.svsi.getServers());
					reply.addSenderIp(this.si.getLocalIp());
					reply.addReceiverTcpPort(m.getSenderTcpPort());
					reply.addSenderTcpPort(si.getLocalPort());
					TcpSend ts = new TcpSend(reply);
					ts.start();
				}
				else if(m.getHeader().equals("POST srvsinfo")){
					Utils.So("SERVER CONNECTED");
				}
				else if(m.getHeader().equals("GET Database")){
					ArrayList<dbLine> records;
				}
				else if(m.getHeader().equals("POST srvsinfo")){
					this.svsi.replaceServersInfo(m.getServersInfo());
				}
				else if(m.value==1){
					new SaveDbProtocol(m, db).start();
				}
				else if(m.value==2){
					String last = db.getLastRecordDate();
					Message reply = new Message(3,last);
					TcpSend ts = new TcpSend(reply);
					ts.start();
				}
				else if(m.value==3){
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
