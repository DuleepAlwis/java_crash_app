package Metric;

import java.util.List;

public class ThreadMetric {

	private String threadName;
	private String threadPrio;
	private String threadId;
	private String threadStackAddress;
	private String threadNumber;
	private String threadState;
	private List<String> stackTrace;
	
	public ThreadMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThreadMetric(String threadName, String threadPrio, String threadId, String threadStackAddress,
			String threadNumber, String threadState, List<String> stackTrace) {
		super();
		this.threadName = threadName;
		this.threadPrio = threadPrio;
		this.threadId = threadId;
		this.threadStackAddress = threadStackAddress;
		this.threadNumber = threadNumber;
		this.threadState = threadState;
		this.stackTrace = stackTrace;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadPrio() {
		return threadPrio;
	}

	public void setThreadPrio(String threadPrio) {
		this.threadPrio = threadPrio;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getThreadStackAddress() {
		return threadStackAddress;
	}

	public void setThreadStackAddress(String threadStackAddress) {
		this.threadStackAddress = threadStackAddress;
	}

	public String getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(String threadNumber) {
		this.threadNumber = threadNumber;
	}

	public String getThreadState() {
		return threadState;
	}

	public void setThreadState(String threadState) {
		this.threadState = threadState;
	}

	public List<String> getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(List<String> stackTrace) {
		this.stackTrace = stackTrace;
	}

	@Override
	public String toString() {
		return "Thread details \n Thread Name=" + threadName + "\n Thread Prio=" + threadPrio + "\n Thread Id=" + threadId
				+ "\n Thread Stack Address=" + threadStackAddress + "\n Thread Number=" + threadNumber + "\n Thread State="
				+ threadState + "\n StackTrace=" + stackTrace.toString() + "\n";
	}
	
	
}
