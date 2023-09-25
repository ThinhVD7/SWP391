package Model;


public class Class {
    private String classID;
    private String className;
    private String courseID;
    private String lecturerID;
    

    public Class() {
    }

    public Class(String classID, String className, String courseID, String lecturerID) {
        this.classID = classID;
        this.className = className;
        this.courseID = courseID;
        this.lecturerID = lecturerID;
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

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
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
