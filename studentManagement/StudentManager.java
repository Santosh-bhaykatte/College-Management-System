package project.collegeManagementSystem.studentManagement;

import java.io.*;   //For File Operations

import project.collegeManagementSystem.utils.InvalidStudentAgeException;
import project.collegeManagementSystem.utils.InvalidMarksException;
import project.collegeManagementSystem.utils.StudentOperations;
import project.collegeManagementSystem.utils.UtilityMethods;

import java.util.Scanner;

//StudentManager class to manage student records
public class StudentManager extends UtilityMethods implements StudentOperations {
    private static final String FOLDER_NAME = "CollegeSystemData";
    private static final String FIlE_NAME = "students.csv";
    private static final String CSV_FILE_PATH;  //Define the CSV file path on the Desktop

    static {    //static block
        String userHome = System.getProperty("user.home");
        CSV_FILE_PATH = userHome + File.separator + "Desktop" + File.separator + FOLDER_NAME + File.separator + FIlE_NAME;
    }
    protected Student[] students;     //Array to manage students
    private static final int MAX_STUDENTS = 100;    //Maximum no. students to manage
    private int count;      //student count

    //StduentManager constructor
    public StudentManager() {
        students = new Student[MAX_STUDENTS];   //Initialize Array
        count = 0;
        loadFromFile();     //Reads file and populates students[] & sets count
    }

    //getters
    public int getCount() {
        return count;
    }
    public Student[] getStudents() {
        return students;
    }

    //===========FILE OPERATIONS============

    //Save Records To The File
    private void saveToFile() {
        File file = new File(CSV_FILE_PATH);    //Create CSV File
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        try {
            fileWriter = new FileWriter(file);
            bw = new BufferedWriter(fileWriter);

            //Write header
            bw.write("id,name,age,email,password,phy,chem,math,bio,eng,attendence,credits,percent,grade,result,progress");
            bw.newLine();

            for (int i = 0; i < count; ++i) {
                Student student = students[i];  //1. Create student from students array

                //Create CSV line using student field's
                String line = student.getId() +","+ student.getName() +","+ student.getAge() +","+ student.getEmail() +","+ student.getPassword() +","+ student.getSubjects().getPhy() +","+ student.getSubjects().getChem() +","+ student.getSubjects().getMath() +","+ student.getSubjects().getBio() +","+ student.getSubjects().getEng() +","+ student.getAttendance() +","+ student.getCredits() +","+ student.getPcent() +","+ student.getGrade() +","+ student.getResult() +","+ student.getProgress();

                bw.write(line);     //Write CSV line to File
                bw.newLine();
            }
            bw.flush();  // Ensure everything is written to disk

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // Close resources in finally
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Load Records From The File
    private void loadFromFile() {
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) return;     //File doesn't exist. Skip

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();  //read and discard header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");    //Split line with commas

                if (data.length < 16) {     //ensures all field's are present
                    continue;
                }
                //Parse all fields fields
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String email = data[3];
                String password = data[4];
                Subjects subjects = new Subjects(Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]));
                int attendence = Integer.parseInt(data[10]);
                int credits = Integer.parseInt(data[11]);
                double pcent = Double.parseDouble(data[12]);
                String grade = data[13];
                String result = data[14];
                String progress = data[15];

                //create student with field's
                Student student = new Student(id, name, age, email, password, subjects, attendence, credits, pcent, grade, result, progress);
                students[count] = student;      //Populate students[] with student
                count++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    //===========STUDENT OPERATIONS==============
    //Add student
    @Override
    public void addStudent(Student student) {
        boolean isInserted = false;
        if (count != 0) {
            for (int i = 0; i < count; ++i) {
                if (student.getId() < students[i].getId()) {
                    isInserted = true;
                    //Shift records to right to make space for new record
                    for (int j = count; j > i; --j) {    // Adjusted j range to avoid out-of-bounds error
                        students[j] = students[j-1];
                    }
                    students[i] = student;      //Add new record at correct position
                    ++count;
                    System.out.println("\n>> Student added successfully!");
                    return;
                }
            }
        }
        // If not inserted in loop, add at the last position
        students[count] = student;
        ++count;
        System.out.println("\n>> Student added successfully!");
        return;
    }

