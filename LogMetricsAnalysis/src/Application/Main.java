package Application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class Main {

	public static void main(String [] args) {
		
		Scanner sca = new Scanner(System.in);

		System.out.println("1 - Thread dump file | 2 - GC log file | 3 - PS log file\n"
				+ "4 - VMStat file | 5 - NetStat file | 6 - Dmesg file");
		System.out.print("Option :- ");
		int option = Integer.parseInt(sca.nextLine());
		
		//readBinToText(sca.nextLine());
		System.out.print("Enter file path : ");

		if(option == 1) {
			ThreadDumpRead thr = new ThreadDumpRead(sca.nextLine());
			thr.readFile();

		}else if(option == 2) {
			GcDumpRead gcdr = new GcDumpRead(sca.nextLine());
			
			System.out.println("Type of GC performed : \n"+"1: Parallel GC 2: G1GC");
			gcdr.readFileSelect(Integer.parseInt(sca.nextLine()));
			
		}else if(option == 3) {
			LinuxLog linuxLog = new LinuxLog(sca.nextLine());
			linuxLog.readPsFile();
			//linuxLog.getPs();
		}else if(option == 4){
			LinuxLog linuxLog = new LinuxLog(sca.nextLine());
			
			linuxLog.readVmStatFile();
			
		}else if(option == 5){
			LinuxLog linuxLog = new LinuxLog(sca.nextLine());
			
			linuxLog.readNetStatFile();
			
		}else if(option == 6){
			LinuxLog linuxLog = new LinuxLog(sca.nextLine());
			
			linuxLog.readDMesg();
			
		}else {
			System.out.println("Option not specified.......");
		}
		

		
	}
	
	public static void readBinToText(String file) {
		
		File input = new File(file);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
//			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//		    Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
			String line = "";
			int i = 0;
			
			while((line=br.readLine())!=null) {
				
				System.out.println("bin data\n"+line);
				
				//byte [] decodedStr = Base64.getDecoder().decode(line);
//				String encodedString = Base64.getEncoder().encodeToString(line.getBytes());
//				System.out.println("encode data "+encodedString);

//				String decodedStr = new String(Base64.getDecoder().decode(line.getBytes(StandardCharsets.UTF_8)));
//				System.out.println("char data "+decodedStr);
				i++;
				// String s1 = new String(line.getBytes(StandardCharsets.ISO_8859_1));
				String s1 = new String(line.getBytes(StandardCharsets.UTF_8));
				    byte[] b = s1.getBytes(StandardCharsets.UTF_8);
				    System.out.println("UTF-8_CHARS");
				    System.out.println(s1);
				    //System.out.println(new String(b, StandardCharsets.UTF_8));
				
				if(i>10) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	
}
