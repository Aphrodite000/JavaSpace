import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static boolean deleteStudent(Student student){
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection=DBUtil.getConnection();
            String sql=" ";
            ps=connection.prepareStatement(sql);
            int num=ps.executeUpdate();
            return num>0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,null);
        }
        return false;
    }
}
