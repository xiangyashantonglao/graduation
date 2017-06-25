package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Course;
import com.liang.graduationProject.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/14.
 */
@Service("courseRepository")
public interface CourseRepository extends MongoRepository<Course,String> {
    public Course findByCourseId(String courseId);
    public ArrayList<Course> findByCourseType(int type);
    @Query(fields = "{'courseName':1,'videoName':1, 'introduction':1}")
    Course findByCourseName(String courseName);
    List<Course> findByCourseNameLike(String key);
}
