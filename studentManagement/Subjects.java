package core_Java.collegeManagementSystem.studentManagement;

public class Subjects {
    private double phy;
    private double chem;
    private double math;
    private double bio;
    private double eng;
    public Subjects(double phy, double chem, double math, double bio, double eng) {
        this.phy = phy;
        this.chem = chem;
        this.math = math;
        this.bio = bio;
        this.eng = eng;
    }
    //getters and setters
    public void setPhy(double phy) {
        this.phy = phy;
    }
    public double getPhy() {
        return phy;
    }
    public void setChem(double chem) {
        this.chem = chem;
    }
    public double getChem() {
        return chem;
    }
    public void setMath(double math) {
        this.math = math;
    }
    public double getMath() {
        return math;
    }
    public void setBio(double bio) {
        this.bio = bio;
    }
    public double getBio() {
        return bio;
    }
    public void setEng(double eng) {
        this.eng = eng;
    }
    public double getEng() {
        return eng;
    }
}
