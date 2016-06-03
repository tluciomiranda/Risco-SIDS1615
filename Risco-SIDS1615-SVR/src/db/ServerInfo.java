package db;

public class ServerInfo {
	private String localIp;
	private int tcpPort;
	
	private String mediatorIp;
	private int mediatorTcpPort;
	
	private boolean amMediator = false;
	
	public ServerInfo(String ip, int port, String mIp, int mPort){
		this.localIp = ip;
		this.tcpPort = port;
		this.mediatorIp = mIp;
		this.mediatorTcpPort = mPort;
		
		if(this.localIp == this.mediatorIp){
			this.amMediator = true;
		}
	}
	
	public String getMediatorIp(){
		return this.mediatorIp;
	}
	
	public String getLocalIp(){
		return this.localIp;
	}
	
	public int getLocalPort(){
		return this.tcpPort;
	}
	
	public int getMediatorPort(){
		return this.mediatorTcpPort;
	}
	
	public boolean amMediator(){
		return this.amMediator;
	}

}
