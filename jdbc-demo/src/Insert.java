import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//新增操作返回值类型是int，是返回几行执行成功了,
//或者返回插入是否成功
public class Insert {
    public boolean insertStudent(Student student){
        //mail就是以什么结尾的
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection=DBUtil.getConnection();
            String sql="insert into student (id,sn,name,qq_mail,classes_id) values (?,?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,student.getId());//查询以这个mail结尾的
            ps.setInt(2,student.getSn());//这两行是根据传入参数来查询的
            ps.setString(3,student.getName());
            ps.setString(4,student.getQqMail());
            ps.setInt(4,student.getClassesId());
            int num=ps.executeUpdate();//增，删和改都用executeUpdate();
            return num>0;
            //插入数据没有结果集
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,null);
        }
        //如果走到这里了肯定是异常了，肯定就是false
        return false;
    }
}
