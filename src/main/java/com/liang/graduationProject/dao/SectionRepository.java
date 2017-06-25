package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
@Service("sectionRepository")
public interface SectionRepository extends MongoRepository<Section,String>{
    public Section findBySectionName(String sectionName);

    @Query(value = "{'sectionName' : {'$ne': null}}",fields = "{'sectionName':1,'describe':1}")
    public List<Section> findAll(int i);
}
