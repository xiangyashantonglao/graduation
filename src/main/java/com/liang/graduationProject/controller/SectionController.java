package com.liang.graduationProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.ServiceFacade;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
@CrossOrigin
@RestController
@RequestMapping("/service/sectionController")
public class SectionController {
    @Resource
    private ServiceFacade serviceFacade;

    @RequestMapping(value = "/addSection", method = RequestMethod.POST)
    public JSONObject addSection(@RequestParam String section) {
        System.out.println("addSection: " +section+"............................."+new Date());
        DataResult data = this.serviceFacade.getSectionService().addSection(section);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }

    @RequestMapping(value = "/deleteSection", method = RequestMethod.POST)
    public JSONObject deleteSection(@RequestParam String sectionId) {
        System.out.println("deleteSection: "+sectionId+"............................."+new Date());
        DataResult data = this.serviceFacade.getSectionService().deleteSection(sectionId);
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return a;
    }
//
//    @RequestMapping(value = "/updateSection", method = RequestMethod.POST)
//    public JSONObject updateSection(@RequestParam String section)
//    {
//        DataResult data = this.serviceFacade.getSectionService().updateSection(section);
//        JSONObject a = FastJsonUtils.beanToJson2(data);
//        return a;
//    }

    @RequestMapping(value = "/querySectionList", method = RequestMethod.POST)
    public JSONObject querySectionList() {
        System.out.println("/querySectionList:");
        DataResult data = this.serviceFacade.getSectionService().querySectionList();
        JSONObject a = FastJsonUtils.beanToJson2(data);
        return  a;
    }
}
