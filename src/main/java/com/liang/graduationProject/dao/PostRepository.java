package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
@Service("postRepository")
public interface PostRepository extends MongoRepository<Post,String> {
    public Post findByPostName(String postName);
    public Page<Post> findBySectionName(String sectionName,Pageable page);

    @Query( fields = "{'postId':1}")
    public List<Post> findBySectionName(String sectionName);

    public List<Post> findByPostType(int postType);
    public List<Post> findBySectionNameAndPostType(String sectionName,int postType);
    public Page<Post> findBySectionNameAndPostType(String sectionName,int postType,Pageable pageable);
    public void deleteBySectionName(String sectionName);
    List<Post> findByPostNameLike(String key);
}
