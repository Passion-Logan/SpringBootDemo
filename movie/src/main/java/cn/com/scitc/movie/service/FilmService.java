package cn.com.scitc.movie.service;

import cn.com.scitc.movie.entity.FilmEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FilmService {

    /**
     * 获取所有电影列表
     * @return
     */
    List<FilmEntity> all();

    /**
     * 添加电影
     */
    void add(FilmEntity filmEntity);

    /**
     * 根据电影名称查找
     * @param title
     * @return
     */
    List<FilmEntity> findByTitle(String title);

    /**
     * 根据电影副标题查找
     * @param subtitle
     * @return
     */
    List<FilmEntity> findBySubtitle(String subtitle);

    /**
     * 根据影片编号删除电影
     * @param id
     */
    void delete(Integer id);
}
