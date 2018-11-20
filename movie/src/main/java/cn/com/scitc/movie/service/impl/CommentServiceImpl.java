package cn.com.scitc.movie.service.impl;

import cn.com.scitc.movie.entity.CommentEntity;
import cn.com.scitc.movie.jpa.CommentJPA;
import cn.com.scitc.movie.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentJPA commentJPA;

    @Override
    public void addComment(CommentEntity commentEntity) {
        commentJPA.save(commentEntity);
    }

    @Override
    public List<CommentEntity> all() {
        return commentJPA.findAll();
    }

    @Override
    public Optional<CommentEntity> findByContent(String content) {
        return commentJPA.findByContent(content);
    }

    @Override
    public Optional<CommentEntity> findByFilmId(Integer filmId) {
        return commentJPA.findByFilmId(filmId);
    }

    @Override
    public Optional<CommentEntity> findByMemberId(Integer memberId) {
        return commentJPA.findByMemberId(memberId);
    }

    @Override
    public Optional<CommentEntity> findByFilmIdAndMemberIdAndContent(Integer filmId, Integer memberId, String content) {
        return commentJPA.findByFilmIdAndMemberIdAndContent(filmId, memberId, content);
    }

    @Override
    public Optional<CommentEntity> findByFilmIdAndMemberId(Integer filmId, Integer memberId) {
        return commentJPA.findByFilmIdAndMemberId(filmId, memberId);
    }

    @Override
    public Optional<CommentEntity> findByFilmIdAndContent(Integer filmId, String content) {
        return commentJPA.findByFilmIdAndContent(filmId, content);
    }

    @Override
    public Optional<CommentEntity> findByMemberIdAndContent(Integer memberId, String content) {
        return commentJPA.findByMemberIdAndContent(memberId, content);
    }

    @Override
    public void deleteComment(Integer id) {
        commentJPA.deleteById(id);
    }
}
