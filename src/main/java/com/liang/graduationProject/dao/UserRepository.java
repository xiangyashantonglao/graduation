package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
@Repository(value = "userRepository")
public interface UserRepository extends MongoRepository<User,String> {
    public User findByPhone(String phone);
    public User findByEmail(String email);
    public User findByUserId(String userId);
}
