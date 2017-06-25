package com.liang.graduationProject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.model.*;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
@Service("postService")
public class PostService extends GenericService{

    public DataResult addOrUpdatePost(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        Post post1 = JSON.parseObject(jsonObject.getString("post"),Post.class);
        String loginId = jsonObject.getString("loginId");
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(post1.getUserId());
        User user = this.getDaoFacade().getUserRepository().findByUserId(post1.getUserId());
        if ( null == post1.getPostId() || "".equals(post1.getPostId()) ) {
            System.out.println("添加帖子post1.getPostId() :"+post1.getPostId());
            if ( null == post1.getSectionName() || "".equals(post1.getSectionName().trim())) {
                data.setErrorCode("400");
                data.setReason("请选择版块");
                return data;
            }
            if ( null != manager) {
                post1.setPostType(1);
            }else if ( null != user) {
                post1.setPostType(2);
            }else {
                data.setErrorCode("400");
                data.setReason("请登陆再操作");
                return data;
            }
            Post post2 = this.getDaoFacade().getPostRepository().findByPostName(post1.getPostName());
            if ( null != post2) {
                data.setErrorCode("400");
                data.setReason("该帖子已存在");
                return data;
            }
            Section section = this.getDaoFacade().getSectionRepository().findBySectionName(post1.getSectionName());
            if ( null == section) {
                data.setErrorCode("400");
                data.setReason("该板块不存在，请选择版块");
            }
            post1.setCreateTime(new Date());
            Manager manager1 = this.getDaoFacade().getManagerRepository().findOne(post1.getUserId());
            User user1 = this.getDaoFacade().getUserRepository().findOne(post1.getUserId());
            if ( null != manager1)
                post1.setUserName(manager1.getManagerName());
            if ( null != user1 )
                post1.setUserName(user1.getNickName());
            Post post3 = this.getDaoFacade().getPostRepository().save(post1);
            data.setErrorCode("200");
            data.setReason("添加成功");
            data.setResult(post3);
        }else {
            System.out.println("修改帖子post1.getPostId() :"+post1.getPostId());
            Post post2 = this.getDaoFacade().getPostRepository().findOne(post1.getPostId());
            if ( null == post2) {
                data.setErrorCode("400");
                data.setReason("该帖子不存在");
            }else {
                Manager manager1 = this.getDaoFacade().getManagerRepository().findOne(loginId);
                User user1 = this.getDaoFacade().getUserRepository().findOne(loginId);
                Post post = this.getDaoFacade().getPostRepository().findOne(post1.getPostId());
                if ( null != manager1) {
                    if ( null != post1.getPostName() && !"".equals(post1.getPostName().trim()))
                        post.setPostName(post1.getPostName().trim());
                    if ( null != post1.getDescribe() && !"".equals(post1.getDescribe().trim()))
                        post.setDescribe(post1.getDescribe().trim());
                    if ( null != post1.getContent() && !"".equals(post1.getContent().trim()))
                        post.setContent(post1.getContent().trim());
                    if ( null != post1.getSectionName() && !"".equals(post1.getSectionName().trim()))
                        post.setSectionName(post1.getSectionName().trim());
                    if ( !"".equals(post1.getPostType()) && ( post1.getPostType() == 1 || post1.getPostType() == 2))
                        post.setPostType(post1.getPostType());
                }else if ( null != user1) {
                    if ( null != post1.getPostName() && !"".equals(post1.getPostName().trim()))
                    post.setPostName(post1.getPostName().trim());
                    if ( null != post1.getDescribe() && !"".equals(post1.getDescribe().trim()))
                        post.setDescribe(post1.getDescribe().trim());
                    if ( null != post1.getContent() && !"".equals(post1.getContent().trim()))
                        post.setContent(post1.getContent().trim());
                }else {
                    data.setErrorCode("400");
                    data.setReason("请登陆再操作");
                    return data;
                }

                Post post3 = this.getDaoFacade().getPostRepository().save(post);
                data.setErrorCode("200");
                data.setReason("修改成功");
                data.setResult(post3);
            }
        }

        return data;
    }

