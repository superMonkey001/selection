package cn.hncj.selection.controller;

import cn.hncj.selection.bean.Class;
import cn.hncj.selection.bean.Course;
import cn.hncj.selection.bean.Student;
import cn.hncj.selection.bean.Teacher;
import cn.hncj.selection.mapper.*;
import jdk.internal.dynalink.support.ClassMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class SelectController {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ChooseMapper chooseMapper;
    @Autowired
    ClassMapper classMapper;
    @GetMapping("/selection")
    public String getSelection(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Course> courses = courseMapper.getAll();
        for (Course course : courses) {
            Integer count = chooseMapper.getCourseCountByCNum(course.getNum());
            course.setTeacher(teacherMapper.getTeacherById(course.getTeacherId()));
            course.setCount(count);
        }
        List<Class> classes = classMapper.getAll();
        for (Class aClass : classes) {
            aClass.setFreeTime(new Timestamp(System.currentTimeMillis()+7*24*60*60*1000));
        }
        session.setAttribute("classes",classes);
        session.setAttribute("courses", courses);
        return "selection";
    }
}
