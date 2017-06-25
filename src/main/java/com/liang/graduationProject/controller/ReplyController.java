package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/service/replyController")
public class ReplyController {
    @Resource
    private ServiceFacade serviceFacade;
    @RequestMapping(value = "/queryReplyList", method = RequestMethod.POST)
    public JSONObject queryReplyList(@RequestParam String jsonData) {
        System.out.println("queryReplyList:" +jsonData);
        DataResult data = this.serviceFacade.getReplyService().queryReplyList(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping( value = "/addReply",method = RequestMethod.POST)
    public JSONObject addReply(@RequestParam String jsonData) {
        System.out.println("addReply:"+jsonData);
        DataResult data = this.serviceFacade.getReplyService().addReply(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
    public JSONObject deleteReply(@RequestParam String jsonData) {
        System.out.println("deleteReply:" +jsonData);
        DataResult data = this.serviceFacade.getReplyService().deleteReply(jsonData);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }
}
