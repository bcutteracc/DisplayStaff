package edu.acc.java2.DisplayStaff;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("edu.acc.java2.DisplayStaff.Main usage: <String> file path");
			return;
		}
		File myFile = new File(args[0]);
		if(!myFile.exists()) {
			System.out.printf("File '%s' does not exist", myFile.getPath());
			return;
		}
		
		int lineCount = 1;
		int employeeRecords = 0;
		int errorCount = 0;
		ArrayList<Employee> importedEmployees = new ArrayList<>();
		try( BufferedReader br = new BufferedReader(new FileReader(myFile)) ) {
			while(br.ready()) {
				String currentLine = br.readLine();
				if(currentLine.length() > 0) {
					if(currentLine.charAt(0) != '#') {
						String[] temp = currentLine.split(",");
						if(temp.length == 4 && isInt(temp[2])) {
							importedEmployees.add(new Employee(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3]));
							employeeRecords++;
						}
						else if( temp.length != 4 || !isInt(temp[2]) ) {
								String errorMessage;
								String correctMessage;
								if(temp.length != 4) {
									errorMessage = String.format("%d", temp.length);
									correctMessage = "4 fields";
								} else {
									errorMessage = temp[2];
									correctMessage = "integer ID";
								}
								System.out.printf("Line %d is invalid.  Expected %s, found %s.\n", lineCount, correctMessage, errorMessage);
								errorCount++;
							}
						 else {
							System.out.printf("Unknown error on line %d, rendered invalid\n", lineCount);
							errorCount++;
						}		
					}
				}
				lineCount++;
			}
		} catch(IOException ie) {
			System.out.println("Error: " + ie.getMessage());
			return;
		}
		if(errorCount > 0) 
			System.out.printf("\nSkipped %d bad recods.\n\n", errorCount);
		if(!importedEmployees.isEmpty()) {
			Collections.sort(importedEmployees);
			for(Employee employee:importedEmployees)
				System.out.println(employee);
		}
				
	}
	
	public static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}