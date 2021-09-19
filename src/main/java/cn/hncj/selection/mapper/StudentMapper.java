package cn.hncj.selection.mapper;

import cn.hncj.selection.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    Student getStudent(@Param("num") Integer num, @Param("password") String password);
}
