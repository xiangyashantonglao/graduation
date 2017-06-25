package com.liang.graduationProject.service;

import com.liang.graduationProject.dao.DaoFacade;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/29.
 */
@Service(value = "serviceFacade")
public class ServiceFacade {
    @Resource
    @Getter
    private UserService userService;
    @Resource
    @Getter
    private CourseService courseService;
    @Resource
    @Getter
    private SectionService sectionService;
    @Resource
    @Getter
    private ManagerService managerService;
    @Resource
    @Getter
    private PostService postService;
    @Resource
    @Getter
    private ReplyService replyService;
    @Resource
    @Getter
    private DaoFacade daoFacade;
    @Resource
    @Getter
    private MessageService messageService;
    @Resource
    @Getter
    private FileService fileService;
    @Resource
    @Getter
    private TeacherService teacherService;
}
