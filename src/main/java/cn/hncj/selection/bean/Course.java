package cn.hncj.selection.bean;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Course {
    private Integer num;
    private String name;
    private Integer teacherId;
    private Integer score;
    private Integer transcript;
    private Teacher teacher;
    private String teacherName;
}
