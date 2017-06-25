package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/4/14.
 */
@Service("teacherRepository")
public interface TeacherRepository extends MongoRepository<Teacher,String> {
    Teacher findByTeacherId(String teacherId);
}
