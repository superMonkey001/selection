package cn.hncj.selection.controller;

import cn.hncj.selection.bean.Student;
import cn.hncj.selection.mapper.ChooseMapper;
import cn.hncj.selection.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    ChooseMapper chooseMapper;
    @ResponseBody
    @GetMapping("/student")
    public List<Student> student(@RequestParam("num") Integer cnum){
        List<Student> students = chooseMapper.getStudentByCNum(cnum);
        return students;
    }
}
