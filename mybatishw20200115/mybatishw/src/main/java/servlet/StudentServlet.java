package servlet;



import com.github.pagehelper.PageInfo;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pojo.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;
import sun.security.util.AuthResources_it;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private  StudentServiceImpl studentService;
    

    @Override
    public void init() throws ServletException {
        ServletContext servletContext =getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        studentService= (StudentServiceImpl) ctx.getBean(StudentService.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String method = req.getParameter("method");
        Class clazz = this.getClass();
        StudentServlet obj = null;
        try {
            Method[] declaredMethods = clazz.getDeclaredMethods();
         /*   obj= (StudentServlet) clazz.newInstance();*/
            for (Method declaredMethod : declaredMethods) {
                declaredMethod.setAccessible(true);
                String methodName = declaredMethod.getName();
                if (methodName.equals(method)) {
                    declaredMethod.invoke(this, req, resp);
                }
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    protected void query(HttpServletRequest req,HttpServletResponse resp){
        Map<String, String[]> map = req.getParameterMap();
        String pageNo = req.getParameter("pageNo");
        Integer currentPage=null;
        if(pageNo!=null&&!"".equals(pageNo)){
            currentPage=Integer.valueOf(pageNo);
        }else {
            currentPage=1;
        }
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
            List<Student> list = studentService.query(student,currentPage);
            PageInfo<Student> pageInfo = new PageInfo<>(list);
            System.out.println(pageInfo);
            req.setAttribute("pageInfo",pageInfo);
            req.setAttribute("students",list);
            req.getSession().setAttribute("stuInfo",student);
            req.getRequestDispatcher("/manage.jsp").forward(req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    protected void delete(HttpServletRequest req,HttpServletResponse resp){
        String id = req.getParameter("id");
        studentService.delete(id);
        try {
            resp.sendRedirect("StudentServlet?method=query");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void add(HttpServletRequest req,HttpServletResponse resp){
        Map<String, String[]> map = req.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
            studentService.add(student);
            resp.sendRedirect("StudentServlet?method=query");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void SelectById(HttpServletRequest req,HttpServletResponse resp){
        String id = req.getParameter("id");
        Student student = new Student();
        student.setId(Integer.valueOf(id));
        List<Student> list = studentService.query(student,1);
        System.out.println(student);
        Student queryInfo = list.get(0);
        req.setAttribute("stu",queryInfo);
        try {
            req.getRequestDispatcher("/update.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void update(HttpServletRequest req,HttpServletResponse resp){
        Map<String, String[]> map = req.getParameterMap();
        Student student = new Student();
        try {
            BeanUtils.populate(student,map);
            studentService.update(student);
            resp.sendRedirect("StudentServlet?method=query");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
