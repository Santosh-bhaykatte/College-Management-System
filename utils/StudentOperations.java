package project.collegeManagementSystem.utils;

import project.collegeManagementSystem.studentManagement.Student;
import project.collegeManagementSystem.studentManagement.StudentManager;
import project.collegeManagementSystem.studentManagement.Subjects;

public interface StudentOperations extends UtilityOperations {
    public void addStudent(Student student);        //Add Student

    //overloaded methods
    public int updateStudent(int id, String name, int age, Subjects subjects);      //Update All details
    public int updateStudent(int id, double marks, String value);       //Update Marks
    public int updateStudentName(int id, String name);     //Update Name
    public void updateStudentAge(int id, int age);          //Update Age
    public void deleteStudent(int id);      //Delete Student record
    public void viewAllStudents();      //View All Records
    public void operations(StudentManager manager);     //Student Operations
}
