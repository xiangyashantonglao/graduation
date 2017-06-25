package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/service/courseController")
public class CourseController {
    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/queryCourseList", method = RequestMethod.POST)
    public JSONObject queryCourseList() {
        System.out.println("queryCourseList:");
        DataResult data = this.serviceFacade.getCourseService().queryCourseList();
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/queryCourse", method = RequestMethod.POST)
    public JSONObject queryCourse(@RequestParam String courseId) {
        System.out.println("queryCourse:"+courseId);
        JSONObject a = FastJsonUtils.beanToJson2(this.serviceFacade.getCourseService().queryCourse(courseId));
        return a;
    }

    @RequestMapping(value = "/getVideoName", method = RequestMethod.POST)
    public JSONObject getVideoName(@RequestParam String courseName) {
        JSONObject a = FastJsonUtils.beanToJson2(this.serviceFacade.getCourseService().getVideoName(courseName));
        return a;
    }

    @RequestMapping( value = "/checkByKey", method = RequestMethod.POST)
    public JSONObject checkByKey( String key) {
        DataResult data = this.serviceFacade.getCourseService().checkByKey(key);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }
}
