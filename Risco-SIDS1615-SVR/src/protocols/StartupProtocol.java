package protocols;


import communication.Message;
import communication.TcpSend;
import db.Db;
import db.ServerInfo;
import utils.*;

public class StartupProtocol extends Thread{
	
	private Db db;
	private ServerInfo si;
	
	public StartupProtocol(Db db, ServerInfo si){
		this.db = db;
		this.si = si;
	}
	
	public void run(){
		
		Message reply = new Message("POST newsrv");
		reply.addReceiverIp(this.si.getMediatorIp());
		reply.addSenderIp(this.si.getLocalIp());
		reply.addServerInfo(si);
		reply.addSenderTcpPort(si.getLocalPort());
		reply.addReceiverTcpPort(si.getMediatorPort());
		TcpSend ts = new TcpSend(reply);
		ts.start();

		/*
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		*/
		
		
		String dbLast = db.getLastRecordDate();
		Message reply2 = new Message("GET databaseDate");
		reply2.addReceiverIp(this.si.getMediatorIp());
		reply2.addSenderIp(this.si.getLocalIp());
		reply2.addSenderTcpPort(si.getLocalPort());
		reply2.addReceiverTcpPort(si.getMediatorPort());
		reply2.addLastDb(dbLast);
		
		TcpSend ts2 = new TcpSend(reply2);
		ts2.start();
		
		
	}

}
