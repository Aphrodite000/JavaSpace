import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    public static void main(String[] args) {
        Connection connection=DBUtil.getConnection();
        String sql="select * from student where id=?";
        //？是占位符，这是要用preparedStatement,之后要替换掉
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,3);
            //1表示从第一个占位符开始，然后跟实际的id值，这样查询出来只是一条语句
            //处理结果集
            ResultSet rs=ps.executeQuery();
            //String sql="select * from student where id=3"; 就用Statement
            while(rs.next()){
                Integer id=rs.getInt(1);
                Integer sn=rs.getInt("sn");
                //也可以是列名
                System.out.println(String.format("id=%s,sn=%s",id,sn));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
