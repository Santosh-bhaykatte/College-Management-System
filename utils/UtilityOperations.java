package core_Java.collegeManagementSystem.utils;

public interface UtilityOperations {
    public default boolean checkIdForDuplication(int id) {
        return false;
    }
    public default double calcPercent(double phy, double chem, double math, double bio, double eng) {      //Default Method
        return 0.0d;
    }
    public default double calcPercent(int i) {
        //default method
        return 0.0d;
    }
    public default String calcGrade(double pcent) {     //Default Method
        return null;
    }
    public default String calcResult(double pcent) {    //Default Method
        return null;
    }
    public default String calcProgress(double pcent) {      //Default Method
        return null;
    }
    public default String getUsername(String name) {        //Default Method
        return null;
    }
    public void sortById(int n, String value);
    public void sortByName(int n, String value);
    public default void sortByPercent(int n, String value) {
        //Empty implementation
    }
    public default void sortByYearsOfExp(int n, String value) {
        //Empty implementation
    }
    public boolean searchById(int id);
    public boolean searchById(int id, String value);    //Search id before updation
    public void searchByName(String name);
}
