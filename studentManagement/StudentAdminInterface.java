package core_Java.collegeManagementSystem.studentManagement;

import java.util.Scanner;

public class StudentAdminInterface {
    public void startStudentAdminInterface() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n========= STUDENT ADMIN INTERFACE =========");
        System.out.println("\n>> Login To Get Access:");

        //Create instance of StudentAdmin class
        StudentAdmin studentAdmin = new StudentAdmin();

        boolean isAuthorized = false;
        do {
            System.out.print("\nEnter username(studentAdmin@gmail.com): ");
            String enteredName = sc.nextLine();
            System.out.print("Enter password(12345): ");
            String enteredPass = sc.nextLine();

            // Call Authentication method
            isAuthorized = studentAdmin.validateAdmin(enteredName, enteredPass);
            if (isAuthorized) {
                System.out.println("\n>> Login Successful!");
                System.out.println("\nWelcome, "+ studentAdmin.getName() +"!");

                //Create StudentManager object
                StudentManager manager = new StudentManager();
                manager.operations(manager);    //start student operations
            } else {
                System.out.println("\n>> Invalid Email or Password. Access Denied!");
            }
        } while (!isAuthorized);
    }
}
