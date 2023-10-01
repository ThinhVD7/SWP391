package Model;


public class Course {
    private String courseID;
    private String courseName;
    private String semester;
    private String startDate;
    private String endDate;

    public Course() {
    }

    public Course(String courseID, String courseName, String semester, String startDate, String endDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
