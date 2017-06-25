package com.liang.graduationProject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.model.Manager;
import com.liang.graduationProject.model.Teacher;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.utils.CommonConsts;
import com.liang.graduationProject.utils.FastJsonUtils;
import com.liang.graduationProject.utils.HeadUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/13/013.
 */
@Service("teacherService")
public class TeacherService extends GenericService {

    public DataResult listTeacher(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String loginId = jsonObject.getString("loginId");
        int pageIndex = Integer.parseInt(jsonObject.getString("pageIndex"));
        int pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);

        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);

        if ( null == manager ) {
            data.setErrorCode("400");
            data.setReason("请登陆再操作");
        }else {
            if ( 2 == manager.getManagerLevel() || 3 == manager.getManagerLevel()) {
                data.setErrorCode("400");
                data.setReason("您的权限不足");
            }else if (1 == manager.getManagerLevel()){
                Page<Teacher> teacherPage = this.getDaoFacade().getTeacherRepository().findAll(pageRequest);
                data.setErrorCode("200");
                data.setReason("查询成功");
                data.setResult(teacherPage);
            }
        }
        return data;
    }

    public DataResult getTeacher( String loginId, String teacherId) {
        DataResult data = new DataResult();

        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);

        if ( null == manager ) {
            data.setErrorCode("400");
            data.setReason("请登陆再操作");
        }else {
            if ( 2 == manager.getManagerLevel() || 3 == manager.getManagerLevel()) {
                data.setErrorCode("400");
                data.setReason("您的权限不足");
            }else if (1 == manager.getManagerLevel()){
                Teacher teacher = this.getDaoFacade().getTeacherRepository().findByTeacherId(teacherId);
                data.setErrorCode("200");
                data.setReason("查询成功");
                data.setResult(teacher);
            }
        }
        return data;
    }

    public DataResult deleteTeacher(String loginId, String teacherId) {
        DataResult data = new DataResult();

        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);

        if ( null == manager ) {
            data.setErrorCode("400");
            data.setReason("请登陆再操作");
        }else {
            if ( 2 == manager.getManagerLevel() || 3 == manager.getManagerLevel()) {
                data.setErrorCode("400");
                data.setReason("您的权限不足");
            }else if (1 == manager.getManagerLevel()){
                this.getDaoFacade().getTeacherRepository().delete(teacherId);
                data.setErrorCode("200");
                data.setReason("删除成功");
            }
        }
        return data;
    }

    public DataResult insertOrUpdateTeacher( String jsonData) {
        DataResult data = new DataResult();

        JSONObject jsonObject = FastJsonUtils.textToJson(jsonData);

        String loginId = jsonObject.getString("loginId");
        String teacherString = jsonObject.getString("teacher");
        String headPicture = jsonObject.getString("headPicture");
        String poster = jsonObject.getString("poster");

        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        Teacher teacher = JSON.parseObject(teacherString,Teacher.class);

        if ( null == manager ) {
            data.setErrorCode("400");
            data.setReason("请登陆再操作");
        }else {

            if ( 2 == manager.getManagerLevel() || 3 == manager.getManagerLevel()) {
                data.setErrorCode("400");
                data.setReason("您的权限不足");
            }else if (1 == manager.getManagerLevel()){

                //添加讲师
                if ( null == teacher.getTeacherId() || "".equals(teacher.getTeacherId())) {

                    Teacher teacher1 = this.getDaoFacade().getTeacherRepository().save(teacher);

                    if ( null != headPicture) {
                        String headUrl = CommonConsts.TEACHER_HEAD_URL + teacher1.getTeacherId()+".jpg";
                        String setHeadUrl = CommonConsts.SET_TEACHER_HEAD_URL + teacher1.getTeacherId() +".jpg";
                        HeadUtils.saveHeadForBase64(headUrl,headPicture);
                        teacher1.setHeadUrl(setHeadUrl);
                    }

                    if ( null != poster) {
                        String pictureUrl = CommonConsts.TEACHER_HEAD_URL + teacher1.getTeacherId()+"-banner.jpg";
                        String setPictureUrl = CommonConsts.SET_TEACHER_HEAD_URL + teacher1.getTeacherId() +"-banner.jpg";

                        HeadUtils.saveHeadForBase64(pictureUrl,poster);
                        teacher1.setPictureUrl(setPictureUrl);
                    }

                    this.getDaoFacade().getTeacherRepository().save(teacher1);
                    data.setErrorCode("200");
                    data.setReason("新增成功");
                    data.setResult(teacher1);

                }else {//修改讲师
                    Teacher teacher1 = this.getDaoFacade().getTeacherRepository().findByTeacherId(teacher.getTeacherId());
                    if ( null != teacher1) {

                        if ( null != teacher.getCourseName()) {
                            teacher1.setCourseName(teacher.getCourseName());
                        }

                        if ( null != teacher.getKeyWord()) {
                            teacher1.setKeyWord(teacher.getKeyWord());
                        }

                        if ( null != teacher.getTeachName()) {
                            teacher1.setTeachName(teacher.getTeachName());
                        }

                        if ( null != teacher.getIntroduction()) {
                            teacher1.setIntroduction(teacher.getIntroduction());
                        }

                        if ( 0 != teacher.getTeacherType()) {
                            teacher1.setTeacherType(teacher.getTeacherType());
                        }

                        if ( null != headPicture && !"".equals(headPicture)) {
                            String headUrl = CommonConsts.TEACHER_HEAD_URL + teacher1.getTeacherId()+".jpg";
                            String setHeadUrl = CommonConsts.SET_TEACHER_HEAD_URL + teacher1.getTeacherId() +".jpg";
                            HeadUtils.saveHeadForBase64(headUrl,headPicture);
                            teacher1.setHeadUrl(setHeadUrl);
                        }

                        if ( null != poster && !"".equals(poster)) {
                            String pictureUrl = CommonConsts.TEACHER_HEAD_URL + teacher1.getTeacherId()+"-banner.jpg";
                            String setPictureUrl = CommonConsts.SET_TEACHER_HEAD_URL + teacher1.getTeacherId() +"-banner.jpg";

                            HeadUtils.saveHeadForBase64(pictureUrl,poster);
                            teacher1.setPictureUrl(setPictureUrl);
                        }

                        this.getDaoFacade().getTeacherRepository().save(teacher1);
                        data.setErrorCode("200");
                        data.setReason("修改成功");
                        data.setResult(teacher1);
                    }else {
                        data.setErrorCode("400");
                        data.setReason("该讲师不存在");
                    }

                }

            }
        }

        return data;
    }


}
