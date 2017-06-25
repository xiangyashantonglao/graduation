package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 2017/3/28.
 */
@Data
@Document(collection = "users")
public class User {

    //基本信息
    @Id
    private String userId;
    @Field
    private String nickName = "";  //昵称
    @Field
    private String password;
    @Field
    private String head;
    @Field
    private String phone;
    @Field
    private String email;
    @Field
    private String authCode;
    @Field
    private String sex;
    @Field
    private String birth;
    @Field
    private String address;
    @Field
    private String qqNum;
    @Field
    private String wechatNum;
    @Field
    private Date registerTime;
    @Field
    private ArrayList<String> myPosts;    //发表的帖子id
    @Field
    private ArrayList<String> myReply;    //回复的id
    @Field
    private ArrayList<String> myCourse;  //自己选的课程的id，包括试学

    public User() {
        this.head = "/demo2/user/img/personal/2_big.jpg";
    }
}
