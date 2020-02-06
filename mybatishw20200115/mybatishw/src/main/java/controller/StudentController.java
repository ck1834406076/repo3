package controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Grade;
import pojo.Student;
import service.GradeService;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @RequestMapping("/query")
    public String query(Student student, Integer pageNo, Map map, HttpServletRequest req){
        Integer currentPage=null;
        if(pageNo!=null&&!"".equals(pageNo)){
            currentPage=Integer.valueOf(pageNo);
        }else {
            currentPage=1;
        }
        List<Student> list = studentService.query(student,currentPage);
        List<Grade> gradelist = gradeService.queryAll();
        req.getSession().setAttribute("gradelist",gradelist);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        map.put("pageInfo",pageInfo);
        map.put("students",list);
        map.put("stuInfo",student);
        return "manage";
    }

    @RequestMapping("delete")
    public String delete(String id){
        studentService.delete(id);
        return "redirect:query";
    }

    @RequestMapping("selectById")
    public String selectById(Student student,Map map){
        List<Student> list = studentService.query(student, 1);
        Student queryInfo = list.get(0);
        map.put("stu",queryInfo);
        return "forward:/update.jsp";
    }

    @RequestMapping("update")
    public String update(Student student){
        studentService.update(student);
        return "redirect:/student/query";
    }

    @RequestMapping(value = "add")
    public String add(Student student){
        studentService.add(student);
        return "redirect:/student/query";
    }
}
