package cn.hncj.selection;

import cn.hncj.selection.bean.Course;
import cn.hncj.selection.mapper.ChooseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class SelectionApplicationTests {
    @Autowired
    ChooseMapper chooseMapper;
    @Test
    void contextLoads() {
        List<Course> list = chooseMapper.getBySNumPlus(201912779);
        for (Course course : list) {
            System.out.println(course);
        }
    }

}
