package cn.hncj.selection.controller;

import cn.hncj.selection.bean.Choose;
import cn.hncj.selection.bean.Course;
import cn.hncj.selection.bean.Student;
import cn.hncj.selection.bean.Teacher;
import cn.hncj.selection.entites.CommonResult;
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
    public Map<String, Object> choose(@RequestBody List<Course> courses, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<>();
        Student student = (Student) session.getAttribute("student");
        Choose choose = new Choose();
        List<Choose> chooses = chooseMapper.getAll();
        choose.setSnum(student.getNum());
        boolean flag = true;
        if (courses != null && courses.size() != 0) {
            for (Course course : courses) {
                choose.setCnum(course.getNum());
                if (chooses.contains(choose)) {
                    flag = false;
                    map.put("msg", "课程编号为" + choose.getCnum() + "的课程已经选啦");
                    map.put("code", 406);
                    return map;
                }
            }
            if(flag)
            {
                for (Course course : courses) {
                    choose.setCnum(course.getNum());
                    chooseMapper.insert(choose);
                }
                map.put("msg", "选课成功,是否查看已选课程");
                map.put("code", 200);
                return map;
            }
        }
        map.put("msg", "不能空选");
        map.put("code", 406);
        return map;
    }

    @GetMapping("/choose")
    public String choose(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        List<Course> chooses = chooseMapper.getBySNumPlus(student.getNum());
//        chooseMapper.getBySNum(student.getNum())
        model.addAttribute("chooses", chooses);
        return "choose";
    }

    @ResponseBody
    @DeleteMapping("/choose")
    public CommonResult choose(@RequestBody List<Course> courses) {
        List<Choose> chooses = chooseMapper.getAll();
        if (chooses.size() != 0 && chooses != null) {
            for (Course course : courses) {
                chooseMapper.deleteByNum(course.getNum());
            }
            return new CommonResult("删除成功",200);
        }
        return new CommonResult("没有课啦",406);
    }
}
