package Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
			ArrayList<ArrayList> values = new ArrayList<ArrayList>();
			
			while((line=br.readLine())!=null) {
				
				if(line.contains("PID")) {
					
					line = line.replaceAll("\s+", " ");
					
					String [] val = line.split(" ");
					
					for(int i=0;i<val.length;i++) {
						
						headers.add(val[i]);
						
					}
					
					while((line=br.readLine()) != null) {
						
						line = line.replaceAll("\s+", " ");
						val = line.split(" ");
						
						ArrayList<String> rows = new ArrayList<String>();
						
						for(int i=0;i<val.length;i++) {
							rows.add(val[i]);
						}
						
						
						values.add(rows);
						
						
					}
					
					headers.forEach(i->{
						System.out.print(i+"\t");
					});
					
					System.out.println();
					
					values.forEach(i->{
						i.forEach(j->{
							System.out.print(j+"\t");
						});
						System.out.println();
					});
				}
			}
			
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
}
