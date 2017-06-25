package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by admin on 2017/3/28.
 */
@Data
@Document(collection = "teachers")
public class Teacher {
    @Id
    private String teacherId;
    @Field
    private String teachName;
    @Field
    private String keyWord;
    @Field
    private String courseName;
    @Field
    private String headUrl;  //头像地址
    @Field
    private String introduction;  //简述
    @Field
    private String pictureUrl;//图片地址
    @Field
    private int teacherType;  //1 金牌讲师，2普通讲师
}
