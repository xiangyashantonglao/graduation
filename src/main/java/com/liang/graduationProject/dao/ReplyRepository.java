package com.liang.graduationProject.dao;

import com.liang.graduationProject.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
@Service("replyRepository")
public interface ReplyRepository extends MongoRepository<Reply,String> {

    public Page<Reply> findByPostId(String postId, Pageable page);
    public void deleteAllByPostId(String postId);
    List<Reply> findByPostId(String postId);
}
