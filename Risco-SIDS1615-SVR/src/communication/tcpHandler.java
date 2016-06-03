package communication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
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
				
				Message m = waitList.remove();
				
				Utils.So(m.getHeader());
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
				else if(m.getHeader().equals("GET databaseDate")){
					ArrayList<dbLine> records;
					String last = db.getLastRecordDate();
					
					Message reply = new Message();
					reply.addReceiverIp(m.getSenderIp());
					reply.addServersInfo(this.svsi.getServers());
					reply.addSenderIp(this.si.getLocalIp());
					reply.addReceiverTcpPort(m.getSenderTcpPort());
					reply.addSenderTcpPort(si.getLocalPort());
					
					
					if(!m.getLastdb().equals("NULL") || !last.equals("NULL")){
						String header;
						
						if(m.getLastdb().equals("NULL") && !last.equals("NULL")){
							Utils.So("1");
							records = db.getAllRecords();
							reply.addDb(records);
							header = "POST records_no_reply";
						}
						else if(!m.getLastdb().equals("NULL") && last.equals("NULL")){
							Utils.So("2");
							header = "POST records_reply_all";
						}
						else{
							Utils.So("3");
							String dP = db.getLastRecordDate();
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS:ss");
							Date datePeer;
							header = "POST discard";
							try {
								datePeer = format.parse(m.getLastdb());
								Date dateMediator = format.parse(dP);
								if(dateMediator.compareTo(datePeer)<0){
									header= "GET databaseDate";
									reply.addLastDb(dP);
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Utils.So(header);
						}
						
						reply.setHeader(header);
						TcpSend ts = new TcpSend(reply);
						ts.start();
					
					}
					
				}
				else if(m.getHeader().equals("POST srvsinfo")){
					this.svsi.replaceServersInfo(m.getServersInfo());
				}
				else if(m.getHeader().equals("POST records_no_reply")){
					
					new SaveDbProtocol(m, db).start();
				}

			}
			
			
		}
		
	}
	
}
