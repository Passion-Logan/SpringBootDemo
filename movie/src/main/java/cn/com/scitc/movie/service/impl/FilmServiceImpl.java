package cn.com.scitc.movie.service.impl;

import cn.com.scitc.movie.entity.FilmEntity;
import cn.com.scitc.movie.jpa.FilmJPA;
import cn.com.scitc.movie.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmJPA filmJPA;

    @Override
    public List<FilmEntity> all() {
        return filmJPA.findAll();
    }

    @Override
    public void add(FilmEntity filmEntity) {
        filmJPA.save(filmEntity);
    }

    @Override
    public Optional<FilmEntity> findById(Integer id) {
        return filmJPA.findById(id);
    }

    @Override
    public List<FilmEntity> findByTitle(String title) {
        return filmJPA.findByTitle(title);
    }

    @Override
    public List<FilmEntity> findBySubtitle(String subtitle) {
        return filmJPA.findBySubtitle(subtitle);
    }

    @Override
    public void delete(Integer id) {
        filmJPA.deleteById(id);
    }

}
