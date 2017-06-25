package com.liang.graduationProject.dao;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/29.
 */
@Repository(value = "daoFacade")
public class DaoFacade {
    @Resource
    @Getter
    private UserRepository userRepository;
    @Resource
    @Getter
    private CourseRepository courseRepository;
    @Resource
    @Getter
    private OutlineRepository outlineRepository;
    @Resource
    @Getter
    private TeacherRepository teacherRepository;
    @Resource
    @Getter
    private ManagerRepository managerRepository;
    @Resource
    @Getter
    private SectionRepository sectionRepository;
    @Resource
    @Getter
    private PostRepository postRepository;
    @Resource
    @Getter
    private ReplyRepository replyRepository;
}
