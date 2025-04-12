package project.collegeManagementSystem.facultyManagement;

import project.collegeManagementSystem.studentManagement.Person;

public class FacultyAdmin extends Person {
    public FacultyAdmin() {
        super("Ajinkya Dawane", "facultyAdmin@gmail.com", "12345");     //call super constructor
    }
    //Authentication Method
    public boolean validateAdmin(String enteredEmail, String enteredPassword) {
        return this.email.equalsIgnoreCase(enteredEmail) && this.password.equalsIgnoreCase(enteredPassword);
    }
}
