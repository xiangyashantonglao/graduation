package com.liang.graduationProject.service;

import com.liang.graduationProject.dao.DaoFacade;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by admin on 2017/3/29.
 */
@Data
public class GenericService implements Serializable{
    @Resource
   private ServiceFacade serviceFacade;
    @Resource
    private DaoFacade daoFacade;
}
