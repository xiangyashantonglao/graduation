package com.liang.graduationProject.model;

import com.liang.graduationProject.model.Outline;
import com.liang.graduationProject.model.Teacher;
import lombok.Data;

import java.util.ArrayList;

/**
 * Created by admin on 2017/4/14.
 */
@Data
public class NetCourseModel {
    private String courseId;
    private String courseName;
    private String introduction; //简介
    private ArrayList<String> baseOutline = new ArrayList<String>();//基础大纲
    private ArrayList<String> workUrl = new ArrayList<String>();//学生作品
    private Outline outline;   //详细大纲
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();//教师
}
