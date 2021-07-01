package Metric;

public class GcG1Metric {

	private String timeStamp;
	private String collCpuUsage;
	private String timeSpent;
	private String reasonForGc;
	private String typeOfGc;
	private String memoryAllocation_1; //psyounggen
	private String memoryAllocation_2; //psoldgen
	private String memoryAllocation_3; //metaspace
	private String memoryAllocation_4;
	private String memoryAllocation_5;
	
	public GcG1Metric() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GcG1Metric(String timeStamp, String collCpuUsage, String timeSpent, String reasonForGc, String typeOfGc,
			String memoryAllocation_1, String memoryAllocation_2, String memoryAllocation_3, String memoryAllocation_4,
			String memoryAllocation_5) {
		super();
		this.timeStamp = timeStamp;
		this.collCpuUsage = collCpuUsage;
		this.timeSpent = timeSpent;
		this.reasonForGc = reasonForGc;
		this.typeOfGc = typeOfGc;
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

	public String getTypeOfGc() {
		return typeOfGc;
	}

	public void setTypeOfGc(String typeOfGc) {
		this.typeOfGc = typeOfGc;
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
		/*return "GC data\n TimeStamp=" + timeStamp + "\n Collection Cpu Usage=" + collCpuUsage+"\n Time Spent for GC="+timeSpent
				+ "\n Reason For Gc=" + reasonForGc + "\n Type Of Gc=" + typeOfGc + "\n PSYoungGen & time spent for gc="
				+ memoryAllocation_1 + "\n ParOldGen=" + memoryAllocation_2 + "\n Metaspace="
				+ memoryAllocation_3 + "\n";*/
		
		
		return "GC data\n TimeStamp and latency=" + timeStamp + "\n Collection Cpu Usage and time spent for gc=" + collCpuUsage
				+ "\n Reason For Gc=" + reasonForGc + "\n Type Of Gc=" + typeOfGc+"\n";
	}

	
	
	
}