    public DataResult queryPostList(String jsonData) {
        DataResult data = new DataResult();
        JSONObject json = JSON.parseObject(jsonData);
        String loginId = json.getString("loginId");
        String sectionName = json.getString("sectionName");
        int pageIndex = Integer.parseInt(json.getString("pageIndex"));
        int pageSize = Integer.parseInt(json.getString("pageSize"));
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        User user = this.getDaoFacade().getUserRepository().findOne(loginId);
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        ArrayList dataList  = new ArrayList();
        List<Section> sectionArrayList = null;
        Page<Post> postPage = null;
        if ( null != manager) {
            if ( manager.getManagerLevel() == 3) {
                sectionArrayList = new ArrayList<Section>();
                Section section = new Section();
                section.setSectionName(manager.getSectionName());
                sectionArrayList.add(section);
                postPage = this.getDaoFacade().getPostRepository().findBySectionName(manager.getSectionName(),pageRequest);
                data.setErrorCode("200");
                data.setReason("查询成功");
                dataList.add(sectionArrayList);
                dataList.add(postPage);
                data.setResult(dataList);
            }else {
                sectionArrayList = this.getDaoFacade().getSectionRepository().findAll(1);
                if ( null == sectionName || "".equals(sectionName.trim())) {
                    Section section = this.getServiceFacade().getSectionService().findFirst();
                    postPage = this.getDaoFacade().getPostRepository().findBySectionName(section.getSectionName(),pageRequest);
                }
                else {
                    postPage = this.getDaoFacade().getPostRepository().findBySectionName(sectionName,pageRequest);
                }
                data.setErrorCode("200");
                data.setReason("查询成功");
                dataList.add(sectionArrayList);
                dataList.add(postPage);
                data.setResult(dataList);
            }
        }else {
            if ( null != sectionName){
                List<Post> topPosts = this.getDaoFacade().getPostRepository().findBySectionNameAndPostType(sectionName,1);
                Page<Post> postPage1 = this.getDaoFacade().getPostRepository().findBySectionNameAndPostType(sectionName,2,pageRequest);
                List<Post> postList = postPage1.getContent();
                dataList.add(topPosts);
                dataList.add(postList);
                data.setErrorCode("200");
                data.setReason("查询成功");
                data.setResult(dataList);
            }else{
                List<Section> sections = this.getDaoFacade().getSectionRepository().findAll(1);
                List<Post> topPosts = this.getDaoFacade().getPostRepository().findBySectionNameAndPostType(sections.get(0).getSectionName(),1);
                Page<Post> postPage1 = this.getDaoFacade().getPostRepository().findBySectionNameAndPostType(sections.get(0).getSectionName(),2,pageRequest);
                List<Post> postList = postPage1.getContent();
                dataList.add(topPosts);
                dataList.add(postList);
                data.setErrorCode("200");
                data.setReason("查询成功");
                data.setResult(dataList);
            }
        }
        return data;
    }

    public DataResult deletePost(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String loginId = jsonObject.getString("loginId").trim();
        String postId = jsonObject.getString("postId").trim();
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        User user = this.getDaoFacade().getUserRepository().findByUserId(loginId);
        Post post = this.getDaoFacade().getPostRepository().findOne(postId);
        if ( null != manager) {
            if ( manager.getManagerLevel() == 3) {
                if ( manager.getSectionName().trim().equals(post.getSectionName().trim())) {
                    data.setReason("400");
                    data.setReason("只能删除自己管理的板块的帖子");
                }
            }else {
                List<Reply> replies = this.getDaoFacade().getReplyRepository().findByPostId(post.getPostId());
                for ( Reply reply : replies) {
                    this.getDaoFacade().getReplyRepository().delete(reply);
                }
                this.getDaoFacade().getPostRepository().delete(postId);
                data.setErrorCode("200");
                data.setReason("删除成功");
            }
        }else if ( null != user) {
            if ( null == post) {
                data.setErrorCode("400");
                data.setReason("该帖子不存在");
            }else if ( !loginId.equals(post.getUserId())) {
                data.setReason("只能删除自己的帖子");
                data.setErrorCode("400");
            }else {
                this.getDaoFacade().getPostRepository().delete(post);
                data.setErrorCode("200");
                data.setReason("删除成功");
            }
        }else {
            data.setErrorCode("400");
            data.setReason("请登陆再操作");
        }
        return data;
    }

    public DataResult queryPost(String postId) {
        DataResult data = new DataResult();
        Post post = this.getDaoFacade().getPostRepository().findOne(postId.trim());
        data.setReason("查询成功");
        data.setErrorCode("200");
        data.setResult(post);
        return data;
    }

    public DataResult checkByKey(String skey) {
        DataResult data = new DataResult();
        try {
            List<Post> postList = this.getDaoFacade().getPostRepository().findByPostNameLike(skey);
            data.setErrorCode("200");
            data.setReason("查找成功");
            data.setResult(postList);
        } catch (Exception e) {
            e.printStackTrace();
            data.setReason("查找成功");
            data.setErrorCode("400");
        }
        return data;
    }
}
