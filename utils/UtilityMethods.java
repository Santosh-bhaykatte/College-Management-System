package project.collegeManagementSystem.utils;

public class UtilityMethods {
    //Method to handle invalid age
    public void checkStudentAge(int age) throws InvalidStudentAgeException {
        if (age <= 17) {
            throw new InvalidStudentAgeException(">> Error: Age can not be less than 18");
        } else if (age > 25) {
            throw new InvalidStudentAgeException(">> Error: Age can not be greater than 25");
        }
    }
    public void checkFacultyAge(int age) throws InvalidFacultyAgeException {
        if (age <= 21) {
            throw new InvalidFacultyAgeException(">> Error: Age can not be less than 21");
        } else if (age > 100) {
            throw new InvalidFacultyAgeException(">> Error: Age can not be greater than 100");
        }
    }
    //Method to handle invalid marks
    public void checkMarks(double marks) throws InvalidMarksException {
        if ((marks < 0)) {
            throw new InvalidMarksException(">> Error: Marks can not be negative");
        } else if ((marks > 100)) {
            throw new InvalidMarksException(">> Error: Marks can not be greater than hundread");
        }
    }
    //Method to handle no records found
    public boolean noRecordsFound(int count) {
        if (count == 0) {
            System.out.println(">> No records found!");
            return true;
        }
        return false;
    }
}
