package Metric;

public class PsMetric {
 
	private String pid;
	private String tty;
	private String time;
	private String cmd;
	
	public PsMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PsMetric(String pid, String tty, String time, String cmd) {
		super();
		this.pid = pid;
		this.tty = tty;
		this.time = time;
		this.cmd = cmd;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTty() {
		return tty;
	}

	public void setTty(String tty) {
		this.tty = tty;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public String toString() {
		return "pid=" + pid + ", tty=" + tty + ", time=" + time + ", cmd=" + cmd + "";
	}
	
	
}
