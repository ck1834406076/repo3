package utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.Contended;

import java.io.InputStream;
public class MyBatisUtil {
    static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<>();
    static private SqlSessionFactory factory;
    private static SqlSession sqlSession;
    static {
        /*读取配置文件*/
        InputStream inputStream = MyBatisUtil.class.getClassLoader().getResourceAsStream("mybatis/mybatis-config.xml");
        /*获取sqlSessionFactory*/
        factory = new SqlSessionFactoryBuilder().build(inputStream);

    }
    public static SqlSession getSession(){
        sqlSession = threadLocal.get();
        if(sqlSession==null){
           sqlSession  = factory.openSession();
           threadLocal.set(sqlSession);
        }
        return threadLocal.get();
    }
    public static void close(){
        if(sqlSession!=null){
            sqlSession.close();
            threadLocal.remove();
        }
    }
}
