package com.liang.graduationProject.service;

import com.liang.graduationProject.model.*;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/4/13 0013.
 */
@Service("courseService")
public class CourseService extends GenericService {
    public DataResult queryCourseList() {
        DataResult data = new DataResult();
        IndexModel indexModel = new IndexModel();
        try {
            List<Course> courses1 =  this.getDaoFacade().getCourseRepository().findByCourseType(1);
            ArrayList<NetCourse> netCourses1 = new ArrayList<NetCourse>();
            for (Course course : courses1) {
                NetCourse netCourse = new NetCourse();
                netCourse.setCourseId(course.getCourseId());
                netCourse.setCourseName(course.getCourseName());
                netCourses1.add(netCourse);
            }
            List<Course> courses2 =  this.getDaoFacade().getCourseRepository().findByCourseType(2);
            ArrayList<NetCourse> netCourses2 = new ArrayList<NetCourse>();
            for (Course course : courses2) {
                NetCourse netCourse = new NetCourse();
                netCourse.setCourseId(course.getCourseId());
                netCourse.setCourseName(course.getCourseName());
                netCourses2.add(netCourse);
            }
            List<Course> courses3 =  this.getDaoFacade().getCourseRepository().findByCourseType(3);
            ArrayList<NetCourse> netCourses3 = new ArrayList<NetCourse>();
            for (Course course : courses3) {
                NetCourse netCourse = new NetCourse();
                netCourse.setCourseId(course.getCourseId());
                netCourse.setCourseName(course.getCourseName());
                netCourses3.add(netCourse);
            }
            indexModel.getCoursesArray().add(netCourses1);
            indexModel.getCoursesArray().add(netCourses2);
            indexModel.getCoursesArray().add(netCourses3);
            data.setErrorCode("200");
            data.setReason("查询成功");
            data.setResult(indexModel);
        } catch (Exception e) {
            e.printStackTrace();
            data.setReason("系统正在维护，请稍后再试");
            data.setErrorCode("400");
        }
        return data;
    }

    public DataResult queryCourse(String courseId) {
        DataResult data = new DataResult();
        NetCourseModel netCourseModel = new NetCourseModel();
        try {
            Course course = this.getDaoFacade().getCourseRepository().findByCourseId(courseId);
            netCourseModel.setCourseId(course.getCourseId());
            netCourseModel.setCourseName(course.getCourseName());
            netCourseModel.setIntroduction(course.getIntroduction());
            netCourseModel.setBaseOutline(course.getBaseOutline());
            netCourseModel.setWorkUrl(course.getWorkUrl());
            Outline outline =this.getDaoFacade().getOutlineRepository().findByOutlineId(course.getOutlineId());
            netCourseModel.setOutline(outline);
            ArrayList<String> teacherIds = course.getTeacherId();
            for ( String teacherId : teacherIds ) {
                Teacher teacher = this.getDaoFacade().getTeacherRepository().findByTeacherId(teacherId);
                netCourseModel.getTeachers().add(teacher);
            }
            data.setErrorCode("200");
            data.setReason("查询成功");
            data.setResult(netCourseModel);
        } catch (Exception e) {
            e.printStackTrace();
            data.setReason("系统出错");
            data.setErrorCode("400");
        }
        return data;
    }

    public DataResult getVideoName(String courseName) {
        DataResult data = new DataResult();
        Course course = this.getDaoFacade().getCourseRepository().findByCourseName(courseName);
        data.setErrorCode("200");
        data.setReason("查询成功");
        data.setResult(course);
        return data;
    }

    public DataResult checkByKey(String skey) {
        DataResult data = new DataResult();
        try {
            List<Course> courseList = this.getDaoFacade().getCourseRepository().findByCourseNameLike(skey);
            data.setErrorCode("200");
            data.setReason("查找成功");
            data.setResult(courseList);
        } catch (Exception e) {
            e.printStackTrace();
            data.setReason("查找成功");
            data.setErrorCode("400");
        }
        return data;
    }
}
