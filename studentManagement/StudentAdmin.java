package core_Java.collegeManagementSystem.studentManagement;

public class StudentAdmin extends Person {
    public StudentAdmin() {
        super("Santosh Bhaykatte", "studentAdmin@gmail.com", "12345");     //call super constructor
    }
    //Authentication Method
    public boolean validateAdmin(String enteredEmail, String enteredPassword) {
        return this.email.equalsIgnoreCase(enteredEmail) && this.password.equalsIgnoreCase(enteredPassword);
    }
}
