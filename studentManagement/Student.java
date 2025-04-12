package project.collegeManagementSystem.studentManagement;

public class Student extends Person {
    private String grade;
    private Subjects subjects;      //Composition: Student HAS-A Subjects
    private double pcent;
    private String result;
    private int attendance;
    private int credits;
    private String progress;

    public Student() {
        //Default constructor
    }

    //Student constructor
    public Student(int id, String name, int age, String email, String password, Subjects subjects, int attendance, int credits, double pcent, String grade, String result, String progress) {
        super(id, name, age, email, password);   //call super class constructor
        this.subjects = subjects;
        this.attendance = attendance;
        this.credits = credits;
        this.pcent = pcent;
        this.grade = grade;
        this.result = result;
        this.progress = progress;
    }

    //getters and setters
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getGrade() {
        return grade;
    }
    public void setPcent(double pcent) {
        this.pcent = pcent;
    }
    public double getPcent() {
        return pcent;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }
    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }
    public Subjects getSubjects() {
        return subjects;
    }
    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
    public int getAttendance() {
        return attendance;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public int getCredits() {
        return credits;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    public String getProgress() {
        return progress;
    }

    public void viewDetails(Student student) {
        System.out.println("\n============ YOUR SCORECARD ============\n");

        // Print table header
        System.out.println("+-----+----------------------+-----+-------+--------+--------+--------+--------+--------+");
        System.out.printf("| %-3s | %-20s | %-3s | %-5s | %-6s | %-6s | %-6s | %-6s | %-6s |%n",
                "ID", "NAME", "AGE", "GRADE", "PHY", "CHEM", "MATH", "BIO", "ENG");
        System.out.println("+-----+----------------------+-----+-------+--------+--------+--------+--------+--------+");

        // Print student data
        System.out.printf("| %-3d | %-20s | %-3d | %-5s | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f |%n",
                student.getId(), student.getName(), student.getAge(),
                student.getGrade(), student.getSubjects().getPhy(),
                student.getSubjects().getChem(), student.getSubjects().getMath(),
                student.getSubjects().getBio(), student.getSubjects().getEng());

        // Print table footer
        System.out.println("+-----+----------------------+-----+-------+--------+--------+--------+--------+--------+");
    }

    //Authentication Method
    public static Student validateStudent(StudentManager manager, String enteredEmail, String enteredPassword) throws NullPointerException {

        Student[] students = manager.getStudents();     //get students[] records
        int count = manager.getCount();

        for (int i = 0; i < count; ++i) {
            if (students[i] != null && students[i].getEmail().equalsIgnoreCase(enteredEmail) && students[i].getPassword().equalsIgnoreCase(enteredPassword)) {
                return students[i];
            }
        }
        return null;   //No match found
    }

    //override toString() method to return students details
    public String toString() {
        return "Details: [id = "+ getId() +", name = "+ getName() +", age = "+ getAge() +", grade = "+ grade +",";
    }
}
