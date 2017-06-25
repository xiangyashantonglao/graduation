package com.liang.graduationProject.model;

import lombok.Data;

import java.util.ArrayList;

/**
 * Created by admin on 2017/4/14.
 */
@Data
public class IndexModel {
    private String[] courseName = {"软件学院","设计学院","产品运营"};
    private ArrayList<ArrayList> coursesArray= new ArrayList<ArrayList>();
}
