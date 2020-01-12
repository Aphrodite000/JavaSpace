import java.math.BigDecimal;

public class Score {
    private Integer id;
    private BigDecimal score;
    private Integer studentId;
    private Integer courseId;
    //private Course course;
    private String courseName;
    private String studentName;
    //分数表查询的时候需要出现这两列，学生名字和课程名字


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score=" + score +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
