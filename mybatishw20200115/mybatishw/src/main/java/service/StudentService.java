package service;

import com.github.pagehelper.Page;
import pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student>query(Student student,Integer currentPage);

    void delete(String id);

    void add(Student student);

    void update(Student student);
}
