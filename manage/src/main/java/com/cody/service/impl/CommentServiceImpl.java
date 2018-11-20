package com.cody.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cody.jpa.UserJPA;
import com.cody.utils.CommonTools;
import com.cody.entity.CommentEntity;
import com.cody.jpa.CommentJPA;
import com.cody.service.CommentService;
import com.cody.utils.ListSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cody_
 * @date 18/10/5
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentJPA commentJPA;

    @Autowired
    UserJPA userJPA;

    @Autowired
    ListSort listSort;

    /**
     * 获取所有数据
     * @return
     */
    @Override
    public List<CommentEntity> all() {
        return commentJPA.findAll();
    }

    /**
     * 分页获取所有留言
     * @param page 页数
     * @param limit 每页数量
     * @return
     */
    @Override
    public List<CommentEntity> pageComment(Integer page, Integer limit) {

        Pageable pageable = PageRequest.of(page-1, limit);
        Page<CommentEntity> commentEntityPage = commentJPA.findAll(pageable);

        Iterator<CommentEntity> commentEntityIterator =  commentEntityPage.iterator();

        //分页后的List
        List<CommentEntity> commentAll = new ArrayList<>();

        while(commentEntityIterator.hasNext()) {
            commentAll.add(commentEntityIterator.next());
        }

        listSort.ListSortByTime(commentAll);

        return commentAll;
    }

    /**
     * 获取所有留言
     * @return
     */
    @Override
    public List<CommentEntity> getComment() {
        List<CommentEntity> allComment = commentJPA.findAll();
        listSort.ListSortByTime(allComment);

        return allComment;
    }

    /**
     * 添加留言
     * @param content 内容
     * @return
     */
    @Override
    public Object leaveWord(String content) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("用户学号：" + userId);

        CommentEntity comment = new CommentEntity();

        comment.setUserId(userId);
        comment.setUsername(userJPA.findByUsername(userId).getName());
        comment.setContent(content);
        comment.setCreateTime(CommonTools.getCurrentTime());
        commentJPA.save(comment);

        listSort.ListSortByTime(commentJPA.findAll());

        return JSONArray.toJSONString(commentJPA.findAll());
    }

    /**
     * 根据留言ID删除留言
     * @param id 主键
     */
    @Override
    public void delWordById(Long id) {
        commentJPA.deleteById(id);
    }

    /**
     * 删除所有留言
     */
    @Override
    public void delAll() {
        commentJPA.deleteAll();
    }

    /**
     * 根据用户姓名 查找留言
     * @param name 用户姓名
     * @return
     */
    @Override
    public List<CommentEntity> findByName(String name) {
        return commentJPA.findByUsername(name);
    }
}
