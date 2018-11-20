package com.cody.controller;

import com.alibaba.fastjson.JSON;
import com.cody.entity.ActivityEntity;
import com.cody.entity.CommentEntity;
import com.cody.service.CommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cody_
 * @date 18/10/5
 */
@RestController
@EnableAutoConfiguration
public class CommentController {

    @Autowired
    CommentService commentService;

    private Log log = LogFactory.getLog(CommentController.class);

    /**
     * 添加留言
     * @param content 内容
     * @return
     */
    @PostMapping(value = "/leaveWord")
    public void leaveWord(@RequestParam("content")String content) {
        commentService.leaveWord(content);
    }

    /**
     * 留言板界面获取所有留言
     * @return
     */
    @RequestMapping(value = "/getWords")
    public Object getWords() {
        return JSON.toJSONString(commentService.getComment());
    }

    /**
     * 管理界面获取所有留言
     * @param delId
     * @param name
     * @return
     */
    @RequestMapping(value = "/allWords")
    public Object getWords(@Param(value = "delId") String delId,
                           @Param(value = "name") String name,
                           @Param("page")Integer page,
                           @Param("limit")Integer limit) {

        List<CommentEntity> listComment = commentService.pageComment(page, limit);
        List<CommentEntity> all = commentService.all();

        //调试用
        /*System.out.println("学号 ：" + delId);
        System.out.println("姓名:" + name);*/


        if(name != null) {
            List<CommentEntity> findByName = commentService.findByName(name);

            Map map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "加载成功！");
            map.put("count", all.size());
            map.put("data", findByName);

            return JSON.toJSONString(map);
        }

        if(delId != null) {
            commentService.delWordById(Long.parseLong(delId));

            List<CommentEntity> newAll = commentService.getComment();

            Map map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "加载成功！");
            map.put("count", all.size());
            map.put("data", newAll);

            return JSON.toJSONString(map);
        }

        Map map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "加载成功！");
        map.put("count", all.size());
        map.put("data", listComment);

        return JSON.toJSONString(map);
    }

    /**
     * 删除所有留言
     * @return
     */
    @RequestMapping(value = "/delAll")
    public String delAll() {
        commentService.delAll();

        return "删除成功!";
    }

}
