package cn.hncj.selection.mapper;

import cn.hncj.selection.bean.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> getAll();
}
