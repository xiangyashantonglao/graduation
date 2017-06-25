package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/service/userRegisterController")
public class UserRegisterController {
    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/checkName",method = RequestMethod.POST)
    public JSONObject checkName(@RequestParam String jsonData) {
        System.out.println("checkName:"+jsonData);
        DataResult data = this.serviceFacade.getUserService().checkName(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JSONObject userRegister(@RequestParam String jsonData) {
        System.out.println("register:"+jsonData);
        DataResult data = this.serviceFacade.getUserService().UserRegister(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject userLogin(@RequestParam String jsonData) {
        System.out.println("login: "+jsonData);
        DataResult data = this.serviceFacade.getUserService().userLogin(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public DataResult test() {
        DataResult data = new DataResult();
//        this.serviceFacade.getFileService().uploadHead(head);
        return data;
    }

}
