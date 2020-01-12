import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Select {

    public static void main(String[] args) {
        Connection connection=DBUtil.getConnection();
        String sql="select * from student where id=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        //？是占位符，这是要用preparedStatement,之后要替换掉
        try {
            ps=connection.prepareStatement(sql);
            ps.setInt(1,3);
            //1表示从第一个占位符开始，然后跟实际的id值，这样查询出来只是一条语句
            //处理结果集
            rs=ps.executeQuery();
            //String sql="select * from student where id=3"; 就用Statement

            //PreparedStatement
            //String sql="select * from student where id=?";
            //PreparedStatement ps=connection.PrepareStatement(sql);
            //ps.setInt(1,3);
            //ResultSet rs=ps.executeQuery(sql);
            //1.性能好，因为具有预编译，类似于java文件的预编译
            //使用占位符进行预编译，然后动态传入一个语句，不用再编译了，直接执行就好
            //2.sql注入，sql漏洞：能保证安全性，因为如果传一个字符串进来1= or 1=1,动态输入时，可以报错
            //动态输入时可以校验一下

            //Statement
            //String sql="select * from student where id=3";
            //Statement statement=connection.createStatement();
            //ResultSet rs=statement.executeQuery(sql);
            //每次用都编译一次，改一次值就有编译
            //String sql="select * from student where id=1 or 1=1"; 会查出所有数据
            //无法验证传入的参数是什么东西，会把传入的东西当做字符串来拼接
            // 结果就不是想要的结果，但是没有报错，所以安全性能没有PreparedStatement高

            //若String sql="select * from student where qq_mail"+"like ? and classes_id=?";
            //插入时：ps.setString(1,"%qq.com");
            //        ps.setInt(2,1);
            while(rs.next()){
                Integer id=rs.getInt(1);
                Integer sn=rs.getInt("sn");
                //也可以是列名
                System.out.println(String.format("id=%s,sn=%s",id,sn));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭
            try {
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(connection!=null){
                    connection.close();
                    //通过数据源获取的连接，关闭的时候，不是真实的物理上的关闭，而是把连接放到连接池
                    //连接池会把连接再次初始化重置操作，物理上是没有关闭的
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /**
         * Connection connection=null
         *         PreparedStatement ps=null;
         *         ResultSet rs=null;
         *         try {
         *             connection=DBUtil.getConnection();
         *             String sql="select id,sn,name,qq_mail,classes_id"+"from student where qq_mail"+"like ? and classes_id=?";
         *             ps=connection.prepareStatement(sql);
         *             ps.setString(1,"%qq.com");//查询以这个结尾的
         *             ps.setInt(2,1);
         *             rs=ps.executeQuery();
         *             while(rs.next()){
         *                 Integer id=rs.getInt("id");
         *                 Integer sn=rs.getInt("sn");
         *                 String name=rs.getString("name");
         *                 String qqMail=rs.getString("qq_mail");
         *                 Integer classesId=rs.getInt("classes_id");
         *                 System.out.println(String.format("id=%s,sn=%s,name=%s,,mail=%s,classesId=%s",id,
         *                         sn,name,qqMail,classesId));
         *             }
         *         } catch (SQLException e) {
         *             e.printStackTrace();
         *         } finally {
         *             //关闭
         *             try {
         *                 if(rs!=null){
         *                     rs.close();
         *                 }
         *                 if(ps!=null){
         *                     ps.close();
         *                 }
         *                 if(connection!=null){
         *                     connection.close();
         *                     //通过数据源获取的连接，关闭的时候，不是真实的物理上的关闭，而是把连接放到连接池
         *                     //连接池会把连接再次初始化重置操作，物理上是没有关闭的
         *                 }
         *             } catch (SQLException e) {
         *                 e.printStackTrace();
         *             }
         *         }
         */
    }

    //把main函数里的东西封装成方法，共别人使用 ，想传入参数和返回值

    //抽取占位符，作为传入参数，
    // 思考返回值，抽出来一个类
    //处理值的时候，建一个Student对象，数据库表到Student方法的转换,在把每一个实体对象加入List中
    //最后返回list
    public List <Student> selectStudent(String mail,Integer classesId){
        //mail就是以什么结尾的
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Student> students=new ArrayList<>();
        try {
            connection=DBUtil.getConnection();
            String sql="select id,sn,name,qq_mail,classes_id"+"from student where qq_mail"+"like ? and classes_id=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,"%"+mail);//查询以这个mail结尾的
            ps.setInt(2,classesId);//这两行是根据传入参数来查询的
            rs=ps.executeQuery();
            while(rs.next()){
                //处理每一个学生的每一列的值
                Student student=new Student();
                student.setId(rs.getInt("id"));
                //Integer id=rs.getInt("id");
                student.setSn(rs.getInt("sn"));
                //Integer sn=rs.getInt("sn");
                student.setName(rs.getString("name"));
                //String name=rs.getString("name");
                student.setQqMail(rs.getString("qq_mail"));
                //String qqMail=rs.getString("qq_mail");
                student.setClassesId(rs.getInt("classes_id"));
                //Integer classesid=rs.getInt("classes_id");
                students.add(student);
               // System.out.println(student); //绿色的列名随便写，这是字符串打印
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            try {
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(connection!=null){
                    connection.close();
                    //通过数据源获取的连接，关闭的时候，不是真实的物理上的关闭，而是把连接放到连接池
                    //连接池会把连接再次初始化重置操作，物理上是没有关闭的
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    public List <Score> selectScore(Integer score,Integer classesId){
        //mail就是以什么结尾的
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Score> scores=new ArrayList<>();
        try {
            connection=DBUtil.getConnection();
            String sql="select from score sco join student stu on sco.student_id=stu.id" +
                    " join course cou on cou.id=sco.course_id" +
                    " where sco.score>? and stu.classes_id=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,"%"+score);//查询以这个mail结尾的
            ps.setInt(2,classesId);//这两行是根据传入参数来查询的
            rs=ps.executeQuery();
            //返回一个分数的集合
            while(rs.next()){
                Score score0=new Score();
                //等号左边掉哟个啥方法，要看结果集邮那些列就对象那些方法
                score0.setStudentId(rs.getInt("id"));
                score0.setStudentName(rs.getString("student_name"));
                score0.setCourseName(rs.getString("course_name"));
                score0.setScore(rs.getBigDecimal("score"));
                //右边绿色的字符随便打，因为他是结果集的列名，
                scores.add(score0);
                System.out.println(score0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            try {
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(connection!=null){
                    connection.close();
                    //通过数据源获取的连接，关闭的时候，不是真实的物理上的关闭，而是把连接放到连接池
                    //连接池会把连接再次初始化重置操作，物理上是没有关闭的
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return scores;

        //查询的时候看结果集缺啥就把啥添加到属性中去
    }

    //返回学生，一个学生带着多个分数，而不是上面的，一个分数，一个学生的写，那样会有学生重复
    public List <Student> selectScore2(Integer score,Integer classesId){
        //mail就是以什么结尾的
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Student> students=new ArrayList<>();
        try {
            connection=DBUtil.getConnection();
            String sql="select from score sco join student stu on sco.student_id=stu.id" +
                    " join course cou on cou.id=sco.course_id" +
                    " where sco.score>? and stu.classes_id=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,"%"+score);//查询以这个mail结尾的
            ps.setInt(2,classesId);//这两行是根据传入参数来查询的
            rs=ps.executeQuery();
            //返回一个分数的集合
            while(rs.next()){
                Integer id=rs.getInt("id");
                //一个同学对应多个分数，多个课程，课程和分数是一对一的
                Student student0=new Student();
                Score score0=new Score();
                Boolean isExists=false;
                score0.setScore(rs.getBigDecimal("score"));
                score0.setCourseName(rs.getString("course_name"));
                for(Student student:students){
                    if(Integer.compare(id,student.getId())==0){
                        //相等的话，把学生设置成链表里面已有的学生
                        student0=student;
                        isExists=true;
                    }
                }
                student0.setId(rs.getInt("id"));
                student0.setName(rs.getString("student_name"));
                List<Score> existsScores=student0.getScores();
                if(existsScores==null){
                    existsScores=new ArrayList<>();
                }
                existsScores.add(score0);
                //初始化学生，获取一个完整的信息，如果没有学生，就新建一个学生值
                if(isExists){
                    students.add(student0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            try {
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
                if(connection!=null){
                    connection.close();
                    //通过数据源获取的连接，关闭的时候，不是真实的物理上的关闭，而是把连接放到连接池
                    //连接池会把连接再次初始化重置操作，物理上是没有关闭的
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;

        //查询的时候看结果集缺啥就把啥添加到属性中去
    }
}
