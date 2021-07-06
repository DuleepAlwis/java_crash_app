package Metric;

public class NetStatMetric {

	//Proto Recv-Q Send-Q Local Address           Foreign Address         State      

	private String proto;
	private String recv;
	private String send;
	private String localAddress;
	private String foreignAddress;
	private String state;
	
	public NetStatMetric() {
		super(); 
		// TODO Auto-generated constructor stub
	}

	public NetStatMetric(String proto, String recv, String send, String localAddress, String foreignAddress,
			String state) {
		super();
		this.proto = proto;
		this.recv = recv;
		this.send = send;
		this.localAddress = localAddress;
		this.foreignAddress = foreignAddress;
		this.state = state;
	}

	public String getProto() {
		return proto;
	}

	public void setProto(String proto) {
		this.proto = proto;
	}

	public String getRecv() {
		return recv;
	}

	public void setRecv(String recv) {
		this.recv = recv;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getForeignAddress() {
		return foreignAddress;
	}

	public void setForeignAddress(String foreignAddress) {
		this.foreignAddress = foreignAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "proto=" + proto + ", recv=" + recv + ", send=" + send + ", localAddress=" + localAddress
				+ ", foreignAddress=" + foreignAddress + ", state=" + state + "";
	}
	
	
	
}
