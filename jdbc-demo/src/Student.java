import java.util.List;

public class Student {
    private  Integer id;
    private  Integer sn;
    private  String  name;
    private  String qqMail;
    private  Integer classesId;
    private  Classes classes;//一个学生对应一个班级
    private List<Score> scores;//selectScore2中要返回带着多个分数的学生
    //不能用 int在new 对象的时候,默认值是0,有意义，可以查数据；Integer默认值是null,
    //所以数据库不用基本数据类型，都用的是它的包装类，封装类型

    //生成每个属性的方法


    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSn() {
        return sn;
    }
    //获取值
    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQqMail() {
        return qqMail;
    }

    public void setQqMail(String qqMail) {
        this.qqMail = qqMail;
    }

    public Integer getClassesId() {
        return classesId;
    }

    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    //Generate toString()自动生成
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sn=" + sn +
                ", name='" + name + '\'' +
                ", qqMail='" + qqMail + '\'' +
                ", classesId=" + classesId +
                '}';
    }
}
