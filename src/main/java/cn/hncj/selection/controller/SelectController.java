package cn.hncj.selection.controller;

import cn.hncj.selection.bean.Course;
import cn.hncj.selection.bean.Student;
import cn.hncj.selection.bean.Teacher;
import cn.hncj.selection.mapper.CourseMapper;
import cn.hncj.selection.mapper.StudentMapper;
import cn.hncj.selection.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SelectController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @PostMapping("/selection")
    public String selection(Student student, HttpServletRequest request){
//        Student dbStudent = studentMapper.getStudent(student.getNum());
        Student dbStudent = studentMapper.getStudent(student.getNum(), student.getPassword());
        if (dbStudent==null)
        {   request.getSession().setAttribute("error","学号或密码错误");
            return "redirect:/";
        }
        HttpSession session = request.getSession();
        session.setAttribute("student",student);
        List<Course> courses = courseMapper.getAll();
        for (Course course : courses) {
            course.setTeacher(teacherMapper.getTeacherById(course.getTeacherId()));
        }
        session.setAttribute("courses",courses);
        return "selection";
    }
    @GetMapping("/selection")
    public String selection2(HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Course> courses = courseMapper.getAll();
        for (Course course : courses) {
            course.setTeacher(teacherMapper.getTeacherById(course.getTeacherId()));
        }
        session.setAttribute("courses",courses);
        return "selection";
    }
}
