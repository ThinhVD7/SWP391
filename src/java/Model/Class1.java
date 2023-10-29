package Model;

public class Class1 {

    private String classID;
    private String className;
    private String courseID;

    public Class1() {
    }

    public Class1(String classID, String className, String courseID) {
        this.classID = classID;
        this.className = className;
        this.courseID = courseID;
    }

    public String getClassID() {
        return classID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
