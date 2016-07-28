package edu.acc.java2.DisplayStaff;

public class Employee implements Comparable<Employee> {
	private String lastName;
	private String firstName;
	private int employeeNumber;
	private String jobTitle;
	
	public Employee(String lastName, String firstName, int employeeNumber, String jobTitle) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.employeeNumber = employeeNumber;
		this.jobTitle = jobTitle;
	}
	
	@Override
	public String toString() {
		return String.format("Employee[name:%s %s, id:%d, title:%s]",
						firstName, lastName, employeeNumber, jobTitle);
	}
	
	@Override
	public int compareTo(Employee other) {
		if( ( this.employeeNumber % 100 ) > ( other.employeeNumber % 100 ) )
			return -1;
		else if( ( this.employeeNumber % 100 ) < ( other.employeeNumber % 100) )
			return 1;
		else {
			int thisTemp = this.employeeNumber - (this.employeeNumber % 100);
			int otherTemp = other.employeeNumber - (other.employeeNumber % 100);
			if ( thisTemp > otherTemp )
				return -1;
			else if ( thisTemp < otherTemp )
				return 1;
			else
				return 0;
		}
	}
}