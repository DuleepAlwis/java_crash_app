package Application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GcDumpRead {

	private String filePath;
	
	public GcDumpRead() {
		
	}
	
	public GcDumpRead(String file) {
		
		this.filePath = file;
	}
	
	public void readFile() {
		
		File file = new File(this.filePath);
		
		String cmdLineFlags = "CommandLine flags";
		String line = "";
		int heapDataStart = 0;
		int heapDataEnd = 0;
		int indexOfOpenPar,indexOfClosePar,tmpStart,tmpEnd,lineEnd,lineNum=0;
		
		ArrayList<ArrayList<String>> gcLogLineList = new ArrayList<ArrayList<String>>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((line=br.readLine())!= null) {
				
				if(line.contains(cmdLineFlags)) {

					
					
					while((line=br.readLine()) != null) {
						
						heapDataStart = 0;
						heapDataEnd = 0;
						indexOfOpenPar = 0;
						indexOfClosePar = 0;
						tmpStart = 0;
						tmpEnd = 0;
						lineEnd = 0;
						lineNum++;
						
						ArrayList<String> gcLogLine = new ArrayList<String>();
									
						heapDataStart = line.indexOf('[');
						heapDataEnd = line.lastIndexOf('[');   
						lineEnd = line.length();
						
						if(line.equalsIgnoreCase("Heap")) {
							//System.out.println(line);
							break;
						}
						
						if(heapDataStart>0) {
							
							gcLogLine.add("======================"+lineNum+"==============================\n");
							gcLogLine.add("Time stamp : "+line.substring(0,heapDataStart-2)+"\n");    //Read the gc collected time
							
							if(heapDataEnd>2) {
								
								gcLogLine.add("Collection time and cpu usage : "+line.substring(heapDataEnd,lineEnd)+"\n");

								String heapData = line.substring(heapDataStart+1,heapDataEnd-2);   //Get the string with in the []
								String [] heapDataArr = heapData.split(",");   //
								
								if(heapDataArr.length>0) {
									
									gcLogLine.add("Time spent for gc : "+heapDataArr[heapDataArr.length-1]+"\n");
									
									for(int i =0;i<heapDataArr.length;i++) {
										
										int tmp = 0;
										String memoryArea = "";
										
										if(i==0) {
											tmp = heapDataArr[i].indexOf("GC");
											
											if(tmp>0) {
												gcLogLine.add("Type of GC : "+heapDataArr[i].substring(0,tmp+2)+"\n");
											}
											
											 indexOfOpenPar = heapDataArr[i].indexOf('(');
											 indexOfClosePar = heapDataArr[i].indexOf(')');
											
											 if(indexOfOpenPar >0 && indexOfClosePar>0) {
													gcLogLine.add("Reason for GC : "+heapDataArr[i].substring(indexOfOpenPar+1,indexOfClosePar)+"\n"); 
											 }
											 tmpStart = heapDataArr[i].indexOf("PSYoungGen");
											
											 if(tmpStart>0) {
												 
												 tmpEnd = heapDataArr[i].indexOf(']',tmpStart);
												 //System.out.println("a"+tmpEnd);
												 if(tmpEnd>0) {
													 memoryArea = heapDataArr[i].substring(tmpStart,tmpEnd);
													 gcLogLine.add(memoryArea+"\n");
												 }
												 tmpStart = 0;

											 }
											 
											 tmpStart = heapDataArr[i].indexOf("ParOldGen");

											 if(tmpStart>0) {
												 
												 tmpEnd = heapDataArr[i].indexOf(']',tmpStart);
												 
												 
												 if(tmpEnd>0) {
													 memoryArea = heapDataArr[i].substring(tmpStart,tmpEnd);
													 gcLogLine.add(memoryArea+"\n");
												 }
												 tmpStart = 0;
											 }
											 
											 
											 tmpStart = heapDataArr[i].indexOf("Metaspace");
											 //System.out.println(tmpStart);
											 if(tmpStart>0) {
												 
												 tmpEnd = heapDataArr[i].indexOf(']',tmpStart);
												 System.out.println(tmpStart + " "+tmpEnd);

												 if(tmpEnd>0) {
													 memoryArea = heapDataArr[i].substring(tmpStart,tmpEnd);
													 gcLogLine.add(memoryArea+"\n");
												 }
												 tmpStart = 0;
											 }
											gcLogLine.add("======================================");
											gcLogLineList.add(gcLogLine);
										}
										
										
										
									}
								}
								
							}
						}

					}
				}
			}
			
			File output = new File("gc_collection_summary.txt");
			FileWriter fw = new FileWriter(output);
			
			
			if(gcLogLineList.size()>0) {
				
				for(int i=0;i<gcLogLineList.size();i++) {
					
					for(int j=0;j<gcLogLineList.get(i).size();j++) {
						//System.out.println(gcLogLineList.get(i).get(j));

						fw.write(gcLogLineList.get(i).get(j));
						fw.flush();
					}
					
					
				}
				
				System.out.println(gcLogLineList.size());
			}
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
