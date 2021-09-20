package cn.hncj.selection.bean;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
@Data
public class Class {
    private String num;
    private Integer capacity;
    private Timestamp freeTime;
    private String courseName;
}
