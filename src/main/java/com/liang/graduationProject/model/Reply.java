package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
@Data
@Document(collection = "replys")
public class Reply {
    private String replyId;
    private String postId;
    private String userId;
    private String content;
    private Date createTime;
    private String nickName;

}