    //update student all details
    @Override
    public int updateStudent(int id, String name, int age, Subjects subjects) {
        int start = 0;
        int end = count-1;

        while (start <= end) {
            int mid = start+(end - start)/2;
            if (students[mid].getId() == id) {
                students[mid].setName(name);
                students[mid].setAge(age);
                students[mid].setSubjects(subjects);
                System.out.println("\n>> updated successfully!");
                return mid;
            } else if (id < students[mid].getId()) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        System.out.println("\n>> Student with [id = "+ id +"] not found!");
        return -1;
    }

    //update student name
    @Override
    public int updateStudentName(int id, String name) {
        int start = 0;
        int end = count-1;

        while (start <= end) {
            int mid = start+(end - start)/2;
            if (students[mid].getId() == id) {
                students[mid].setName(name);
                System.out.println("\n>> updated successfully!");
                return mid;
            } else if (id < students[mid].getId()) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        System.out.println("\n>> Student with [id = "+ id +"] not found!");
        return -1;
    }

    //update student age
    @Override
    public void updateStudentAge(int id, int age) {
        int start = 0;
        int end = count-1;

        while (start <= end) {
            int mid = start+(end - start)/2;
            if (students[mid].getId() == id) {
                students[mid].setAge(age);
                System.out.println("\n>> updated successfully!");
                return;
            } else if (id < students[mid].getId()) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        System.out.println("\n>> Student with [id = "+ id +"] not found!");
        return;
    }

    //update student marks
    public int updateStudent(int id, double marks, String value) {
        int start = 0;
        int end = count-1;

        while (start <= end) {
            int mid = start+(end - start)/2;
            if (students[mid].getId() == id) {
                Subjects subjects = students[mid].getSubjects();
                switch (value.toLowerCase()) {
                    case "phy":
                        subjects.setPhy(marks);
                        break;
                    case "chem":
                        subjects.setChem(marks);
                        break;
                    case "math":
                        subjects.setMath(marks);
                        break;
                    case "bio":
                        subjects.setBio(marks);
                        break;
                    case "eng":
                        subjects.setEng(marks);
                        break;
                    default:
                        System.out.println("\n>> Error: Invalid subject name!");
                        return -1;
                }
                System.out.println("\n>> updated successfully!");
                return mid;
            } else if (id < students[mid].getId()) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        System.out.println("\n>> Student with [id = "+ id +"] not found!");
        return -1;
    }

    //Delete student record
    @Override
    public void deleteStudent(int id) {
        int start = 0, end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (students[mid].getId() == id) {
                // Shift elements left to remove the student
                for (int j = mid; j < count - 1; ++j) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null; // Clear the last element
                --count;
                System.out.println("\n>> Deleted successfully!");
                return;
            } else if (students[mid].getId() < id) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println("\n>> Student with [id = "+ id +"] not found!");
    }

    //Sort records
    @Override
    public void sortById(int n, String value) {     //Sort By Id
        if (value.equalsIgnoreCase("Ascending")) {
            for (int i = 0; i < n-1; ++i) {
                for (int j = 0; j < n-1-i; ++j) {
                    if (students[j+1].getId() < students[j].getId()) {
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i < n-1; ++i) {
                for (int j = 0; j < n-1-i; ++j) {
                    if (students[j+1].getId() > students[j].getId()) {
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
            return;
        }
    }
    @Override
    public void sortByPercent(int n, String value) {
        if (value.equalsIgnoreCase("Ascending")) {
            for (int i = 0; i < n-1; ++i) {
                for (int j = 0; j < n-1-i; ++j) {
                    if (students[j+1].getPcent() < students[j].getPcent()) {
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i < n-1; ++i) {
                for (int j = 0; j < n-1-i; ++j) {
                    if (students[j+1].getPcent() > students[j].getPcent()) {
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
            return;
        }
    }

    @Override
    public void sortByName(int n, String value) {
        if (value.equalsIgnoreCase("Ascending")) {
            for (int i = 0; i < n-1; ++i) {
                for (int j = 0; j < n-1-i; ++j) {
                    if (students[j].getName().compareToIgnoreCase(students[j+1].getName()) > 0) {
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i < n-1; ++i) {
                for (int j = 0; j < n-1-i; ++j) {
                    if (students[j].getName().compareToIgnoreCase(students[j+1].getName()) < 0) {
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
            return;
        }
    }

    //Search records
    public boolean searchById(int id) {
        int start = 0;
        int end = count-1;

        while (start <= end) {
            int mid = start + (end - start)/2;
            if (students[mid].getId() == id) {
                System.out.println(students[mid].toString() +" phy = "+ students[mid].getSubjects().getPhy() +", chem = "+ students[mid].getSubjects().getChem() +", math = "+ students[mid].getSubjects().getMath() +", bio = "+ students[mid].getSubjects().getBio() +", eng = "+ students[mid].getSubjects().getEng() +", pcent = "+ students[mid].getPcent() +", result = "+ students[mid].getResult() +"]");
                return true;
            } else if (id < students[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    //Check id before Updation
    public boolean searchById(int id, String value) {
        int start = 0;
        int end = count-1;

        while (start <= end) {
            int mid = start + (end - start)/2;
            if (students[mid].getId() == id) {
                return true;
            } else if (id < students[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
    public void searchByName(String name) {
        boolean isFound = false;
        for (int i = 0; i < count; ++i) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                isFound = true;
                System.out.println(students[i].toString() +" phy = "+ students[i].getSubjects().getPhy() +", chem = "+ students[i].getSubjects().getChem() +", math = "+ students[i].getSubjects().getMath() +", bio = "+ students[i].getSubjects().getBio() +", eng = "+ students[i].getSubjects().getEng() +", pcent = "+ students[i].getPcent() +", result = "+ students[i].getResult() +"]");
            }
        }
        if (!isFound) {
            System.out.println("\nStudent with [name = "+ name +"] not found!");
        } else {
            System.out.println("\n>> Searched successfully!");
        }
    }

    //view students
    @Override
    public void viewAllStudents() {
        System.out.println("\n>> Student Details::");
        if (noRecordsFound(count)) {
            return;
        }
        // Print table header
        System.out.println("+-----+----------------------+-----+-------+--------+--------+--------+--------+--------+--------+--------+");
        System.out.printf("| %-3s | %-20s | %-3s | %-5s | %-6s | %-6s | %-6s | %-6s | %-6s | %-7s | %-7s |%n",
                "ID", "NAME", "AGE", "GRADE", "PHY", "CHEM", "MATH", "BIO", "ENG", "PERCENT", "RESULT");
        System.out.println("+-----+----------------------+-----+-------+--------+--------+--------+--------+--------+--------+--------+");

        // Print student data
        for (int i = 0; i < count; ++i) {
            System.out.printf("| %-3d | %-20s | %-3d | %-5s | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-7.2f | %-7s %n",
                    students[i].getId(), students[i].getName(), students[i].getAge(),
                    students[i].getGrade(), students[i].getSubjects().getPhy(),
                    students[i].getSubjects().getChem(), students[i].getSubjects().getMath(), students[i].getSubjects().getBio(), students[i].getSubjects().getEng(), students[i].getPcent(), students[i].getResult());
        }

        // Print table footer
        System.out.println("+-----+----------------------+-----+-------+--------+--------+--------+--------+--------+--------+--------+");

        System.out.println(count +" records found.");
        System.out.println();
    }

    //==============UTILITY OPERATIONS==================
    //calculate percent
    @Override
    public double calcPercent(int i) {
        double percent = 0.0;
        if (count != 0) {
            percent = (students[i].getSubjects().getPhy() + students[i].getSubjects().getChem() + students[i].getSubjects().getMath() + students[i].getSubjects().getBio() + students[i].getSubjects().getEng()) * 100 / 500;
            students[i].setPcent(percent);
        }
        return percent;
    }
    public double calcPercent(double phy, double chem, double math, double bio, double eng) {
        double percent = (phy+chem+math+bio+eng)*100/500;
        return percent;
    }
    @Override
    public String calcGrade(double pcent) {     //Calculate grade
        if (pcent >= 90) {
            return "O";
        } else if (pcent < 90 && pcent >= 80) {
            return "A";
        } else if (pcent < 80 && pcent >= 65) {
            return "B";
        } else if (pcent < 65 && pcent >= 45) {
            return "C";
        } else {
            return "F";
        }
    }
    @Override
    public String calcResult(double pcent) {    //Calculate Result
        if (pcent >= 45) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    @Override
    public String calcProgress(double pcent) {
        if (pcent >= 90) {
            return "Excellent";
        } else if (pcent < 90 && pcent >= 80) {
            return "Very Good";
        } else if (pcent < 80 && pcent >= 65) {
            return "Good";
        } else if (pcent < 65 && pcent >= 45) {
            return "Satisfactory";
        } else {
            return "Poor";
        }
    }

    @Override
    public String getUsername(String name) {
        name = name.replace(" ", "");   // Remove spaces and return the modified string
        return name.toLowerCase();
    }
    public boolean checkIdForDuplication(int id) {
        for (int i = 0; i < count; ++i) {
            if (id == students[i].getId()) {
                return true;
            }
        }
        return false;
    }

    //==================MANAGE OPERATIONS==================
    @Override
    public void operations(StudentManager manager) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n========Student Operations========");
            System.out.print("\n1. Add Student\n2. Update Student\n3. Delete Student\n4. View All Students\n5. Sort Records\n6. Search In Records\n7. Save & Logout\n");
            System.out.print("\n>> Choose an operation: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:     //Add Student
                    if (count == students.length) {
                        System.out.println("\n>> Cannot add. Maximum limit reached!");
                        break;
                    }
                    System.out.println("\nEnter Student Details [WARNING: ID Cannot Be Updated Later]: ");
                    System.out.print("Id: ");
                    int id = sc.nextInt();

                    //Check for Invalid Id
                    do {
                        if (id > 0) {
                            break;
                        }
                        System.out.println(">> Error: Id cannot be negative");
                        System.out.print("Enter Valid Id: ");
                        id = sc.nextInt();
                    } while (true);

                    //Check Id For Duplication
                    if (count != 0) {
                        do {
                            if (checkIdForDuplication(id)) {
                                System.out.println(">> [Id = "+ id +"] Already Present");
                                System.out.print("Enter Valid Id: ");
                                id = sc.nextInt();
                            } else {
                                break;
                            }
                        } while (true);
                    }

                    sc.nextLine();      //read left over newline character

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    //Handle invalid age
                    do {
                        try {
                            checkStudentAge(age);
                        } catch (InvalidStudentAgeException e) {
                            System.out.println(e.getMessage());
                        }
                        if (age > 17 && age <= 25) {
                            break;
                        }
                        System.out.print("Enter valid Age: ");
                        age = sc.nextInt();
                    } while (true);
                    sc.nextLine();      //read left over newline character

                    System.out.print("Enter physics marks: ");
                    double phy = sc.nextDouble();
                    //Handle invalid marks
                    do {
                        try {
                            checkMarks(phy);
                        } catch (InvalidMarksException e) {
                            System.out.println(e.getMessage());
                        }
                        if (phy > 0 && phy <= 100) {
                            break;
                        }
                        System.out.print("Enter valid marks: ");
                        phy = sc.nextInt();
                    } while (true);

                    System.out.print("Enter chemistry marks: ");
                    double chem = sc.nextDouble();
                    //Handle invalid marks
                    do {
                        try {
                            checkMarks(chem);
                        } catch (InvalidMarksException e) {
                            System.out.println(e.getMessage());
                        }
                        if (chem > 0 && chem <= 100) {
                            break;
                        }
                        System.out.print("Enter valid marks: ");
                        chem = sc.nextInt();
                    } while (true);

                    System.out.print("Enter math marks: ");
                    double math = sc.nextDouble();
                    //Handle invalid marks
                    do {
                        try {
                            checkMarks(math);
                        } catch (InvalidMarksException e) {
                            System.out.println(e.getMessage());
                        }
                        if (math > 0 && math <= 100) {
                            break;
                        }
                        System.out.print("Enter valid marks: ");
                        math = sc.nextInt();
                    } while (true);

                    System.out.print("Enter biology marks: ");
                    double bio = sc.nextDouble();
                    //Handle invalid marks
                    do {
                        try {
                            checkMarks(bio);
                        } catch (InvalidMarksException e) {
                            System.out.println(e.getMessage());
                        }
                        if (bio > 0 && bio <= 100) {
                            break;
                        }
                        System.out.print("Enter valid marks: ");
                        bio = sc.nextInt();
                    } while (true);

                    System.out.print("Enter english marks: ");
                    double eng = sc.nextDouble();
                    //Handle invalid marks
                    do {
                        try {
                            checkMarks(eng);
                        } catch (InvalidMarksException e) {
                            System.out.println(e.getMessage());
                        }
                        if (eng > 0 && eng <= 100) {
                            break;
                        }
                        System.out.print("Enter valid marks: ");
                        eng = sc.nextInt();
                    } while (true);

                    System.out.print("Enter Attendence: ");
                    int attendence = sc.nextInt();

                    System.out.print("Enter Credits(Out of 5): ");
                    int credits = sc.nextInt();

                    //Generate Email And Password For Student
                    String email = getUsername(name).concat("@gmail.com");
                    String password = getUsername(name).concat("@123");

                    //Calculate Percent, Grade, Result and Progress
                    double pcent = manager.calcPercent(phy, chem, bio, math, eng);

                    String grade = calcGrade(pcent);

                    String result = calcResult(pcent);

                    String progress = calcProgress(pcent);

                    //Call Method To Add Student Record
                    manager.addStudent(new Student(id, name, age, email, password, new Subjects(phy, chem, math, bio, eng), attendence, credits, pcent, grade, result, progress));
                    break;
                case 2:     //Update Student Details
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.println("\nWhat to update?");
                    System.out.print("1. Name\n2. Age\n3. Physics marks\n4. Chemistry marks\n5. Math marks\n6. Biology marks\n7. English marks\n8. All\n");

                    System.out.print("Enter your choice: ");
                    int choice = sc.nextInt();

                    System.out.print("Enter Student id: ");
                    id = sc.nextInt();

                    manager.sortById(count, "Ascending");
                    boolean isTrue = searchById(id, "update");      //Check id before updation
                    if (!isTrue) {
                        System.out.println("\nStudent with [id = "+ id +"] not found!");
                        continue;
                    }

                    sc.nextLine();      //read leftover newline character

                    switch (choice) {
                        case 1:     //Update name
                            System.out.print("Enter name: ");
                            name = sc.nextLine();

                            sortById(count, "Ascending");
                            int index = manager.updateStudentName(id, name);

                            //Update email, password after name updation
                            if (index != -1) {
                                students[index].setEmail(getUsername(name).concat("@gmail.com"));
                                students[index].setPassword(getUsername(name).concat("@123"));
                            }
                            break;
                        case 2:     //Update Age
                            System.out.print("Enter Age: ");
                            age = sc.nextInt();

                            //Handle invalid age
                            do {
                                try {
                                    checkStudentAge(age);
                                } catch (InvalidStudentAgeException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (age > 17 && age <= 25) {
                                    break;
                                }
                                System.out.print("Enter valid Age: ");
                                age = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            manager.updateStudentAge(id, age);
                            break;
                        case 3:     //Update Marks
                            System.out.print("Enter physics marks: ");
                            phy = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(phy);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (phy > 0 && phy <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                phy = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            index = manager.updateStudent(id, phy, "phy");

                            //Update Percent, Grade, Result and Progress
                            if (index != -1) {
                                pcent = manager.calcPercent(index);

                                grade = manager.calcGrade(pcent);
                                students[index].setGrade(grade);

                                result = calcResult(pcent);
                                students[index].setResult(result);

                                progress = calcProgress(pcent);
                                students[index].setProgress(progress);
                            }

                            break;
                        case 4:
                            System.out.print("Enter chemistry marks: ");
                            chem = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(chem);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (chem > 0 && chem <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                chem = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            index = manager.updateStudent(id, chem, "chem");

                            //Update Percent, Grade, Result and Progress
                            if (index != -1) {
                                pcent = manager.calcPercent(index);

                                grade = manager.calcGrade(pcent);
                                students[index].setGrade(grade);

                                result = calcResult(pcent);
                                students[index].setResult(result);

                                progress = calcProgress(pcent);
                                students[index].setProgress(progress);
                            }
                            break;
                        case 5:
                            System.out.print("Enter math marks: ");
                            math = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(math);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (math > 0 && math <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                math = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            index = manager.updateStudent(id, math, "math");

                            //Update Percent, Grade, Result and Progress
                            if (index != -1) {
                                pcent = manager.calcPercent(index);

                                grade = manager.calcGrade(pcent);
                                students[index].setGrade(grade);

                                result = calcResult(pcent);
                                students[index].setResult(result);

                                progress = calcProgress(pcent);
                                students[index].setProgress(progress);
                            }
                            break;
                        case 6:
                            System.out.print("Enter biology marks: ");
                            bio = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(bio);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (bio > 0 && bio <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                bio = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            index = manager.updateStudent(id, bio, "bio");

                            //Update Percent, Grade, Result and Progress
                            if (index != -1) {
                                pcent = manager.calcPercent(index);

                                grade = manager.calcGrade(pcent);
                                students[index].setGrade(grade);

                                result = calcResult(pcent);
                                students[index].setResult(result);

                                progress = calcProgress(pcent);
                                students[index].setProgress(progress);
                            }
                            break;
                        case 7:
                            System.out.print("Enter english marks: ");
                            eng = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(eng);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (eng > 0 && eng <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                eng = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            index = manager.updateStudent(id, eng, "eng");

                            //Update Percent, Grade, Result and Progress
                            if (index != -1) {
                                pcent = manager.calcPercent(index);

                                grade = manager.calcGrade(pcent);
                                students[index].setGrade(grade);

                                result = calcResult(pcent);
                                students[index].setResult(result);

                                progress = calcProgress(pcent);
                                students[index].setProgress(progress);
                            }
                            break;
                        case 8:     //Update All details
                            System.out.print("Enter name: ");
                            name = sc.nextLine();

                            System.out.print("Enter Age: ");
                            age = sc.nextInt();

                            //Handle invalid age
                            do {
                                try {
                                    checkStudentAge(age);
                                } catch (InvalidStudentAgeException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (age > 17 && age <= 25) {
                                    break;
                                }
                                System.out.print("Enter valid Age: ");
                                age = sc.nextInt();
                            } while (true);
                            sc.nextLine();      //read left over newline character

                            System.out.print("Enter physics marks: ");
                            phy = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(phy);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (phy > 0 && phy <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                phy = sc.nextInt();
                            } while (true);

                            System.out.print("Enter chemistry marks: ");
                            chem = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(chem);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (chem > 0 && chem <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                chem = sc.nextInt();
                            } while (true);

                            System.out.print("Enter math marks: ");
                            math = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(math);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (math > 0 && math <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                math = sc.nextInt();
                            } while (true);

                            System.out.print("Enter biology marks: ");
                            bio = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(bio);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (bio > 0 && bio <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                bio = sc.nextInt();
                            } while (true);

                            System.out.print("Enter english marks: ");
                            eng = sc.nextDouble();
                            //Handle invalid marks
                            do {
                                try {
                                    checkMarks(eng);
                                } catch (InvalidMarksException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (eng > 0 && eng <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid marks: ");
                                eng = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            index = manager.updateStudent(id, name, age, new Subjects(phy, chem, math, bio, eng));

                            //Update Email, Password, Percent, Grade, Result and Progress
                            if (index != -1) {
                                students[index].setEmail(getUsername(name).concat("@gmail.com"));
                                students[index].setPassword(getUsername(name).concat("@123"));

                                pcent = manager.calcPercent(index);

                                grade = manager.calcGrade(pcent);
                                students[index].setGrade(grade);

                                result = calcResult(pcent);
                                students[index].setResult(result);

                                progress = calcProgress(pcent);
                                students[index].setProgress(progress);
                            }
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    break;
                case 3:     //Delete Student
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();

                    manager.sortById(count, "Ascending");
                    manager.deleteStudent(id);
                    break;
                case 4:     //View Students
                    manager.viewAllStudents();
                    break;
                case 5:     //Sort Records
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.print("Sort by:\n1. Id\n2. Name\n3. Percent\nEnter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:     //Sort by ID
                            System.out.print("1. Ascending\n2. Descending\nEnter your choice: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:     //Sort by Ascending
                                    manager.sortById(count, "Ascending");
                                    System.out.println("\n>> sorted successfully");
                                    break;
                                case 2:     //Sort by Descending
                                    manager.sortById(count, "Descending");
                                    System.out.println("\n>> sorted successfully");
                                    break;
                                default:
                                    System.out.println("Invalid Choice!");
                                    continue;
                            }
                            break;
                        case 2:     //Sort by Name
                            System.out.print("1. Ascending\n2. Descending\nEnter your choice: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:     //Sort by Ascending
                                    manager.sortByName(count, "Ascending");
                                    System.out.println("\n>> sorted successfully");
                                    break;
                                case 2:     //Sort by Descending
                                    manager.sortByName(count, "Descending");
                                    System.out.println("\n>> sorted successfully");
                                    break;
                                default:
                                    System.out.println("Invalid Choice!");
                                    continue;
                            }
                            break;
                        case 3:     //Sort by Percent
                            System.out.print("1. Ascending\n2. Descending\nEnter your choice: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:     //Sort by Ascending
                                    manager.sortByPercent(count, "Ascending");
                                    System.out.println("\n>> sorted successfully");
                                    break;
                                case 2:     //Sort by Descending
                                    manager.sortByPercent(count, "Descending");
                                    System.out.println("\n>> sorted successfully");
                                    break;
                                default:
                                    System.out.println("Invalid Choice!");
                                    continue;
                            }
                            break;
                        default:
                            System.out.println("Invalid Choice!");
                            continue;
                    }
                    break;
                case 6:     //Search Records
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.print("Search By\n1. Id\n2. Name\nEnter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:     //Search By ID
                            System.out.print("Enter id: ");
                            id = sc.nextInt();

                            manager.sortById(count, "Ascending");

                            if (manager.searchById(id)) {
                                System.out.println("\n>> Searched successfully!");
                            } else {
                                System.out.println("\nStudent with [id = "+ id +"] not found!");
                            }
                            break;
                        case 2:     //Search By Name
                            sc.nextLine();      //read leftover newline character
                            System.out.print("Enter name: ");
                            name = sc.nextLine();
                            manager.searchByName(name);
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            continue;
                    }
                    break;
                case 7:     //Exit From Application
                    if (count != 0) {
                        sortById(count, "Ascending");
                        saveToFile();   //Writes updated state of students[]
                        System.out.println("\n>> Data Saved Successfully!");
                    }
                    System.out.println(">> Logged Out..");
                    return;
                default:
                    System.out.println(">> Invalid Operation!");
            }

        } while (true);
    }
}
// End