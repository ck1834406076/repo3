package mapper;

import pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student>query(Student student);

    void delete(String id);

    void add(Student student);

    void update(Student student);
}
