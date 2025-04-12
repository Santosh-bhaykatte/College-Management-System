package project.collegeManagementSystem;

import project.collegeManagementSystem.studentManagement.StudentAdminInterface;
import project.collegeManagementSystem.facultyManagement.FacultyAdminInterface;
import project.collegeManagementSystem.studentManagement.StudentInterface;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("\n------------------------------------------------");
            System.out.println("======== COLLEGE MANAGEMENT SYSTEM ========");
            System.out.println("\n1. Student Admin Interface\n2. Faculty Admin Interface\n3. Student Interface\n4. Exit");
            System.out.print("\nEnter Option: ");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    StudentAdminInterface.main(null);
                    break;
                case 2:
                    FacultyAdminInterface.main(null);
                    break;
                case 3:
                    StudentInterface.main(null);
                    break;
                case 4:
                    System.out.println("\n>> Exiting Application..");
                    break;      //Exits switch then, loop condition breaks out
                default:
                    System.out.println("\n>> Invalid Option");
            }
        } while (op != 4);
    }
}
