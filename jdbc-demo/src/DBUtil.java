import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

//DataSource效率更高，不用每一次调用getConnection();都真时的创建一次物理上的连接
//而是直接去池里面直接去取这个连接对象
public class DBUtil {
    /**
     * 1.加载驱动
     * 2.建立连接
     * 3.创建执行对象
     * 4.执行sql
     * 5.处理结果集ResultSet
     * 6.释放资源
     * @param args
     */
    //jdbc:mysql//ip:端口/数据库名
    private  static final String URL="jdbc:mysql://localhost:3306/test2";
    private  static final String USER_NAME="root";
    private  static final String PASSWORD="root";

    //一个数据源多个链接，不用关闭，实际上只是再次初始化
    //创建mysql的数据源
    private static DataSource DATASOURCE=new MysqlDataSource();
    //数据源初始化
    static {
        //强制转化
        ((MysqlDataSource) DATASOURCE).setUrl(URL);
        ((MysqlDataSource) DATASOURCE).setUser(USER_NAME);
        ((MysqlDataSource) DATASOURCE).setPassword(PASSWORD);
    }
    //别人调用时，给别人返回一个Connection对象（连接对象）
    //封装起来，提供给别人使用，不用每次都用手写main来测试它是否正常，借助第三方框架来测试
    // 封装 其他程序要用的直接用connection对象，不用直接用数据源
    public static Connection getConnection(){
        try {
            return DATASOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接获取失败");
        }
    }

    public static void main(String[] args) {
        try {
            //获取连接
            Connection connection=DATASOURCE.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            //1.加载驱动
            //反射，初始化这个类，把这个类加载出来，把这个类返回回来（jdbc.Driver这个类）
            Class.forName("com.mysql.jdbc.Driver");
            //执行Driver里的静态代码块，驱动管理器注册驱动
            //物理上的真实的创建连接
            connection=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            //2.获取连接
            System.out.println(connection);
            //获取不为空，表示连接成功

            //3.创建执行对象
            statement= connection.createStatement();
            //4.执行sql语句
            String sql="select * from student";
            //executeQuery返回类型是一个结果集
            resultSet=statement.executeQuery(sql);
            //5.处理结果集
            //获取每一行的每一列
            while(resultSet.next()){
                //下一条是否有数据，如果有，就一直处理这个结果
                //Integer sn=resultSet.getInt("id");也可以根据列名来获取
                Integer id=resultSet.getInt(1);
                //表示ID，从1开始第几列，对于第一行来说取出的值为1
                Integer sn=resultSet.getInt(2);
                String name=resultSet.getString(3);
                String qq=resultSet.getString(4);
                Integer classesId=resultSet.getInt(5);
                System.out.println(String.format("id=%s,sn=%s,"+
                        "name=%s,qq=%s,classesId=%s",id,sn,name,qq,classesId));

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //6.释放资源，关闭要反着写
            try {
                //在上面的try中出错可能不能完成初始化，就可能为空,所以要判断
                if(resultSet!=null)
                resultSet.close();
                if(statement!=null)
                statement.close();
                if(connection!=null)
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }    */
}
