package cn.com.scitc.movie.controller;

import cn.com.scitc.movie.entity.CommentEntity;
import cn.com.scitc.movie.entity.MemberEntity;
import cn.com.scitc.movie.service.CommentService;
import cn.com.scitc.movie.service.MemberService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MemberService memberService;

    /**
     * 发表影评
     * @param film_id
     * @param content
     * @param rate
     * @return
     */
    @RequestMapping("/comment/addComment")
    public Object addComment(@Param("film_id")Integer film_id,
                             @Param("content")String content,
                             @Param("rate")Integer rate,
                             HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        Object account = request.getSession().getAttribute("account");

        try {

            Optional<MemberEntity> member = memberService.findByAccount(String.valueOf(account));

            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setFilmId(film_id);
            commentEntity.setContent(content);
            commentEntity.setRate(rate);
            commentEntity.setMemberId(member.get().getId());

            commentService.addComment(commentEntity);

            map.put("code", 0);
            map.put("msg", "评论发表成功");
            map.put("data", commentEntity);

            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "对不起，评论被和谐！");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 查询影评
     * @param searchFeild 查询字段，content
     * @param searchValue 查询内容，
     * @param mine 是否只查自己的
     * @param film 电影id
     * @param request 获取session中的用户账号
     * @return
     */
    @RequestMapping("/comment/fetchComment")
    public Object fetchComment(@Param("searchFeild")String searchFeild,
                               @Param("searchValue")String searchValue,
                               @Param("mine")Integer mine,
                               @Param("film")Integer film,
                               HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        Integer code = 0;
        String msg = "获取成功";

        Object account = request.getSession().getAttribute("account");
        Optional<MemberEntity> member = memberService.findByAccount(String.valueOf(account));
        Integer memberId = member.get().getId();

        try {
            if(searchFeild != null && mine != null && film != null) {
                map.put("code", code);
                map.put("data", commentService.findByFilmIdAndMemberIdAndContent(film, memberId, searchValue));
            }else if(film == null && searchFeild != null && mine != null) {
                map.put("code", code);
                map.put("data", commentService.findByFilmIdAndMemberId(film, memberId));
            }else if (mine == null && searchFeild != null && film != null) {
                map.put("code", code);
                map.put("data", commentService.findByFilmIdAndContent(film, searchValue));
            }else if (film == null && searchFeild != null && mine != null) {
                map.put("code", code);
                map.put("data", commentService.findByMemberIdAndContent(memberId, searchValue));
            }else if (film == null && searchFeild == null && mine != null) {
                map.put("code", code);
                map.put("data", commentService.findByMemberId(memberId));
            }else if (film == null && searchFeild != null && mine == null) {
                map.put("code", code);
                map.put("data", commentService.findByContent(searchValue));
            }else if (film != null && searchFeild == null && mine == null) {
                map.put("code", code);
                map.put("data", commentService.findByFilmId(film));
            }else {
                map.put("code", code);
                map.put("data", commentService.all());
            }
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "你仿佛来到了荒芜的世界~");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 根据影评编号删除影评
     * @param list
     * @return
     */
    @RequestMapping("/comment/deleteComment")
    public Object deleteComment(@Param("list")String[] list) {
        Map<String ,Object> map = new HashMap<>();

        Integer code = 0;
        String msg = "删除成功";

        try {
            if(list.length != 0) {
                for(String id : list) {
                    commentService.deleteComment(Integer.parseInt(id));
                }

                map.put("code", code);
                map.put("msg", msg);
            }else {
                map.put("code", 1);
                map.put("msg", "请选择需要删除的影评！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "你仿佛来到了荒芜的世界~");
        }

        return JSON.toJSONString(map);
    }
}
