package protocols;


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
		
		
		String last = db.getLastRecordDate();
		Message reply2 = new Message(3,last);
		TcpSend ts2 = new TcpSend(reply);
		ts.start();
		
		/*
		Message reply2 = new Message("GET Database");
		String lastDate = db.getLastRecordDate();
		reply2.addLastDb(lastDate);
		reply2.addServerInfo(this.si);
		reply2.addReceiverIp(this.si.getMediatorIp());
		reply2.addSenderIp(this.si.getLocalIp());
		TcpSend ts2 = new TcpSend(reply2);
		ts2.start();*/
	}

}
