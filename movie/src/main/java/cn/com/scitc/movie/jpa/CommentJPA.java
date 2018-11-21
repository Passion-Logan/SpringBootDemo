package cn.com.scitc.movie.jpa;

import cn.com.scitc.movie.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentJPA extends JpaRepository<CommentEntity, Integer> {

    /**
     * 根据影评内容查询影评
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
     * 根据电影编号和会员编号查询影评
     * @param filmId
     * @param memberId
     * @return
     */
    List<CommentEntity> findByFilmIdAndMemberId (Integer filmId, Integer memberId);

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
     * 根据内容，用户,电影编号查询影评
     * @param filmId
     * @param memberId
     * @return
     */
    List<CommentEntity> findByFilmIdAndMemberIdAndContent (Integer filmId, Integer memberId, String content);
}
