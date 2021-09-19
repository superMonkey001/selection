package cn.hncj.selection.mapper;

import cn.hncj.selection.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    Teacher getTeacherById(Integer id);
}
