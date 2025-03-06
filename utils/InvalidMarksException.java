package core_Java.collegeManagementSystem.utils;

public class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);     //Call super class constructor
    }
    @Override
    public String getMessage() {
        return super.getMessage();  //Call super class method
    }
}
