package Metric;

public class DmesgMetric {

	private String timeLatency;
	private String msg;
	
	public DmesgMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DmesgMetric(String timeLatency, String msg) {
		super();
		this.timeLatency = timeLatency;
		this.msg = msg;
	}

	public String getTimeLatency() {
		return timeLatency;
	}

	public void setTimeLatency(String timeLatency) {
		this.timeLatency = timeLatency;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Time Latency=" + timeLatency + ", messsage=" + msg + "\n";
	}
	
	
}
