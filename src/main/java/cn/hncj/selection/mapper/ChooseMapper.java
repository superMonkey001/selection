package cn.hncj.selection.mapper;

import cn.hncj.selection.bean.Choose;
import cn.hncj.selection.bean.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChooseMapper {
    int insert (Choose choose);
    List<Choose> getAll();
    List<Course> getBySNum(Integer snum);
    List<Course> getBySNumPlus(Integer snum);
}
