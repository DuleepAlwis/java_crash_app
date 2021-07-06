package Application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Metric.GcG1Metric;
import Metric.GcMetric;

public class GcDumpRead {

	private String filePath;
	
	public GcDumpRead() {
		 
	}
	
	public GcDumpRead(String file) {
		
		this.filePath = file;
	}
	
	public void readFileSelect() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(this.filePath)));
			String line = "";
			
			while((line = br.readLine())!=null) {
				
				if(line.contains("1.8.0_261-b12")) {
					readFileParallelGC();
				}else if(line.contains("Using G1")) {
					readFileG1GC();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		/*if(gc == 1) {
			readFileParallelGC();

		}else if(gc == 2) {
			readFileG1GC();

		}*/
	}
	
	public void readFileParallelGC() {
		
		File file = new File(this.filePath);
		
		String cmdLineFlags = "CommandLine flags";
		String line = "";
		int heapDataStart = 0;
		int heapDataEnd = 0;
		int indexOfOpenPar,indexOfClosePar,tmpStart,tmpEnd,lineEnd = 0,lineNum=0;
		List<GcMetric> gcLogLines = new ArrayList<GcMetric>();
		//ArrayList<ArrayList<String>> gcLogLineList = new ArrayList<ArrayList<String>>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((line=br.readLine())!= null) {
				
				GcMetric gc = new GcMetric();
				
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
						
						//ArrayList<String> gcLogLine = new ArrayList<String>();
									
						heapDataStart = line.indexOf('[');
						heapDataEnd = line.lastIndexOf('[');   
						lineEnd = line.length();
						
						if(line.equalsIgnoreCase("Heap")) {
							//System.out.println(line);
							break;
						}
						
						if(heapDataStart>0) {
							
							//gcLogLine.add("======================"+lineNum+"==============================\n");
							//gcLogLine.add("Time stamp : "+line.substring(0,heapDataStart-2)+"\n");    //Read the gc collected time
							gc.setTimeStamp(line.substring(0,heapDataStart-2));
							
							if(heapDataEnd>2) {
								
								//gcLogLine.add("Collection time and cpu usage : "+line.substring(heapDataEnd,lineEnd)+"\n");
								gc.setCollCpuUsage(line.substring(heapDataEnd,lineEnd));
								String heapData = line.substring(heapDataStart+1,heapDataEnd-2);   //Get the string with in the []
								String [] heapDataArr = heapData.split(",");   //
								
								if(heapDataArr.length>0) {
									
									//gcLogLine.add("Time spent for gc : "+heapDataArr[heapDataArr.length-1]+"\n");
									gc.setTimeSpent(heapDataArr[heapDataArr.length-1]);
									
									for(int i =0;i<heapDataArr.length;i++) {
										
										int tmp = 0;
										String memoryArea = "";
										
										if(i==0) {
											tmp = heapDataArr[i].indexOf("GC");
											
											if(tmp>0) {
												//gcLogLine.add("Type of GC : "+heapDataArr[i].substring(0,tmp+2)+"\n");
												gc.setTypeOfGc(heapDataArr[i].substring(0,tmp+2));
											}
											
											 indexOfOpenPar = heapDataArr[i].indexOf('(');
											 indexOfClosePar = heapDataArr[i].indexOf(')');
											
											 if(indexOfOpenPar >0 && indexOfClosePar>0) {
													//gcLogLine.add("Reason for GC : "+heapDataArr[i].substring(indexOfOpenPar+1,indexOfClosePar)+"\n"); 
												 	gc.setReasonForGc(heapDataArr[i].substring(indexOfOpenPar+1,indexOfClosePar));
											 }
											 tmpStart = heapDataArr[i].indexOf("PSYoungGen");
											
											 if(tmpStart>0) {
												 
												 tmpEnd = heapDataArr[i].indexOf(']',tmpStart);
												 //System.out.println("a"+tmpEnd);
												 if(tmpEnd>0) {
													 memoryArea = heapDataArr[i].substring(tmpStart,tmpEnd);
													 //gcLogLine.add(memoryArea+"\n");
													 gc.setMemoryAllocation_1(memoryArea);
												 }
												 tmpStart = 0;

											 }
											 
											 tmpStart = heapDataArr[i].indexOf("ParOldGen");

											 if(tmpStart>0) {
												 
												 tmpEnd = heapDataArr[i].indexOf(']',tmpStart);
												 
												 
												 if(tmpEnd>0) {
													 memoryArea = heapDataArr[i].substring(tmpStart,tmpEnd);
													 //gcLogLine.add(memoryArea+"\n");
													 gc.setMemoryAllocation_2(memoryArea);
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
													 //gcLogLine.add(memoryArea+"\n");
													 gc.setMemoryAllocation_3(memoryArea);
												 }
												 tmpStart = 0;
											 }
											gcLogLines.add(gc);
											//gcLogLineList.add(gcLogLine);
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
			
			
			/*if(gcLogLineList.size()>0) {
				
				for(int i=0;i<gcLogLineList.size();i++) {
					
					for(int j=0;j<gcLogLineList.get(i).size();j++) {
						//System.out.println(gcLogLineList.get(i).get(j));

						fw.write(gcLogLineList.get(i).get(j));
						fw.flush();
					}
					
					
				}
				
				System.out.println(gcLogLineList.size());
			}*/
			
			
			if(gcLogLines.size()>0) {
				
				for(int i=0;i<gcLogLines.size();i++) {
					
				

						fw.write(gcLogLines.get(i).toString());
						fw.flush();
					
					
					
				}
				
				System.out.println(gcLogLines.size());
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
		ArrayList<GcG1Metric> gcLogLines = new ArrayList<GcG1Metric>();
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
					
					
					GcG1Metric gcMet = new GcG1Metric();
					lineNum++;
					//gcLogLine.add("Time stamp : "+line.substring(indexOfOpenPar+1,indexOfClosePar)+"\n");
					gcMet.setTimeStamp(line.substring(indexOfOpenPar+1,indexOfClosePar));
					
					indexOfOpenPar = line.lastIndexOf('(');
					indexOfClosePar = line.lastIndexOf(')');
					
					//gcLogLine.add("Reason of GC : "+line.substring(indexOfOpenPar+1,indexOfClosePar)+"\n");
					gcMet.setReasonForGc(line.substring(indexOfOpenPar+1,indexOfClosePar));
					
					while((line=br.readLine())!=null) {
						
						
						
						 lineEnd++;
						if(line.contains(gcHeap)) {
							
							indexOfClosePar = line.lastIndexOf(']');
							
							//gcLogLine.add("GC heap : "+line.substring(indexOfClosePar+1,line.length())+"\n");
							gcMet.setTypeOfGc(line.substring(indexOfClosePar+1,line.length()));
							
						}
						
						if(line.contains(gcMetaSpace)) {
							
							indexOfClosePar = line.lastIndexOf(']');
							
							//gcLogLine.add("GC Meta space : "+line.substring(indexOfClosePar+1,line.length())+"\n");
							gcMet.setMemoryAllocation_3(line.substring(indexOfClosePar+1,line.length()));
						}
						
					/*	if(line.contains(gc)) {
							
							tmpStart = line.indexOf("gc");
							
							if(tmpStart>0) {
								tmpStart = tmpStart + 2;
								
								if(!(line.charAt(tmpStart)==',')) {
									
									//indexOfClosePar = line.lastIndexOf(']');
									
									//gcLogLine.add("Young memory allocation & Time spent for gc: "+line.substring(indexOfClosePar+1,line.length())+"\n");
									//gcMet.setMemoryAllocation_1(line.substring(indexOfClosePar+1,line.length()));
									indexOfClosePar = line.lastIndexOf(')');
									gcMet.setTimeSpent(line.substring(indexOfClosePar+1,line.length()));
								}
							}
							
							
						}*/
						
						if(line.contains(gcCpu)) {
							
							indexOfClosePar = line.lastIndexOf(']');
							String tmpLine = line.substring(indexOfClosePar+1,line.length());
							indexOfClosePar = tmpLine.lastIndexOf("User");
							tmpLine = "["+tmpLine.substring(indexOfClosePar,tmpLine.length())+"]";
							//gcLogLine.add("Collection time and cpu usage : "+line.substring(indexOfClosePar+1,line.length())+"\n");
							//gcLogLine.add("============================("+lineNum+")===============================\n");
							//gcLogLineList.add(gcLogLine);
							gcMet.setCollCpuUsage(tmpLine);
							gcLogLines.add(gcMet);
							break;
						}
						
						
						
					}
					
					
					
				}
			}
			
			System.out.println("read lines "+lineEnd);
			
			
			File output = new File("gc_collection_g1_summary.txt");
			FileWriter fw = new FileWriter(output);
			
			
		/*	if(gcLogLineList.size()>0) {
				
				for(int i=0;i<gcLogLineList.size();i++) {
					
					for(int j=0;j<gcLogLineList.get(i).size();j++) {
						//System.out.println(gcLogLineList.get(i).get(j));
						fw.write("====================("+(i+1)+")============================\n");
						fw.write(gcLogLineList.get(i).get(j));
						fw.write("================================================\n");

						fw.flush();
					}
					
					
				}
				
				System.out.println(gcLogLineList.size());
			}*/
			
			/*if(gcLogLines.size()>0) {
				
				gcLogLines.forEach(i->{
					System.out.println(i.toString());
				});
			}*/
			
			for(int i=0;i<gcLogLines.size();i++) {
				fw.write("====================("+(i+1)+")============================\n");
				fw.write(gcLogLines.get(i).toString());
				fw.write("================================================\n");
			}
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		
		
	}
}
