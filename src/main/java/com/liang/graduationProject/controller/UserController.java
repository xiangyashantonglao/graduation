package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import com.liang.graduationProject.utils.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/service/userController")
public class UserController {
    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/userUpdate",method = RequestMethod.POST)
    public JSONObject userUpdate(@RequestParam String jsonData) {
        System.out.println("userUpdate: "+jsonData);
        DataResult data = this.serviceFacade.getUserService().userUpdate(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "bindingOrNot", method = RequestMethod.POST)
    public JSONObject bindingOrNot(@RequestParam String jsonData) {
        System.out.println("bindingOrNot: " + jsonData);
        DataResult data = this.serviceFacade.getUserService().bindingOrNot(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public JSONObject updatePassword( String phone, String authCode,String newPassword) {
        DataResult data = this.serviceFacade.getUserService().updatePassword(phone, authCode, newPassword);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/queryUserPage", method = RequestMethod.POST)
    public JSONObject queryUserPage(@RequestParam String jsonData) {
        DataResult data = this.serviceFacade.getUserService().queryUserPage(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/uploadHead", method = RequestMethod.POST)
    public JSONObject uploadHead(String loginId,String head) {
        System.out.println("loginId:"+loginId+"\nhead:"+head);
        DataResult data = this.serviceFacade.getFileService().uploadHead(loginId,head);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    private JSONObject getUser(String loginId, String userId){
        JSONObject a = FastJsonUtils.beanToJson2(this.serviceFacade.getUserService().getUser(loginId,userId));
        return a;
    }

    @RequestMapping( value = "/sendMessage", method = RequestMethod.POST)
    private JSONObject sendMessage(String phone) {
        String authCode = RandomUtils.getAuthCode();
        JSONObject a = FastJsonUtils.beanToJson2(this.serviceFacade.getMessageService().sendMessage(phone,authCode));
        return a;
    }


    @RequestMapping( value = "/deleteUser", method = RequestMethod.POST)
    private JSONObject deleteUser(String loginId, String userId) {
        JSONObject a = FastJsonUtils.beanToJson2(this.serviceFacade.getUserService().deleteUser(loginId, userId));
        return a;
    }
}
