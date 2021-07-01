package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Metric.DmesgMetric;
import Metric.NetStatMetric;
import Metric.PsMetric;
import Metric.VmStatMetric;

public class LinuxLog {

	private String filePath;
	
	public LinuxLog() {
		
	}
	
	public LinuxLog(String file) {
		
		this.filePath = file;
	}
	
	public void readPsFile() {
		
		
		File file = new File(this.filePath);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			List<String> headers = new ArrayList<String>();	
			List<PsMetric> rows = new ArrayList<PsMetric>();
			String [] val = null;
			int itr = 1,startReadData=0;
			
			BufferedReader brHeaders = new BufferedReader(new FileReader(new File("./config/headers_file.txt")));
			
			/**Read headers from the config file**/
			
			while((line=brHeaders.readLine())!=null) {
				
				
				if(line.contains("PS_LOG_UNIX_HEADERS")) {
					line = line.replace("PS_LOG_UNIX_HEADERS:", "");
					val = line.split(",");
					
					for(int i =0;i<val.length;i++) {
						headers.add(val[i]);
					}
				}else if(line.contains("PS_LOG_UNIX_START_READ_DATA")) {
					
					startReadData = Integer.parseInt(line.split(":")[1]);
					
				}
			}
			
			while((line=br.readLine())!=null) {
				
				if(itr>=startReadData) {
					

					
					
						line = line.replaceAll("\s+", " ");

						val = line.split(" ");
						
						PsMetric ps = new PsMetric();
						
						for(int i=0;i<val.length;i++) {
							if(headers.get(i).equalsIgnoreCase("PID")) {
								ps.setPid(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("TTY")) {
								ps.setTty(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("TIME")) {
								ps.setTime(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("CMD")) {
								ps.setCmd(val[i]);
							}
						}
						rows.add(ps);
				}
				itr++;
			}
			
			System.out.println(rows.size());
			rows.forEach(i->{
				
				System.out.println(i.toString());
			});
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public void readVmStatFile() {
		
		
		File file = new File(this.filePath);
		int iter = 1;
		int dataRowStart = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			List<String> headers = new ArrayList<String>();
			ArrayList<VmStatMetric> rows = new ArrayList<VmStatMetric>();
			String [] val ;
			
			BufferedReader brHeaders = new BufferedReader(new FileReader(new File("./config/headers_file.txt")));
			
			while((line=brHeaders.readLine())!=null) {
				
				if(line.contains("VM_STAT_HEADERS")) {
					line = line.replace("VM_STAT_HEADERS:", "");
					val = line.split(",");
					
					for(int i =0;i<val.length;i++) {
						headers.add(val[i]);
					}
					
				}
				if(line.contains("VM_STAT_START_READ_DATA")) {
					dataRowStart = Integer.parseInt(line.split(":")[1]);
				}
			}
			line = "";
			while((line=br.readLine())!=null) {
				
				
				if(iter>=dataRowStart) {
					
					line = line.replaceAll("\s+", " ");

						line = line.replaceAll("\s+", " ");
						
						val = line.split(" ");
						
						for(int i =0;i<val.length;i++) {
							if(!val[i].equalsIgnoreCase(" ")) {
								val[i] = val[i];
							}
						}
						
						VmStatMetric vm = new VmStatMetric();
						
						
						for(int i=0;i<val.length;i++) {
							
							if(headers.get(i).equalsIgnoreCase("r")) {
								vm.setR(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("b")) {
								vm.setB(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("b")) {
								vm.setB(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("swpd")) {
								vm.setSwpd(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("free")) {
								vm.setFree(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("buff")) {
								vm.setBuff(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("cache")) {
								vm.setCache(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("si")) {
								vm.setSi(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("so")) {
								vm.setSo(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("bi")) {
								vm.setBi(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("bo")) {
								vm.setBo(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("in")) {
								vm.setIn(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("cs")) {
								vm.setCs(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("us")) {
								vm.setUs(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("sy")) {
								vm.setSy(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("id")) {
								vm.setId(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("wa")) {
								vm.setWa(val[i]);
							}else if(headers.get(i).equalsIgnoreCase("st")) {
								vm.setSt(val[i]);
							}
							
							
						}
						
						rows.add(vm);
											
					
					
				}
				iter++;
			}
			
			System.out.println(rows.size());
			
			rows.forEach(i->{
				System.out.println(i.toString());
			});
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public void getPs() {
		
		try {
			String process;
			// getRuntime: Returns the runtime object associated with the current Java application.
			// exec: Executes the specified string command in a separate process.
			//Process p = Runtime.getRuntime().exec("ps -few");
			Process p = Runtime.getRuntime().exec("tasklist");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((process = input.readLine()) != null) {
				System.out.println(process); // <-- Print all Process here line
												// by line
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	
	public void readNetStatFile() {
		
		
		File file = new File(this.filePath);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			List<String> headers = new ArrayList<String>();
			
			ArrayList<NetStatMetric> rows = new ArrayList<NetStatMetric>();
			int startRead = 0,itr=1;
			String [] val = null;
			
			BufferedReader headerFile = new BufferedReader(new FileReader(new File("./config/headers_file.txt")));
			
			while((line = headerFile.readLine())!=null) {
				
				if(line.contains("NET_STAT_START_READ")) {
					
					startRead = Integer.parseInt(line.split(":")[1]);
					
				}else if(line.contains("NET_STAT_HEADERS")) {
					
					line = line.replaceFirst("NET_STAT_HEADERS:", "");
					val = line.split(",");
					
					for(int i =0;i<val.length;i++) {
						headers.add(val[i]);
					}
					
				}
			}
			
			while((line=br.readLine())!=null) {
				
				
					if(itr>=startRead) {
						
						
						
						if(line.contains("UNIX")) {
							break;
						}
						
						NetStatMetric nm = new NetStatMetric();
						
							
						line = line.replaceAll("\s+", " ");
						val = line.split(" ");
						
						
						if(headers.size()==val.length) {
							
						
							for(int i=0;i<val.length;i++) {
								
								if(headers.get(i).equalsIgnoreCase("Proto")) {
									nm.setProto(val[i]);
								}else if(headers.get(i).equalsIgnoreCase("Recv-Q")) {
									nm.setRecv(val[i]);
								}else if(headers.get(i).equalsIgnoreCase("Send-Q")) {
									nm.setSend(val[i]);
								}else if(headers.get(i).equalsIgnoreCase("Local Address")) {
									nm.setLocalAddress(val[i]);
								}else if(headers.get(i).equalsIgnoreCase("Foreign Address")) {
									nm.setForeignAddress(val[i]);
								}else if(headers.get(i).equalsIgnoreCase("State")) {
									nm.setState(val[i]);
								}
	
	
								
								
							}
							rows.add(nm);
						}
						
						
						
								
								
							
						
						
					}
					
				
				
				
			
				itr++;
			}
			
			System.out.println(rows.size());
			rows.forEach(i->{
				System.out.println(i.toString());
			});
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	public void readDMesg() {
		
		File file = new File(this.filePath);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			//String headers = "\t\tTime\t\t\tDriver Message\n----------------------------------------------------\n";
			ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
			ArrayList<DmesgMetric> msg = new ArrayList<DmesgMetric>();
			List<String> headers = new ArrayList<String>();
			
			int openParIndex = 0;
			int closeParIndex = 0;
			int itr = 1,startRead = 0;
			String [] val = null;
			
			BufferedReader headerFile = new BufferedReader(new FileReader(new File("./config/headers_file.txt")));
			
			while((line = headerFile.readLine())!=null) {
				
				if(line.contains("DMESG_DATA_START")) {
					
					startRead = Integer.parseInt(line.split(":")[1]);
					
				}else if(line.contains("DMESG_HEADERS")) {
					
					line = line.replaceFirst("DMESG_HEADERS:", "");
					val = line.split(",");
					
					for(int i =0;i<val.length;i++) {
						headers.add(val[i]);
					}
					
				}
			}
			
			while((line = br.readLine())!=null) {
				
				//ArrayList<String> msg = new ArrayList<String>();
				DmesgMetric dmesg = new DmesgMetric();
				
				openParIndex = line.indexOf('[');
				closeParIndex = line.indexOf(']');
				
				if(openParIndex>=0 && closeParIndex>=0) {
					dmesg.setTimeLatency(line.substring(openParIndex+1,closeParIndex).trim());
					dmesg.setMsg(line.substring(closeParIndex+1, line.length()).trim());
				}
				msg.add(dmesg);
				
			}
			
			File output = new File("dmesg_summary.txt");
			FileWriter fw = new FileWriter(output);
			
			
			/*if(values.size()>0) {
				
				fw.write(headers);
				for(int i=0;i<values.size();i++) {
					
					
					for(int j=0;j<values.get(i).size();j++) {
						//System.out.println(gcLogLineList.get(i).get(j));

						fw.write(values.get(i).get(j));
						fw.flush();
					}
					
					
				}
				
				System.out.println(values.size());
			}*/
			
			fw.write("Kernal Messages :\n");
			for(int i=0;i<msg.size();i++) {
				
				fw.write(msg.get(i).toString());
				fw.flush();
			}
			
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
