package com.liang.graduationProject.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;


/**
 * Created by admin on 2017/3/28.
 */
@Data
@Document(collection = "courses")
public class Course {
    @Id
    private String courseId;
    @Field
    private String courseName;
    @Field
    private int courseType;
    @Field
    private String introduction; //简介
    @Field
    private ArrayList<String> baseOutline;//基础大纲
    @Field
    private ArrayList<String> workUrl;//学生作品
    @Field
    private String outlineId;   //详细大纲
    @Field
    private ArrayList<String> teacherId;//教师
    @Field
    private String[] videoName;
}
