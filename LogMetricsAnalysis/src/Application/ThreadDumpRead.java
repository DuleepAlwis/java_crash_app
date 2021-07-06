package Application;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Metric.ThreadMetric;

public class ThreadDumpRead {

	private String filePath;
	
	public ThreadDumpRead() {
		
	}
	
	public ThreadDumpRead(String filePath) {
		
		this.filePath = filePath;
	}
	
	public void readFile() {
		
		File file = new File(this.filePath);
		//ArrayList<ArrayList<ThreadMetric>> threadDetails = new ArrayList<ArrayList<ThreadMetric>>();
		ArrayList<ThreadMetric> threads = new ArrayList<ThreadMetric>();
		boolean threadStackPrc = false,threadMetaData = false,threadStackTrace = false,threadState = false;
		  //0 - thread name , 1-thread priority, 2- thread address , 3- thread stack address , 4- thread state 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = "";
			//";//"/(?:'|\").*(?:'|\")(\\w).*";
			String patThreadInitLine = "(^\")(\\w.*)(\")(.*)";
			String patThreadId = "#(\\d+)";
			
			String patThreadPrio = "prio=(\\d+)";
			String patThreadStackAddress = "\\[(0x)(\\w).*\\]";
			String patThreadAddress = "tid=(\\w).*";
			String patThreadState = "java.lang.Thread.State:";
			String patOSThreadId = "nid=(0x)(\\w).*";
			boolean isJvmThread = true;
			
			System.out.println("Start reading thread dump file");
			
			while((line=br.readLine())!=null) {
//				if(line.length()==0) {
//					System.out.println("line empty");
//				}
				
				if(line.matches(patThreadInitLine)) {   //Identify the initialization of the thread 
					threadStackPrc = true;
					
					ThreadMetric tm = new ThreadMetric();
					ArrayList<String> stackTrace = new ArrayList<String>();
					//System.out.println(line);

					//System.out.println(line.substring(line.indexOf("\"")+1,line.lastIndexOf("\"")));
					tm.setThreadName(line.substring(line.indexOf("\"")+1,line.lastIndexOf("\"")));
					//thread.add(0,"Thread name = "+line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""))+"\n");
					String [] content = line.substring(line.lastIndexOf("\"")+1).split(" ");
					
					for(int i = 0;i<content.length;i++) {
						
						if(content[i].matches(patThreadId)) {
							//thread.add(1,"Thread id = "+content[i]+"\n");
							tm.setThreadId(content[i]);
						}else if(content[i].matches(patThreadPrio)) { //Match thread priority
							//thread.add(1,content[i]+"\n");
							tm.setThreadPrio(content[i].replace("prio=", ""));
							isJvmThread = false;
						}else if(content[i].matches(patThreadAddress)) { //Match thread address 
							tm.setThreadNumber(content[i].replace("tid=", ""));
							//thread.add(2,content[i]+"\n");
						}else if(content[i].matches(patThreadStackAddress)) { //Match thread stack address
							tm.setThreadStackAddress(content[i]);
							//thread.add(3,"Thread stack address = "+content[i]+"\n");
						}else if(content[i].matches(patOSThreadId) && (i+1)<content.length) { //Matches the state of the JVM only threads.
							if(isJvmThread){
								//thread.add(4,"Thread state = "+content[i+1]+"\n");
								tm.setThreadState(content[i]);
							}
						}
						
						

					}
					isJvmThread = true;

					
					threadMetaData = true;
					
					while(threadMetaData && ((line=br.readLine())!=null) && line.length()>0) {
						
						threadState = true;
						
						if(line.contains(patThreadState) && threadStackPrc == true) {   //Match thread state of the application
							
							String [] stateContent = line.split(":");
							
							//thread.add(5,"Thread state = "+stateContent[1].trim()+"\n");
							tm.setThreadState(stateContent[1].trim());
							

						}
						//thread.add("Stack trace : \n");
						break;
						
					}
					
					//Stack trace for the thread
					
					while(threadState && ((line=br.readLine())!=null) && line.length()>0) {
						
						
							//thread.add(line.trim()+"\n");
						stackTrace.add(line.trim());
						
					}
					
					tm.setStackTrace(stackTrace);
					
					//Add the thread details to the thread list
					if(threadStackPrc) {
						threads.add(tm);
						threadStackPrc = false;
						threadMetaData = false;
						threadStackTrace = false;
						threadState = false;
					}
					
				} 
				
				
				
			}
			
			System.out.println("Print thread details");
			
//				threadDetails.forEach(i->{
//					//System.out.println(i.toString());
//					System.out.println("=================================");
//					i.forEach(j->{
//						if(j.length()>0) {
//							System.out.print(j);
//						}
//					});
//					System.out.println("=================================");
//
//				});
				
//				for(int i = 0;i<threadDetails.size();i++) {
//					
//					
//					System.out.println("==========================================");
//					System.out.println("Thread count : "+(i+1));
//					
//					for(int j=0;j<threadDetails.get(i).size();j++) {
//						System.out.print(threadDetails.get(i).get(j));
//					}
//					System.out.println("==========================================");
//
//				}
//				
				File output = new File("thread_dump_summary.txt");
				FileWriter fw = new FileWriter(output);
				
				
				if(threads.size()>0) {
					
					/*
					for(int i=0;i<threadDetails.size();i++) {
						
						for(int j=0;j<threadDetails.get(i).size();j++) {
							//System.out.println(gcLogLineList.get(i).get(j));

							fw.write(threadDetails.get(i).get(j));
							
							
						}
						fw.write("================================================\n");
						fw.flush();
						
						
					}*/
					for(int i=0;i<threads.size();i++) {
						
						fw.write("====================("+(i+1)+")============================\n");
						fw.write(threads.get(i).toString());
						fw.write("================================================\n");
						fw.flush();

					}
					
					System.out.println(threads.size());
				}
			
			System.out.println("end reading thread dump file");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
