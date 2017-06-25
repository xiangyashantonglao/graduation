package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by admin on 2017/4/18.
 */
@Service(value = "managerRepository")
public interface ManagerRepository extends MongoRepository<Manager,String> {
    public Manager findByManagerName(String managerName);
    public ArrayList<Manager> findByManagerLevel(int i);
}
