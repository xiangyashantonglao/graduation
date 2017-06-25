package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by admin on 2017/3/28.
 */
@Data
@Document(collection = "managers")
public class Manager {
    @Id
    private String managerId;
    @Field
    private String managerName;
    @Field
    private int managerLevel;
    @Field
    private String password;
    @Field
    private String sectionName;
}
