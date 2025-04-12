package core_Java.collegeManagementSystem.facultyManagement;

import core_Java.collegeManagementSystem.utils.InvalidFacultyAgeException;
import core_Java.collegeManagementSystem.utils.FacultyOperations;
import core_Java.collegeManagementSystem.utils.UtilityMethods;

import java.io.*;
import java.util.Scanner;

//FacultyManager class to manage faculty records
public class FacultyManager extends UtilityMethods implements FacultyOperations {
    private static final String FILE_NAME = "faculty.csv";
    private static final String FOLDER_NAME = "CollegeSystemData";
    private static final String CSV_FILE_PATH;      //csv file path on desktop

    static {
        String userHome = System.getProperty("user.home");
        CSV_FILE_PATH = userHome + File.separator + "Desktop" + File.separator + FOLDER_NAME + File.separator + FILE_NAME;
    }
    protected Faculty[] faculties;  //Array to manage faculties
    private final int MAX_FACULTIES = 5;        //Can Update to suit your needs
    private int count;      //faculty count

    // FacultyManager constructor
    public FacultyManager() {
        faculties = new Faculty[MAX_FACULTIES];
        count = 0;
        loadFromFile();     //populate faculties[] & set count
    }

    //========= FILE OPERATIONS =========

    private void saveToFile() {
        File file = new File(CSV_FILE_PATH);
        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        try {
            fileWriter = new FileWriter(file);
            bw = new BufferedWriter(fileWriter);

            //Write header
            bw.write("id,name,age,subject,experience,qualification");
            bw.newLine();

            for (int i = 0; i < count; ++i) {
                Faculty faculty = faculties[i];     //get faculty

                //Build csv line using faculty field's
                String line = faculty.getId() +","+ faculty.getName() +","+ faculty.getAge() +","+ faculty.getSubject() +","+ faculty.getYearsOfExp() +","+ faculty.getQualification();

                bw.write(line);
                bw.newLine();
            }
            bw.flush();     // Ensure everything is written to disk

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
    private void loadFromFile() {
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) {
            return;     //File not exists yet, Skip
        }

        //try-with-resources block
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String headerLine = br.readLine();      //Read & discard header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");    //Split line into array of strings

                if (data.length < 6) {
                    continue;   //Ensure all field's are present
                }

                //parse required field's
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String subject = data[3];
                int yearsOfExp = Integer.parseInt(data[4]);
                String qualification = data[5];

                Faculty faculty = new Faculty(id, name, age, subject, yearsOfExp, qualification);
                faculties[count] = faculty;
                count++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //========= Faculty Operations ==========
    @Override
    public void addFaculty(Faculty faculty) {       //Add faculty
        boolean isInserted = false;
        if (count != 0) {
            for (int i = 0; i < count; ++i) {
                if (faculty.getId() < faculties[i].getId()) {
                    isInserted = true;
                    //Shift records to right to make space for new record
                    for (int j = count; j > i; --j) {    // Adjusted j range to avoid out-of-bounds error
                        faculties[j] = faculties[j-1];
                    }
                    faculties[i] = faculty;      //Add new record at correct position
                    ++count;
                    System.out.println("\n>> Faculty added successfully!");
                    return;
                }
            }
        }
        // If not inserted in loop, add at the last position
        faculties[count] = faculty;
        ++count;
        System.out.println("\n>> Faculty added successfully!");
    }

    //Update Faculty All Details
    @Override
    public void updateFaculty(int id, String name, int age, int yearsOfExp) {
        int start = 0;
        int end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (faculties[mid].getId() == id) {
                faculties[mid].setName(name);
                faculties[mid].setAge(age);
                faculties[mid].setYearsOfExp(yearsOfExp);
                System.out.println("\n>> updated successfully!");
                return;
            } else if (id < faculties[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("\n>> Faculty with [id = " + id + "] not found!");
        return;
    }

    //Update Faculty Name
    @Override
    public void updateFacultyName(int id, String name) {
        int start = 0;
        int end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (faculties[mid].getId() == id) {
                faculties[mid].setName(name);
                System.out.println("\n>> updated successfully!");
                return;
            } else if (id < faculties[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("\n>> Faculty with [id = " + id + "] not found!");
        return;
    }

    //Update Faculty Age
    @Override
    public void updateFacultyAge(int id, int age) {
        int start = 0;
        int end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (faculties[mid].getId() == id) {
                faculties[mid].setAge(age);
                System.out.println("\n>> updated successfully!");
                return;
            } else if (id < faculties[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("\n>> Faculty with [id = " + id + "] not found!");
        return;
    }

    //Update Faculty years of experience
    @Override
    public void updateFacultyYearsOfExp(int id, int yearsOfExp) {
        int start = 0;
        int end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (faculties[mid].getId() == id) {
                faculties[mid].setYearsOfExp(yearsOfExp);
                System.out.println("\n>> updated successfully!");
                return;
            } else if (id < faculties[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println("\n>> Faculty with [id = " + id + "] not found!");
        return;
    }

    //Delete Faculty record
    @Override
    public void deleteFaculty(int id) {
        int start = 0, end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (faculties[mid].getId() == id) {
                // Shift elements left to remove the student
                for (int j = mid; j < count - 1; ++j) {
                    faculties[j] = faculties[j + 1];
                }
                faculties[count - 1] = null; // Clear the last element
                --count;
                System.out.println("\n>> Deleted successfully!");
                return;
            } else if (faculties[mid].getId() < id) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println("\n>> Faculty with [id = "+ id +"] not found!");
    }

    //Sort Faculty Records
    @Override
    public void sortById(int n, String value) {     //Sort By Id
        if (value.equalsIgnoreCase("Ascending")) {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    if (faculties[j + 1].getId() < faculties[j].getId()) {
                        Faculty temp = faculties[j];
                        faculties[j] = faculties[j + 1];
                        faculties[j + 1] = temp;
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    if (faculties[j + 1].getId() > faculties[j].getId()) {
                        Faculty temp = faculties[j];
                        faculties[j] = faculties[j + 1];
                        faculties[j + 1] = temp;
                    }
                }
            }
            return;
        }
    }

    @Override
    public void sortByName(int n, String value) {       //Sort By Name
        if (value.equalsIgnoreCase("Ascending")) {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    if (faculties[j].getName().compareToIgnoreCase(faculties[j + 1].getName()) > 0) {
                        Faculty temp = faculties[j];
                        faculties[j] = faculties[j + 1];
                        faculties[j + 1] = temp;
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    if (faculties[j].getName().compareToIgnoreCase(faculties[j + 1].getName()) < 0) {
                        Faculty temp = faculties[j];
                        faculties[j] = faculties[j + 1];
                        faculties[j + 1] = temp;
                    }
                }
            }
            return;
        }
    }

    @Override
    public void sortByYearsOfExp(int n, String value) {     //Sort By Years Of Exp.
        if (value.equalsIgnoreCase("Ascending")) {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    if (faculties[j + 1].getYearsOfExp() < faculties[j].getYearsOfExp()) {
                        Faculty temp = faculties[j];
                        faculties[j] = faculties[j + 1];
                        faculties[j + 1] = temp;
                    }
                }
            }
            return;
        } else {
            for (int i = 0; i < n - 1; ++i) {
                for (int j = 0; j < n - 1 - i; ++j) {
                    if (faculties[j + 1].getYearsOfExp() > faculties[j].getYearsOfExp()) {
                        Faculty temp = faculties[j];
                        faculties[j] = faculties[j + 1];
                        faculties[j + 1] = temp;
                    }
                }
            }
            return;
        }
    }

    //Search records
    @Override
    public boolean searchById(int id) {
        int start = 0;
        int end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (faculties[mid].getId() == id) {
                System.out.println(faculties[mid].toString() + "sub = " + faculties[mid].getSubject() + ", years_Of_Exp = " + faculties[mid].getYearsOfExp() + ", qualifn = " + faculties[mid].getQualification() + "]");
                return true;
            } else if (id < faculties[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    @Override
    public boolean searchById(int id, String value) {       //Check id before Updation
        int start = 0;
        int end = count - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (faculties[mid].getId() == id) {
                return true;
            } else if (id < faculties[mid].getId()) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    @Override
    public void searchByName(String name) {     //Search By Name
        boolean isFound = false;
        for (int i = 0; i < count; ++i) {
            if (faculties[i].getName().equalsIgnoreCase(name)) {
                isFound = true;
                System.out.println(faculties[i].toString() + "sub = " + faculties[i].getSubject() + ", years_Of_Exp = " + faculties[i].getYearsOfExp() + ", qualifn = " + faculties[i].getQualification() + "]");
            }
        }
        if (!isFound) {
            System.out.println("\nStudent with [name = " + name + "] not found!");
        } else {
            System.out.println("\n>> Searched successfully!");
        }
    }
    public boolean checkIdForDuplication(int id) {
        for (int i = 0; i < count; ++i) {
            if (id == faculties[i].getId()) {
                return true;
            }
        }
        return false;
    }

    //View faculties
    @Override
    public void viewAllFaculties() {
        System.out.println("\n>> Faculty Details::");
        if (noRecordsFound(count)) {
            return;
        }
        // Print table header
        System.out.println("+-----+----------------------+-----+------------------+------------------+----------------------+");
        System.out.printf("| %-3s | %-20s | %-3s | %-16s | %-16s | %-20s |%n",
                "ID", "NAME", "AGE", "SUBJECT", "YEARS OF EXP", "QUALIFICATION");
        System.out.println("+-----+----------------------+-----+------------------+------------------+----------------------+");

        // Print faculty data
        for (int i = 0; i < count; ++i) {
            System.out.printf("| %-3d | %-20s | %-3d | %-16s | %-16d | %-20s |%n",
                    faculties[i].getId(), faculties[i].getName(), faculties[i].getAge(),
                    faculties[i].getSubject(), faculties[i].getYearsOfExp(), faculties[i].getQualification());
        }

        // Print table footer
        System.out.println("+-----+----------------------+-----+------------------+------------------+----------------------+");


        System.out.println(count + " records found.");
        System.out.println();
    }

    //==================MANAGE OPERATIONS==================

    @Override
    public void operations(FacultyManager manager) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n========Faculty Operations========");
            System.out.print("\n1. Add Faculty\n2. Update Faculty\n3. Delete Faculty\n4. View All Faculty\n5. Sort Records\n6. Search In Records\n7. Save & Logout\n");
            System.out.print("\n>> Choose an operation: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:     // Add Faculty
                    if (count == faculties.length) {
                        System.out.println("\n>> Cannot add. Maximum limit reached!");
                        break;
                    }
                    System.out.println("\nEnter Faculty Details:");
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

                    sc.nextLine(); // Read left over newline character

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    // Handle invalid age
                    do {
                        try {
                            checkFacultyAge(age);
                        } catch (InvalidFacultyAgeException e) {
                            System.out.println(e.getMessage());
                        }
                        if (age > 21 && age <= 100) {
                            break;
                        }
                        System.out.print("Enter valid Age: ");
                        age = sc.nextInt();
                    } while (true);
                    sc.nextLine(); // Read left over newline character

                    System.out.print("Enter Subject: ");
                    String subject = sc.nextLine();

                    System.out.print("Enter Years of Experience: ");
                    int yearsOfExp = sc.nextInt();

                    do {
                        if (yearsOfExp >= 0 && yearsOfExp <= 50) { // Valid experience range
                            break;
                        }
                        System.out.print("Enter valid Years of Experience: ");
                        yearsOfExp = sc.nextInt();
                    } while (true);

                    System.out.print("Enter Qualification: ");
                    sc.nextLine(); // Consume newline
                    String qualification = sc.nextLine();

                    //Call Method To Add Faculty
                    manager.addFaculty(new Faculty(id, name, age, subject, yearsOfExp, qualification));
                    break;
                case 2:     // Update Faculty Details
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.println("\nWhat to update?");
                    System.out.print("1. Name\n2. Age\n3. Years of Experience\n4. All (Name, Age, Years of Experience)\n");

                    System.out.print("Enter your choice: ");
                    int choice = sc.nextInt();

                    System.out.print("Enter Faculty id: ");
                    id = sc.nextInt();

                    manager.sortById(count, "Ascending");
                    boolean isTrue = searchById(id, "update"); // Check ID before updating
                    if (!isTrue) {
                        System.out.println("\nFaculty with [id = " + id + "] not found!");
                        continue;
                    }

                    sc.nextLine(); // Read leftover newline character

                    switch (choice) {
                        case 1: // Update Name
                            System.out.print("Enter Name: ");
                            name = sc.nextLine();

                            sortById(count, "Ascending");
                            manager.updateFacultyName(id, name);
                            break;

                        case 2: // Update Age
                            System.out.print("Enter Age: ");
                            age = sc.nextInt();

                            // Handle invalid age
                            do {
                                try {
                                    checkFacultyAge(age);
                                } catch (InvalidFacultyAgeException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (age > 21 && age <= 100) { // Faculty should be above 21
                                    break;
                                }
                                System.out.print("Enter valid Age: ");
                                age = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            manager.updateFacultyAge(id, age);
                            break;

                        case 3: // Update Years of Experience
                            System.out.print("Enter Years of Experience: ");
                            yearsOfExp = sc.nextInt();

                            do {
                                if (yearsOfExp >= 0 && yearsOfExp <= 50) { // Valid experience range
                                    break;
                                }
                                System.out.print("Enter valid Years of Experience: ");
                                yearsOfExp = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            manager.updateFacultyYearsOfExp(id, yearsOfExp);
                            break;

                        case 4: // Update All (Name, Age, Years of Experience)
                            System.out.print("Enter Name: ");
                            name = sc.nextLine();

                            System.out.print("Enter Age: ");
                            age = sc.nextInt();

                            // Handle invalid age
                            do {
                                try {
                                    checkFacultyAge(age);
                                } catch (InvalidFacultyAgeException e) {
                                    System.out.println(e.getMessage());
                                }
                                if (age > 21 && age <= 100) {
                                    break;
                                }
                                System.out.print("Enter valid Age: ");
                                age = sc.nextInt();
                            } while (true);

                            System.out.print("Enter Years of Experience: ");
                            yearsOfExp = sc.nextInt();

                            do {
                                if (yearsOfExp >= 0 && yearsOfExp <= 50) {
                                    break;
                                }
                                System.out.print("Enter valid Years of Experience: ");
                                yearsOfExp = sc.nextInt();
                            } while (true);

                            sortById(count, "Ascending");
                            manager.updateFaculty(id, name, age, yearsOfExp);
                            break;

                        default:
                            System.out.println("Invalid choice! Please select a valid option.");
                            break;
                    }
                    break;
                case 3:     // Delete Faculty
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();

                    manager.sortById(count, "Ascending");
                    manager.deleteFaculty(id);
                    break;

                case 4:     // View Faculties
                    manager.viewAllFaculties();
                    break;

                case 5:     // Sort Faculty Records
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.print("Sort by:\n1. Id\n2. Name\n3. Years of Experience\nEnter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:     // Sort by ID
                            System.out.print("1. Ascending\n2. Descending\nEnter your choice: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    manager.sortById(count, "Ascending");
                                    System.out.println("\n>> Sorted successfully");
                                    break;
                                case 2:
                                    manager.sortById(count, "Descending");
                                    System.out.println("\n>> Sorted successfully");
                                    break;
                                default:
                                    System.out.println("Invalid Choice!");
                                    continue;
                            }
                            break;
                        case 2:     // Sort by Name
                            System.out.print("1. Ascending\n2. Descending\nEnter your choice: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    manager.sortByName(count, "Ascending");
                                    System.out.println("\n>> Sorted successfully");
                                    break;
                                case 2:
                                    manager.sortByName(count, "Descending");
                                    System.out.println("\n>> Sorted successfully");
                                    break;
                                default:
                                    System.out.println("Invalid Choice!");
                                    continue;
                            }
                            break;
                        case 3:     // Sort by Years of Experience
                            System.out.print("1. Ascending\n2. Descending\nEnter your choice: ");
                            choice = sc.nextInt();
                            switch (choice) {
                                case 1:
                                    manager.sortByYearsOfExp(count, "Ascending");
                                    System.out.println("\n>> Sorted successfully");
                                    break;
                                case 2:
                                    manager.sortByYearsOfExp(count, "Descending");
                                    System.out.println("\n>> Sorted successfully");
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

                case 6:     // Search Faculty Records
                    if (noRecordsFound(count)) {
                        continue;
                    }
                    System.out.print("Search By\n1. Id\n2. Name\nEnter your choice: ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:     // Search By ID
                            System.out.print("Enter id: ");
                            id = sc.nextInt();

                            manager.sortById(count, "Ascending");
                            if (manager.searchById(id)) {
                                System.out.println("\n>> Searched successfully!");
                            } else {
                                System.out.println("\nFaculty with [id = " + id + "] not found!");
                            }
                            break;
                        case 2:     // Search By Name
                            sc.nextLine();  // read leftover newline character
                            System.out.print("Enter name: ");
                            name = sc.nextLine();
                            manager.searchByName(name);
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            continue;
                    }
                    break;

                case 7:     // Exit From Application
                    if (count != 0) {
                        sortById(count, "Ascending");
                        saveToFile();   //Writes updated state of faculties[] to file
                        System.out.println("\n>> Data Saved Successfully.");
                    }
                    System.out.println(">> Logged Out..");
                    return;

                default:
                    System.out.println(">> Invalid Operation!");
            }
        } while (true);
    }
}