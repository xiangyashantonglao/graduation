package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/4/18.
 */
@RestController
@CrossOrigin
@RequestMapping("/service/managerController")
public class ManagerController {
    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/managerLogin", method = RequestMethod.POST)
    public JSONObject managerLogin(@RequestParam String jsonData) {
        System.out.println("managerLogin: "+jsonData);
        DataResult data = this.serviceFacade.getManagerService().managerLogin(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/addOrAuthManager", method = RequestMethod.POST)
    public JSONObject addOrAuthManager(@RequestParam String managerId, String manager) {
        System.out.println("addOrAuthManager:      managerId: "+managerId+"   manager：  "+manager);
        DataResult data = this.serviceFacade.getManagerService().addOrAuthManager(managerId,manager);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "deleteManager", method = RequestMethod.POST)
    public JSONObject deleteManager(@RequestParam String deleteManagerId, String managerId) {
        DataResult data = this.serviceFacade.getManagerService().deleteManager(deleteManagerId,managerId);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/updateManager", method = RequestMethod.POST)
    public JSONObject updateManager(@RequestParam String manager) {
        System.out.println("updateManager:  "+manager);
        DataResult data = this.serviceFacade.getManagerService().updateManager(manager);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/queryManagerList", method = RequestMethod.POST)
    public JSONObject queryManagerList(@RequestParam String managerId) {
        System.out.println("queryManagerList:   "+managerId);
        DataResult data = this.serviceFacade.getManagerService().queryManagerList(managerId);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }
}
