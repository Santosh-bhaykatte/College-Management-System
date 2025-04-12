package core_Java.collegeManagementSystem.utils;

import core_Java.collegeManagementSystem.studentManagement.Student;
import core_Java.collegeManagementSystem.studentManagement.StudentManager;
import core_Java.collegeManagementSystem.studentManagement.Subjects;

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
