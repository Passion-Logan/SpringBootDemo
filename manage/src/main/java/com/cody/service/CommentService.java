package com.cody.service;

import com.cody.entity.CommentEntity;

import java.util.List;

/**
 * @author Cody_
 * @date 18/10/5
 */
public interface CommentService {
    /**
     * 获取所有数据
     * @return
     */
    List<CommentEntity> all();

    /**
     * 分页获取所有留言
     * @param page 页数
     * @param limit 每页条数
     * @return
     */
    List<CommentEntity> pageComment(Integer page, Integer limit);

    /**
     * 获取所有留言
     * @return
     */
    List<CommentEntity> getComment();

    /**
     * 添加留言
     * @param content 内容
     * @return
     */
    Object leaveWord(String content);

    /**
     * 删除留言
     * @param id 主键
     * @return
     */
    void delWordById(Long id);

    /**
     * 删除所有留言
     * @return
     */
    void delAll();

    /**
     *查找留言 根据用户姓名来查
     * @param name 用户姓名
     * @return
     */
    List<CommentEntity> findByName(String name);

}
