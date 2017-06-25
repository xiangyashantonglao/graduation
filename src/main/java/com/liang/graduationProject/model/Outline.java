package com.liang.graduationProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

/**大纲
 * Created by admin on 2017/4/14.
 */
@Data
@Document(collection = "outlines")
public class Outline {
    @Id
    private String outlineId;
    @Field
    private ArrayList<LessionPeriod> lessionPeriods = new ArrayList<LessionPeriod>();
}
