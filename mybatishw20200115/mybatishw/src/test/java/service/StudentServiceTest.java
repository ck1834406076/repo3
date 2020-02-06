package service;

import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;
import service.impl.StudentServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {
 ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/applicationContext-*");
    @Test
    public void query() {
        StudentService service = ctx.getBean(StudentService.class);
        System.out.println(service);
        List<Student> list = service.query(null,1);
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void delete() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }
}