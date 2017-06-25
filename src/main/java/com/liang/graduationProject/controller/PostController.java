package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/service/postController")
public class PostController {
    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/addOrUpdatePost", method = RequestMethod.POST)
    public JSONObject addOrUpdatePost(@RequestParam String jsonData) {
        System.out.println("addOrUpdatePost:   "+jsonData);
        DataResult data = this.serviceFacade.getPostService().addOrUpdatePost(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/queryPostList", method = RequestMethod.POST)
    public JSONObject queryPostList(@RequestParam String jsonData) {
        System.out.println("queryPostList:  " +jsonData);
        DataResult data = this.serviceFacade.getPostService().queryPostList(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/deletePost", method = RequestMethod.POST)
    public JSONObject deletePost(String jsonData) {
        System.out.println("deletePost:  "+jsonData);
        DataResult data = this.serviceFacade.getPostService().deletePost(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/queryPost", method = RequestMethod.POST)
    public JSONObject queryPost(@RequestParam String postId) {
        System.out.println("queryPost:" + postId);
        DataResult data = this.serviceFacade.getPostService().queryPost(postId);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/checkByKey", method = RequestMethod.POST)
    public JSONObject checkByKey( String key) {
        DataResult data = this.serviceFacade.getPostService().checkByKey(key);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }
}
