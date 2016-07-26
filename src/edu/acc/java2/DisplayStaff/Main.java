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
		ArrayList<Employee> importedEmployees = new ArrayList<>();
		try( BufferedReader br = new BufferedReader(new FileReader(myFile)) ) {
			while(br.ready()) {
				String currentLine = br.readLine();
				if(currentLine.length() > 0) {
					if(currentLine.charAt(0) != '#') {
						String[] temp = currentLine.split(",");
						if(temp.length == 4 && isInt(temp[2])) 
							importedEmployees.add(new Employee(temp[0], temp[1], Integer.parseInt(temp[2]), temp[3]));
					}
				}
			}
		} catch(IOException ie) {
			System.out.println("Error: " + ie.getMessage());
			return;
		}
		if(!importedEmployees.isEmpty()) {
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