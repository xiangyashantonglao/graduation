package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/5/13/013.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/service/teacherController")
public class TeacherController {

    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/listTeacher", method = RequestMethod.POST)
    public JSONObject listTeacher(String jsonData){
        DataResult data = this.serviceFacade.getTeacherService().listTeacher(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/getTeacher", method = RequestMethod.POST)
    public JSONObject getTeacher(String loginId, String teacherId) {
        DataResult data = this.serviceFacade.getTeacherService().getTeacher(loginId,teacherId);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/deleteTeacher", method = RequestMethod.POST)
    public JSONObject deleteTeacher(String loginId, String teacherId) {
        DataResult data = this.serviceFacade.getTeacherService().deleteTeacher(loginId, teacherId);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/insertOrUpdateTeacher", method = RequestMethod.POST)
    public JSONObject insertOrUpdateTeacher(String jsonData) {
        DataResult data = this.serviceFacade.getTeacherService().insertOrUpdateTeacher(jsonData);

        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }
}
