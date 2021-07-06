package Metric;

public class PsWindowsMetric {

	//Image Name                     PID Session Name        Session#    Mem Usage

	private String imageName;
	private String pid;
	private String sessionName;
	private String session;
	private String memUsage;
	
	public PsWindowsMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PsWindowsMetric(String imageName, String pid, String sessionName, String session, String memUsage) {
		super();
		this.imageName = imageName;
		this.pid = pid;
		this.sessionName = sessionName;
		this.session = session;
		this.memUsage = memUsage;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getMemUsage() {
		return memUsage;
	}

	public void setMemUsage(String memUsage) {
		this.memUsage = memUsage;
	}

	@Override
	public String toString() {
		return "PsWindowsMetric [imageName=" + imageName + ", pid=" + pid + ", sessionName=" + sessionName
				+ ", session=" + session + ", memUsage=" + memUsage + "]";
	}
	
	
}
