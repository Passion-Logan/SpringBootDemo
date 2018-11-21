package cn.com.scitc.movie.service;

import cn.com.scitc.movie.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    /**
     * 发表影评
     * @param commentEntity
     */
    void addComment(CommentEntity commentEntity);

    /**
     * 获取所有影评
     * @return
     */
    List<CommentEntity> all();

    /**
     * 根据内容查询影评
     * @param content
     * @return
     */
    List<CommentEntity> findByContent(String content);

    /**
     * 根据电影编号获取影评
     * @param filmId
     * @return
     */
    List<CommentEntity> findByFilmId(Integer filmId);

    /**
     * 根据会员编号获取影评
     * @param memberId
     * @return
     */
    List<CommentEntity> findByMemberId(Integer memberId);

    /**
     * 根据电影编号，会员编号获取影评
     * @param filmId
     * @return
     */
    List<CommentEntity> findByFilmIdAndMemberId(Integer filmId, Integer memberId);

    /**
     * 根据电影编号,内容获取影评
     * @param filmId
     * @return
     */
    List<CommentEntity> findByFilmIdAndContent(Integer filmId, String content);

    /**
     * 根据会员编号和内容获取影评
     * @param memberId
     * @param content
     * @return
     */
    List<CommentEntity> findByMemberIdAndContent(Integer memberId, String content);

    /**
     * 根据电影编号，会员编号,影评内容 获取影评
     * @param filmId
     * @return
     */
    List<CommentEntity> findByFilmIdAndMemberIdAndContent(Integer filmId, Integer memberId, String content);

    /**
     * 根据编号删除影评
     * @param id
     */
    void deleteComment(Integer id);
}
