package Metric;

public class VmStatMetric {

	/**r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st**/
	private String headerLine = "procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----\r\n";
	private String r;
	private String b;
	private String swpd;
	private String free;
	private String buff;
	private String cache;
	private String si;
	private String so;
	private String bi;
	private String bo;
	private String in;
	private String cs;
	private String us;
	private String sy;
	private String id;
	private String wa;
	private String st;
	
	public VmStatMetric() {
		
	}

	public VmStatMetric(String headerLine, String r, String b, String swpd, String free, String buff, String cache,
			String si, String bi, String bo, String in, String cs, String us, String sy, String id, String wa,
			String st,String so) {
		super();
		this.headerLine = headerLine;
		this.r = r;
		this.b = b;
		this.swpd = swpd;
		this.free = free;
		this.buff = buff;
		this.cache = cache;
		this.si = si;
		this.so = so;
		this.bi = bi;
		this.bo = bo;
		this.in = in;
		this.cs = cs;
		this.us = us;
		this.sy = sy;
		this.id = id;
		this.wa = wa;
		this.st = st;
	}

	public void setSo(String so) {
		this.so = so;
	}
	
	public String getSo() {
		return so;
	}
	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getSwpd() {
		return swpd;
	}

	public void setSwpd(String swpd) {
		this.swpd = swpd;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getBuff() {
		return buff;
	}

	public void setBuff(String buff) {
		this.buff = buff;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	public String getBo() {
		return bo;
	}

	public void setBo(String bo) {
		this.bo = bo;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWa() {
		return wa;
	}

	public void setWa(String wa) {
		this.wa = wa;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	@Override
	public String toString() {
		return "Procs[r=" + r + ", b=" + b + "]  Memory[swpd=" + swpd + ", free=" + free + ", buff=" + buff + ", cache="
				+ cache + "] Swap[si=" + si + ", so=" + so + "] io[bi=" + bi + ", bo=" + bo + "]  system[in=" + in + ", cs=" + cs
				+ "] cpu[us=" + us + ", sy=" + sy + ", id=" + id + ", wa=" + wa + ", st=" + st + "]";
	}

	/*@Override
	public String toString() {
		return "Procs[r=" + r + ", b=" + b + "] Memory[swpd=" + swpd + ", free=" + free + ", buff=" + buff + ", cache="
				+ cache + "]  Swap[si=" + si + ",so="+so+"] io[bi=" + bi + ", bo=" + bo + "]  System[in=" + in + ", cs=" + cs + "]  CPU[us=" + us
				+ ", sy=" + sy + ", id=" + id + ", wa=" + wa + ", st=" + st + "]";
	}*/
	
	
	

}
