package com.liang.graduationProject.service;

import com.alibaba.fastjson.JSON;
import com.liang.graduationProject.model.Post;
import com.liang.graduationProject.model.Reply;
import com.liang.graduationProject.model.Section;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
@Service("sectionService")
public class SectionService extends GenericService{
    public DataResult addSection(String section) {
        DataResult data = new DataResult();
        Section section1 = JSON.parseObject(section,Section.class);
        if ( null == section1.getSectionId() || "".equals(section1.getSectionId())) {
            try {
                Section section2 = this.getDaoFacade().getSectionRepository().findBySectionName(section1.getSectionName());
                if ( null == section2) {
                    section1.setSectionId(null);
                    Section section3 = this.getDaoFacade().getSectionRepository().save(section1);
                    data.setErrorCode("200");
                    data.setReason("添加板块成功");
                    data.setResult(section3);
                }else {
                    data.setReason("该板块已存在");
                    data.setErrorCode("400");
                    return data;
                }
            } catch (Exception e) {
                e.printStackTrace();
                data.setReason("系统正在维护，请稍后再试");
                data.setErrorCode("400");
            }
        }else {
            try {
                Section section2 = this.getDaoFacade().getSectionRepository().findOne(section1.getSectionId());
                if (section2 == null) {
                    data.setReason("该板块不存在");
                    data.setErrorCode("400");
                    return data;
                }
                Section section3 = this.getDaoFacade().getSectionRepository().findBySectionName(section1.getSectionName());
                if ( null != section3) {
                    data.setErrorCode("400");
                    data.setReason("该名字已存在，请换一个名字");
                    return data;
                }
                Section section4 = this.getDaoFacade().getSectionRepository().save(section1);
                data.setReason("修改成功");
                data.setErrorCode("200");
                data.setResult(section4);
            } catch (Exception e) {
                e.printStackTrace();
                data.setErrorCode("400");
                data.setReason("系统正在维护，请稍后再试");
            }
        }

        return data;
    }

    public DataResult deleteSection(String sectionId) {
        DataResult data = new DataResult();
        try {
            Section section = this.getDaoFacade().getSectionRepository().findOne(sectionId);
            List<Post> posts = this.getDaoFacade().getPostRepository().findBySectionName(section.getSectionName());
            for (Post post :posts){
                List<Reply> replies = this.getDaoFacade().getReplyRepository().findByPostId(post.getPostId());
                for ( Reply reply : replies) {
                    this.getDaoFacade().getReplyRepository().delete(reply);
                }
                this.getDaoFacade().getPostRepository().delete(post.getPostId());
            }
            this.getDaoFacade().getSectionRepository().delete(section);
            data.setReason("板块删除成功");
            data.setErrorCode("200");
        } catch (Exception e) {
            e.printStackTrace();
            data.setErrorCode("400");
            data.setReason("系统正在维护，请稍后再试");
        }
        return data;
    }

    public DataResult querySectionList() {
        DataResult data = new DataResult();
        try {
            ArrayList dataList = new ArrayList();
            List<Section> sectionArrayList = this.getDaoFacade().getSectionRepository().findAll(1);
            List topPost = new ArrayList<>();
            for (Section section :sectionArrayList) {
                List<Post> posts = this.getDaoFacade().getPostRepository().findBySectionNameAndPostType(section.getSectionName(),1);
                topPost.add(posts);
            }
            dataList.add(sectionArrayList);
            dataList.add(topPost);
            data.setErrorCode("200");
            data.setReason("列表查找成功");
            data.setResult(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public Section findFirst() {
        Section section = this.getDaoFacade().getSectionRepository().findAll().get(0);
        return section;
    }
}
