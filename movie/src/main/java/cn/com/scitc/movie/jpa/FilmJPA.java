package cn.com.scitc.movie.jpa;

import cn.com.scitc.movie.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmJPA extends JpaRepository<FilmEntity, Integer> {

    /**
     * 根据电影名称查找
     * @param title
     * @return
     */
    List<FilmEntity> findByTitle(String title);

    /**
     * 根据电影副标题查找
     * @param title
     * @return
     */
    List<FilmEntity> findBySubtitle(String title);
}
