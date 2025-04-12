package project.collegeManagementSystem.studentManagement;

// abstract class for common behaviour - base class
public abstract class Person {
    private int id;
    private String name;
    private int age;
    protected String email;        //Will be System Generated for student
    protected String password;        //Will be System Generated for student

    public Person() {
        //Default constructor
    }

    public Person(String name, String email, String password) {      //For constructing StudentAdmin & FacultyAdmin Object
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Person(int id, String name, int age, String email, String password) {       //For constructing Student Object
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
    public Person(int id, String name, int age) {       //For constructing Faculty Object
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //getters and setters
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
