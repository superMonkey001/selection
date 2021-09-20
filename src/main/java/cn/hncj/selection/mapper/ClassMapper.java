package cn.hncj.selection.mapper;

import cn.hncj.selection.bean.Class;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Class> getAll();
}
