import com.sun.org.apache.bcel.internal.generic.Select;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

//junit测试框架
//junit功能，没有main方法，可以直接运行junit方法 //右键debug"testConnection()'
public class DBUtilTest {
    @Test
    //注解(和类一级的，可以写在类上面，方法，成员变量上面)
    //现在要写junit测试框架，就写在测试方法上
    //测试方法都为public void的
    public void testConnection(){
        //junit中使用最多的API是Assert
        //该方法判断对象是否为空，Assert本意为断言，不为空就是成功的
        Assert.assertNotNull(new Object());
        Assert.assertNotNull(null);
        //这样的测试为junit的单元测试
        //每一个开发人员，提供给别人使用的接口，封装的功能封装的接口都是能够自己写的代码的完整性和正确性，这
        //通过单元测试
    }

    @Test
    public void testSelectScore(){
        List<Score> list=new Select().selectScore(60,1);
        System.out.println(list);
        //是否有9行数据
        Assert.assertTrue(list.size()==9);
    }

    @Test
    public void testSelectScore2(){
        List<Student> list=new Select().selectScore(60,1);
        System.out.println(list.size());
        //是否有9行数据
        Assert.assertTrue(list.size()==9);
    }
}
