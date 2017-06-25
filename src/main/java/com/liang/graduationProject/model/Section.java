package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 版块
 * Created by admin on 2017/3/28.
 */
@Data
@Document(collection = "sections")
public class Section {
    @Id
    private String sectionId;
    @Field
    private String sectionName;
    @Field
    private String describe;  //版块的简述
    @Field
    private String managerName;
}
