package cn.hncj.selection.controller;

import cn.hncj.selection.bean.Choose;
import cn.hncj.selection.bean.Course;
import cn.hncj.selection.bean.Student;
import cn.hncj.selection.bean.Teacher;
import cn.hncj.selection.mapper.ChooseMapper;
import cn.hncj.selection.mapper.CourseMapper;
import cn.hncj.selection.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChooseController {
    @Autowired
    ChooseMapper chooseMapper;
    @Autowired
    CourseMapper courseMapper;
    @ResponseBody
    @PostMapping("/choose")
//    @RequestMapping(value="/choose",method = RequestMethod.POST)
    public Map<String,Object> choose(@RequestBody Course course, HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        Choose choose = new Choose();
        choose.setCnum(course.getNum());
        choose.setSnum(student.getNum());
        List<Choose> chooses = chooseMapper.getAll();
        for (Choose dbChoose : chooses) {
            if (dbChoose.equals(choose)) {
                Map<String, Object> map = new HashMap<>();
                map.put("msg","有课程你已经选过,是否查看已选课程");
                map.put("code",406);
                return map;
            }
        }
        chooseMapper.insert(choose);
        Map<String, Object> map = new HashMap<>();
        map.put("msg","选课成功,是否查看已选课程");
        map.put("code",200);
        return map;
    }

    @GetMapping("/choose")
    public String choose(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        List<Course> chooses = chooseMapper.getBySNumPlus(student.getNum());
        model.addAttribute("chooses",chooses);
        return "choose";
    }
}
