package com.liang.graduationProject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.model.Manager;
import com.liang.graduationProject.model.Reply;
import com.liang.graduationProject.model.User;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
@Service("replyService")
public class ReplyService extends GenericService {
    public DataResult queryReplyList(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String postId = jsonObject.getString("postId");
        int pageIndex = Integer.parseInt(jsonObject.getString("pageIndex"));
        int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<Reply> replyPage = this.getDaoFacade().getReplyRepository().findByPostId(postId,pageRequest);
        data.setReason("请求成功");
        data.setErrorCode("200");
        data.setResult(replyPage);
        return data;
    }

    public DataResult addReply(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String loginId = jsonObject.getString("loginId").trim();
        Reply newReply = JSON.parseObject(jsonObject.getString("newReply"),Reply.class);
        User user =this.getDaoFacade().getUserRepository().findByUserId(loginId);
        if ( null == user) {
            data.setReason("请登陆再回帖");
            data.setErrorCode("400");
        }else {
            newReply.setCreateTime(new Date());
            if ( null != user.getPhone() && !"".equals(user.getPhone()))
                newReply.setNickName(user.getPhone());
            if ( null != user.getEmail() && !"".equals(user.getEmail()))
                newReply.setNickName(user.getEmail());
            if ( null != user.getNickName() && !"".equals(user.getNickName()))
                newReply.setNickName(user.getNickName());
            Reply reply = this.getDaoFacade().getReplyRepository().save(newReply);
            data.setErrorCode("200");
            data.setReason("添加成功");
            data.setResult(reply);
        }
        return data;
    }

    public DataResult deleteReply(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String loginId = jsonObject.getString("loginId").trim();
        String replyId = jsonObject.getString("replyId").trim();
        User user = this.getDaoFacade().getUserRepository().findByUserId(loginId);
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        if ( null != user || null != manager) {
            this.getDaoFacade().getReplyRepository().delete(replyId);
            data.setReason("删除成功");
            data.setErrorCode("200");
        } else {
            data.setReason("您没有权限删除该回复");
            data.setErrorCode("400");
        }
        return data;
    }
}
