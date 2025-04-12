package project.collegeManagementSystem.utils;

import project.collegeManagementSystem.facultyManagement.Faculty;
import project.collegeManagementSystem.facultyManagement.FacultyManager;

public interface FacultyOperations extends UtilityOperations {
    public void addFaculty(Faculty faculty);    //Add faculty
    public void updateFaculty(int id, String name, int age, int yearsOfExp);    //Update Faculty All Details
    public void updateFacultyName(int id, String name);     //Update faculty name
    public void updateFacultyAge(int id, int age);      //Update faculty age
    public void updateFacultyYearsOfExp(int id, int yearsOfExp);    //Update Years_Of_Exp
    public void deleteFaculty(int id);      //Delete Faculty
    public void viewAllFaculties();     //View All faculties
    public void operations(FacultyManager manager);
}
