package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String postId;
    @Field
    private String postName;
    @Field
    private String describe;
    @Field
    private String content;//帖子内容
    @Field
    private String sectionName;
    @Field
    private String userId;
    @Field
    private Date createTime;
    @Field
    private String userName;
    @Field
    private int postType;  //1置顶帖 2普通贴
}
