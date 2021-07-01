package Metric;

public class GcMetric {

	private String timeStamp;
	private String collCpuUsage;
	private String timeSpent;
	private String reasonForGc;
	private String typeOfGc;
	private String memoryAllocation_1;
	private String memoryAllocation_2;
	private String memoryAllocation_3;
	private String memoryAllocation_4;
	private String memoryAllocation_5;
	
	public GcMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public GcMetric(String timeStamp,String typeOfGc, String collCpuUsage, String timeSpent, String reasonForGc,
			String memoryAllocation_1, String memoryAllocation_2, String memoryAllocation_3, String memoryAllocation_4,
			String memoryAllocation_5) {
		super();
		this.timeStamp = timeStamp;
		this.typeOfGc = typeOfGc;
		this.collCpuUsage = collCpuUsage;
		this.timeSpent = timeSpent;
		this.reasonForGc = reasonForGc;
		this.memoryAllocation_1 = memoryAllocation_1;
		this.memoryAllocation_2 = memoryAllocation_2;
		this.memoryAllocation_3 = memoryAllocation_3;
		this.memoryAllocation_4 = memoryAllocation_4;
		this.memoryAllocation_5 = memoryAllocation_5;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTypeOfGc() {
		return typeOfGc;
	}


	public void setTypeOfGc(String typeOfGc) {
		this.typeOfGc = typeOfGc;
	}

	public String getCollCpuUsage() {
		return collCpuUsage;
	}

	public void setCollCpuUsage(String collCpuUsage) {
		this.collCpuUsage = collCpuUsage;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getReasonForGc() {
		return reasonForGc;
	}

	public void setReasonForGc(String reasonForGc) {
		this.reasonForGc = reasonForGc;
	}

	public String getMemoryAllocation_1() {
		return memoryAllocation_1;
	}

	public void setMemoryAllocation_1(String memoryAllocation_1) {
		this.memoryAllocation_1 = memoryAllocation_1;
	}

	public String getMemoryAllocation_2() {
		return memoryAllocation_2;
	}

	public void setMemoryAllocation_2(String memoryAllocation_2) {
		this.memoryAllocation_2 = memoryAllocation_2;
	}

	public String getMemoryAllocation_3() {
		return memoryAllocation_3;
	}

	public void setMemoryAllocation_3(String memoryAllocation_3) {
		this.memoryAllocation_3 = memoryAllocation_3;
	}

	public String getMemoryAllocation_4() {
		return memoryAllocation_4;
	}

	public void setMemoryAllocation_4(String memoryAllocation_4) {
		this.memoryAllocation_4 = memoryAllocation_4;
	}

	public String getMemoryAllocation_5() {
		return memoryAllocation_5;
	}

	public void setMemoryAllocation_5(String memoryAllocation_5) {
		this.memoryAllocation_5 = memoryAllocation_5;
	}

	@Override
	public String toString() {
		return "GC data \n TimeStamp and latency=" + timeStamp + "\n Type of GC = "+typeOfGc+"\n Collection Cpu Usage and time spent for gc=" + collCpuUsage
				+ "\n Reason For Gc=" + reasonForGc + "\n PSYoungGen=" + memoryAllocation_1
				+ "\n ParOldGen=" + memoryAllocation_2 + "\n Metaspace=" + memoryAllocation_3+"\n";
	}
	
	
	


}
