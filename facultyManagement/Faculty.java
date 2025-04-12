package core_Java.collegeManagementSystem.facultyManagement;

import core_Java.collegeManagementSystem.studentManagement.Person;

public class Faculty extends Person {
    private String subject;     //Once Added, cannot update
    private int yearsOfExp;
    private String qualification;     //Once Added, cannot update

    //constructor
    public Faculty(int id, String name, int age, String subject, int yearsOfExp, String qualification) {
        super(id, name, age);
        this.subject = subject;
        this.yearsOfExp = yearsOfExp;
        this.qualification = qualification;
    }

    //Getters and Setters
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getSubject() {
        return subject;
    }
    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }
    public int getYearsOfExp() {
        return yearsOfExp;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getQualification() {
        return qualification;
    }
    //override toString() method to view students details
    public String toString() {
        return "Student [id = "+ getId() +", name = "+ getName() +", age = "+ getAge() +", ";
    }
}
