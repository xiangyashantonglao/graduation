package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Outline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/4/14.
 */
@Service("outlineRepository")
public interface OutlineRepository extends MongoRepository<Outline,String>{
    public Outline findByOutlineId(String outlineId);
}
