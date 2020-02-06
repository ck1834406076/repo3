package service.impl;

import com.github.pagehelper.PageHelper;
import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Student;
import service.StudentService;
import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly =false)
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Student> query(Student student,Integer currentPage ) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(currentPage,3);
        List<Student> list = studentMapper.query(student);
        return list!=null&&list.size()>0?list:null;
    }

    @Override
    public void delete(String id) {

        studentMapper.delete(id);
        int i=1/0;
    }

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }
}
