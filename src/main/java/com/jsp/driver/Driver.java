package com.jsp.driver;

import java.util.Scanner;
import com.jsp.service.AddressService;
import com.jsp.service.DepartmentService;
import com.jsp.service.EmployeeService;
import com.jsp.service.ProjectService;

public class Driver {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("------------------------------------------------------");
		System.out.println("Enter 1 To Work With Department Related Details :");
		System.out.println("Enter 2 To Work With Employee Related Details :");
		System.out.println("Enter 3 To Work With Address Related Details :");
		System.out.println("Enter 4 To Work With Project Related Details :");
		System.out.println("Enter 5 to exit");
		
		int choice = sc.nextInt();
		System.out.println("------------------------------------------------------");

		
		if (choice == 1) {
			DepartmentService.deptService();
			main(args);
		}
		else if (choice == 2) {
			EmployeeService.empService();
			main(args);

		}
		else if(choice==3) {
			AddressService.addressService();
			main(args);

		}
		else if(choice==4) {
			ProjectService.projectService();
			main(args);

		}
		else if(choice==5) {
			System.exit(0);
		}
		else {
			System.out.println("enter correct number");
			main(args);
		}
		sc.close();
	}
}