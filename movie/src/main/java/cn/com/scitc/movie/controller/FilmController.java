package cn.com.scitc.movie.controller;

import cn.com.scitc.movie.entity.FilmEntity;
import cn.com.scitc.movie.service.FilmService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FilmController {

    @Autowired
    FilmService filmService;

    static Map<String ,Object> map = Collections.synchronizedMap(new HashMap<>());

    /**
     * 添加电影
     * @param title 标题
     * @param subtitle 副标题
     * @param href 观看链接
     * @param cover 图片链接
     * @return
     */
    @RequestMapping("/film/addFilm")
    public Object addFilm(@Param("title")String title,
                          @Param("subtitle")String subtitle,
                          @Param("href")String href,
                          @Param("cover")String cover) {
        FilmEntity filmEntity = new FilmEntity();

        String msg = "添加成功";
        Integer code = 0;

        filmEntity.setTitle(title);
        filmEntity.setSubtitle(subtitle);
        filmEntity.setHref(href);
        filmEntity.setCover(cover);

        try {
            filmService.add(filmEntity);

            map.put("code", code);
            map.put("msg", msg);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "添加失败！");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 查询影片
     * @param searchFeild 搜索字段，title、subtitle
     * @param searchValue 搜索内容
     * @return
     */
    @RequestMapping("/film/fetchFilm")
    public Object fetchFilm(@Param("searchFeild")String searchFeild,
                             @Param("searchValue")String searchValue) {
        String title = "title";
        String subtitle = "subtitle";

        Integer code = 0;
        String msg = "获取成功";

        try {
            if(searchFeild != null) {
                if (searchFeild.equals(title)) {
                    map.put("code", code);
                    map.put("msg", msg);
                    map.put("data", filmService.findByTitle(searchValue));
                }else if(searchFeild.equals(subtitle)) {
                    map.put("code", code);
                    map.put("msg", msg);
                    map.put("data", filmService.findBySubtitle(searchValue));
                }
            }else {
                map.put("code", code);
                map.put("msg", msg);
                map.put("data", filmService.all());
            }
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "查找失败，请输入有效的查询信息！");
        }

        return JSON.toJSONString(map);
    }


    /**
     * 根据电影编号删除电影
     * @param list 编号数组
     * @return
     */
    @RequestMapping("/film/deleteFilm")
    public Object deleteFilm(@Param("list[]")Integer[] list) {
        Integer code = 0;
        String msg = "删除成功";

        try {
            if(list != null && list.length == 0) {
                map.put("code", 1);
                map.put("msg", "请选择需要删除的影片！");
            }else {
                for(Integer id : list) {
                    filmService.delete(id);
                }

                map.put("code", code);
                map.put("msg", msg);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "你仿佛来到了荒芜的世界~");
        }

        return JSON.toJSONString(map);
    }
}
