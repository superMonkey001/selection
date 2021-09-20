package cn.hncj.selection.controller;

import cn.hncj.selection.bean.Course;
import cn.hncj.selection.bean.Student;
import cn.hncj.selection.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    StudentMapper studentMapper;
    @GetMapping(value = {"/","/login"})
    public String index(){
        return "index";
    }
    @PostMapping(value = {"/","/login"})
    public String login(HttpServletRequest request, Student student, Model model){
        HttpSession session = request.getSession();
        Student dbStudent = studentMapper.getStudent(student.getNum(), student.getPassword());
        if (dbStudent == null) {
            model.addAttribute("error", "学号或密码错误");
            return "index";
        }
        session.setAttribute("student", student);
        return "redirect:/selection";
    }
}
