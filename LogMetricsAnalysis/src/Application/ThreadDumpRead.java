package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreadDumpRead {

	private String filePath;
	
	public ThreadDumpRead() {
		
	}
	
	public ThreadDumpRead(String filePath) {
		
		this.filePath = filePath;
	}
	
	public void readFile() {
		
		File file = new File(this.filePath);
		ArrayList<ArrayList<String>> threadDetails = new ArrayList<ArrayList<String>>();
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
					ArrayList<String> thread = new ArrayList<String>();
					
					//System.out.println(line);

					//System.out.println(line.substring(line.indexOf("\"")+1,line.lastIndexOf("\"")));
					thread.add(0,"Thread name = "+line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""))+"\n");
					String [] content = line.substring(line.lastIndexOf("\"")+1).split(" ");
					
					for(int i = 0;i<content.length;i++) {
						
						if(content[i].matches(patThreadId)) {
							thread.add(1,"Thread id = "+content[i]+"\n");
						}else if(content[i].matches(patThreadPrio)) { //Match thread priority
							thread.add(1,content[i]+"\n");
							isJvmThread = false;
						}else if(content[i].matches(patThreadAddress)) { //Match thread address 
							thread.add(2,content[i]+"\n");
						}else if(content[i].matches(patThreadStackAddress)) { //Match thread stack address
							thread.add(3,"Thread stack address = "+content[i]+"\n");
						}else if(content[i].matches(patOSThreadId) && (i+1)<content.length) { //Matches the state of the JVM only threads.
							if(isJvmThread){
								thread.add(4,"Thread state = "+content[i+1]+"\n");
								
							}
						}
						else {
							thread.add("");
						}
						thread.add("");

					}
					isJvmThread = true;

					
					threadMetaData = true;
					
					while(threadMetaData && ((line=br.readLine())!=null) && line.length()>0) {
						
						threadState = true;
						
						if(line.contains(patThreadState) && threadStackPrc == true) {   //Match thread state of the application
							
							String [] stateContent = line.split(":");
							
							thread.add(5,"Thread state = "+stateContent[1].trim()+"\n");
							
							

						}
						thread.add("Stack trace : \n");
						break;
						
					}
					
					//Stack trace for the thread
					
					while(threadState && ((line=br.readLine())!=null) && line.length()>0) {
						
						
							thread.add(line.trim()+"\n");
						
					}
					
					//Add the thread details to the thread list
					if(threadStackPrc) {
						threadDetails.add(thread);
						threadStackPrc = false;
						threadMetaData = false;
						threadStackTrace = false;
						threadState = false;
					}
					
				} 
				
				
				
			}
			
			System.out.println("Print thread details");
			
				threadDetails.forEach(i->{
					//System.out.println(i.toString());
					System.out.println("=================================");
					i.forEach(j->{
						if(j.length()>0) {
							System.out.print(j);
						}
					});
					System.out.println("=================================");

				});
				
				for(int i = 0;i<threadDetails.size();i++) {
					
					
					System.out.println("==========================================");
					System.out.println("Thread count : "+(i+1));
					
					for(int j=0;j<threadDetails.get(i).size();j++) {
						System.out.print(threadDetails.get(i).get(j));
					}
					System.out.println("==========================================");

				}
			
			System.out.println("end reading thread dump file");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
