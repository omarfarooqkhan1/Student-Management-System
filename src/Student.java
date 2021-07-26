public class Student {

    private String rollNumber;
    private String name;
    private int semester;

    public Student() {
    }

    public Student(String rollNumber, String name, int semester) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.semester = semester;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return String.format("[Roll Number: %s, Name: %s, Semester: %d]", rollNumber, name, semester);
    }

}
