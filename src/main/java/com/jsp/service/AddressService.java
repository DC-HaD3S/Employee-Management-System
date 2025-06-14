package com.jsp.service;

import java.util.Scanner;
import com.jsp.dao.AddressDao;
import com.jsp.dao.EmployeeDao;
import com.jsp.entity.Address;
import com.jsp.entity.Employee;

public class AddressService {
    
    private static Scanner sc = new Scanner(System.in);
    
    public static void addressService() {
        while (true) {
            System.out.println("Enter 1 To Add New Address");
            System.out.println("Enter 2 To Fetch The Details Of The Existing Address");
            System.out.println("Enter 3 To Update The Existing Address Details");
            System.out.println("Enter 4 To Delete The Existing Address");
            System.out.println("Enter 5 To Exit");
            int choice = sc.nextInt();
            System.out.println("**********************");
            
            switch (choice) {
                case 1:
                    addNewAddress();
                    break;
                case 2:
                    getAddressById();
                    break;
                case 3:
                    updateAddress();
                    break;
                case 4:
                    deleteAddress();
                    break;
                case 5:
                    System.out.println("Exiting Address Service");
                    return;
                default:
                    System.out.println("Invalid option, please try again");
            }
        }
    }
    
    public static void addNewAddress() {
        System.out.println("Enter The Employee Id To Associate With New Address:");
        int empId = sc.nextInt();
        Employee employee = EmployeeDao.getEmpById(empId);
        
        if (employee != null) {
            if (employee.getAddress() != null) {
                System.out.println("Employee already has an address. Use Update option to modify.");
                return;
            }
            sc.nextLine();
            System.out.println("Enter The Street:");
            String street = sc.nextLine();
            System.out.println("Enter The City:");
            String city = sc.nextLine();
            System.out.println("Enter The State:");
            String state = sc.nextLine();
            System.out.println("Enter The Country:");
            String country = sc.nextLine();
            System.out.println("Enter The Pincode:");
            int pincode = sc.nextInt();
            
            Address address = new Address(street, city, state, country, pincode, employee);
            AddressDao.addNewAddress(address);
            System.out.println("Address added successfully for Employee ID: " + empId);
        } else {
            System.out.println("Employee with ID: " + empId + " does not exist");
        }
    }
    
    public static void getAddressById() {
        System.out.println("Enter The Employee Id Whose Address Needs To Be Fetched:");
        int id = sc.nextInt();
        
        Employee employee = EmployeeDao.getEmpById(id);
        if (employee != null && employee.getAddress() != null) {
            Address address = employee.getAddress();
            System.out.println("Employee Address is:");
            System.out.println("Address Id is: " + address.getId());
            System.out.println("Street is: " + address.getStreet());
            System.out.println("City is: " + address.getCity());
            System.out.println("State is: " + address.getState());
            System.out.println("Country is: " + address.getCountry());
            System.out.println("Pincode is: " + address.getPincode());
        } else {
            System.out.println("Employee with ID: " + id + " does not exist or has no address");
        }
    }
    
    public static void updateAddress() {
        System.out.println("Enter The Employee Id Whose Address Needs To Be Updated:");
        int id = sc.nextInt();
        Employee employee = EmployeeDao.getEmpById(id);
        
        if (employee != null && employee.getAddress() != null) {
            Address address = employee.getAddress();
            sc.nextLine();
            System.out.println("Enter The Street:");
            String street = sc.nextLine();
            System.out.println("Enter The City:");
            String city = sc.nextLine();
            System.out.println("Enter The State:");
            String state = sc.nextLine();
            System.out.println("Enter The Country:");
            String country = sc.nextLine();
            System.out.println("Enter The Pincode:");
            int pincode = sc.nextInt();
            
            address.setStreet(street);
            address.setCity(city);
            address.setState(state);
            address.setCountry(country);
            address.setPincode(pincode);
            
            AddressDao.updateAddress(address);
            System.out.println("Address updated successfully for Employee ID: " + id);
        } else {
            System.out.println("Employee with ID: " + id + " does not exist or has no address");
        }
    }
    
    public static void deleteAddress() {
        System.out.println("Enter The Employee Id Whose Address Needs To Be Deleted:");
        int id = sc.nextInt();
        Employee employee = EmployeeDao.getEmpById(id);
        
        if (employee != null && employee.getAddress() != null) {
            Address address = employee.getAddress();
            AddressDao.deleteAddress(address);
            System.out.println("Address deleted successfully for Employee ID: " + id);
        } else {
            System.out.println("Employee with ID: " + id + " does not exist or has no address");
        }
    }
}