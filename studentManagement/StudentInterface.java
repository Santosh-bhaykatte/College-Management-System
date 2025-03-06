package core_Java.collegeManagementSystem.studentManagement;

import java.util.Scanner;

public class StudentInterface {
    public void startStudentInterface() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n========= STUDENT INTERFACE =========");


        //Create StudentManager instance
        StudentManager manager = new StudentManager();

        if (manager.getCount() == 0) {
            // No Student records in the system
            System.out.println("\n>> No Student Records Exist!");
            return;
        }

        System.out.println("\n>> Login To View Your Scorecard:");

        Student student;
        do {
            System.out.print("\nEnter username(studentname@gmail.com): ");
            String enteredName = sc.nextLine();
            System.out.print("Enter password(studentname@123): ");
            String enteredPass = sc.nextLine();

            // Call Authentication method
            student = Student.validateStudent(manager, enteredName, enteredPass);   //return an instance of Student

            if (student != null) {
                System.out.println("\n>> Login Successful!");
                System.out.println("\nWelcome, "+ student.getName() +"!");

                student.viewDetails(student);   //Call viewDetails()
                System.out.println("Attendace: "+ student.getAttendance());
                System.out.println("Percentage: "+ student.getPcent());
                System.out.println("Result: "+ student.getResult());
                System.out.println("Credits: "+ student.getCredits());
                System.out.println("Progress: "+ student.getProgress());

            } else {
                System.out.println("\n>> Invalid Email OR Password. Access Denied!");
            }
        } while (student == null);
    }
}
