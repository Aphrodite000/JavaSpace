import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {
    public static void main(String[] args) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            connection=DBUtil.getConnection();
            String sql="";
            ps=connection.prepareStatement(sql);
            //在这里释放资源的话可能会执行不到
            rs=ps.executeQuery();
            while (rs.next()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
