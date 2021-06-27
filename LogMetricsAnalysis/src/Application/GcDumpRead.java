package Application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public void readFileSelect(int gc) {
		
		if(gc == 1) {
			readFileParallelGC();

		}else if(gc == 2) {
			readFileParallelGC();
			readFileG1GC();

		}
	}
	
	public void readFileParallelGC() {
		
		File file = new File(this.filePath);
		
		String cmdLineFlags = "CommandLine flags";
		String line = "";
		int heapDataStart = 0;
		int heapDataEnd = 0;
		int indexOfOpenPar,indexOfClosePar,tmpStart,tmpEnd,lineEnd = 0,lineNum=0;
		
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
	
	
	public void readFileG1GC() {
		
		File file = new File(this.filePath);
		
		String gcStart = "gc,start";
		String gcHeap = "gc,heap";
		String gcMetaSpace = "gc,metaspace";
		String gc = "gc";
		
		String gcCpu = "gc,cpu";
		
		String line = "";
		int heapDataStart = 0;
		int heapDataEnd = 0;
		int indexOfOpenPar,indexOfClosePar,tmpStart,tmpEnd,lineEnd = 0,lineNum=0;
		
		ArrayList<ArrayList<String>> gcLogLineList = new ArrayList<ArrayList<String>>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine())!=null) {
				
			  indexOfOpenPar = 0;
			  indexOfClosePar = 0;
			  tmpStart = 0;
			  tmpEnd = 0;
			  lineEnd++;

				if(line.contains(gcStart)) {
					
					indexOfOpenPar = line.indexOf('[');
					indexOfClosePar = line.indexOf(']');
					
					ArrayList<String> gcLogLine = new ArrayList<String>();
					lineNum++;
					gcLogLine.add("Time stamp : "+line.substring(indexOfOpenPar+1,indexOfClosePar)+"\n");
					
					indexOfOpenPar = line.lastIndexOf('(');
					indexOfClosePar = line.lastIndexOf(')');
					
					gcLogLine.add("Reason of GC : "+line.substring(indexOfOpenPar+1,indexOfClosePar)+"\n");
					
					while((line=br.readLine())!=null) {
						
						 lineEnd++;
						if(line.contains(gcHeap)) {
							
							indexOfClosePar = line.lastIndexOf(']');
							
							gcLogLine.add("GC heap : "+line.substring(indexOfClosePar+1,line.length())+"\n");
							
						}
						
						if(line.contains(gcMetaSpace)) {
							
							indexOfClosePar = line.lastIndexOf(']');
							
							gcLogLine.add("GC Meta space : "+line.substring(indexOfClosePar+1,line.length())+"\n");
							
						}
						
						if(line.contains(gc)) {
							
							tmpStart = line.indexOf("gc");
							
							if(tmpStart>0) {
								tmpStart = tmpStart + 2;
								
								if(!(line.charAt(tmpStart)==',')) {
									
									indexOfClosePar = line.lastIndexOf(']');
									
									gcLogLine.add("Young memory allocation & Time spent for gc: "+line.substring(indexOfClosePar+1,line.length())+"\n");
								}
							}
							
							
						}
						
						if(line.contains(gcCpu)) {
							
							indexOfClosePar = line.lastIndexOf(']');
							
							gcLogLine.add("Collection time and cpu usage : "+line.substring(indexOfClosePar+1,line.length())+"\n");
							gcLogLine.add("============================("+lineNum+")===============================\n");
							gcLogLineList.add(gcLogLine);
							
							break;
						}
						
						
						
					}
					
					
					
				}
			}
			
			System.out.println("read lines "+lineEnd);
			
			
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
