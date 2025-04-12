package project.collegeManagementSystem.facultyManagement;

import java.util.Scanner;

public class FacultyAdminInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n========= FACULTY ADMIN INTERFACE =========");
        System.out.println("\n>> Login To get Access:");

        //Create object of FacultyAdmin class
        FacultyAdmin facultyAdmin = new FacultyAdmin();

        boolean isAuthorized = false;
        do {
            System.out.print("\nEnter username(facultyAdmin@gmail.com): ");
            String enteredName = sc.nextLine();
            System.out.print("Enter password(12345): ");
            String enteredPass = sc.nextLine();

            isAuthorized = facultyAdmin.validateAdmin(enteredName, enteredPass);
            // Call Authentication method
            if (isAuthorized) {
                System.out.println("\n>> Login Successful!");
                System.out.println("\nWelcome, "+ facultyAdmin.getName() +"!");

                //create instance of FacultyManager
                FacultyManager manager = new FacultyManager();
                manager.operations(manager);
            } else {
                System.out.println("\n>> Invalid Email or Password. Access Denied!");
            }
        } while (!isAuthorized);
    }
}
