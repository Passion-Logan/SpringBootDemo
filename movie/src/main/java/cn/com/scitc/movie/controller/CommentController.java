package cn.com.scitc.movie.controller;

import cn.com.scitc.movie.entity.CommentEntity;
import cn.com.scitc.movie.entity.FilmEntity;
import cn.com.scitc.movie.entity.MemberEntity;
import cn.com.scitc.movie.service.CommentService;
import cn.com.scitc.movie.service.FilmService;
import cn.com.scitc.movie.service.MemberService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MemberService memberService;

    @Autowired
    FilmService filmService;

    static Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());

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
        Object account = request.getSession().getAttribute("account");

        try {
            MemberEntity member = memberService.findByAccount(String.valueOf(account));

            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setFilmId(film_id);
            commentEntity.setContent(content);
            commentEntity.setRate(rate);
            commentEntity.setMemberId(member.getId());

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
        Integer code = 0;
        String admin = "ROOT_ADMIN";
        String msg = "获取成功";
        Integer memberId = null;

        Object account = request.getSession().getAttribute("account");
        Object role = request.getSession().getAttribute("role");
        MemberEntity member = memberService.findByAccount(String.valueOf(account));

        if (role != null && !role.equals(admin)) {
            memberId = member.getId();
        }

        try {
            if(searchFeild != null && mine != null && film != null) {
                map.put("code", code);
                map.put("data", commentService.findByFilmIdAndMemberIdAndContent(film, memberId, searchValue));
            }else if(searchFeild == null && film != null && mine != null) {
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
    public Object deleteComment(@Param("list[]")Integer[] list) {
        Integer code = 0;
        String msg = "删除成功";

        try {
            if(list != null && list.length != 0) {
                for(Integer id : list) {
                    commentService.deleteComment(id);
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

    /**
     * @param id 电影id
     * @return
     */
    @RequestMapping("/film/fetchRate")
    public Object fetchRate(@Param("id")Integer id) {

        Integer code = 0;
        String msg = "获取成功";
        Integer[] count = new Integer[5];
        float rate = 0;
        Map<String , Object> data = new HashMap<>();

        try {
            List<CommentEntity> commentEntity = commentService.findByFilmId(id);
            Optional<FilmEntity> filmEntity = filmService.findById(id);

            if (commentEntity.size() == 0) {
                count[0] = 0;
                count[1] = 0;
                count[2] = 0;
                count[3] = 0;
                count[4] = 0;
                rate = 0;
                data.put("count", count);
                data.put("rate", rate);
                data.put("data", filmEntity);

                map.put("code", code);
                map.put("msg", msg);
                map.put("data", data);
            }else {
                int a = 0;
                int b = 0;
                int c = 0;
                int d = 0;
                int e = 0;

                for(CommentEntity temp : commentEntity) {
                    switch (temp.getRate()) {
                        case 5:
                            a += 1;
                            break;
                        case 4:
                            b += 1;
                            break;
                        case 3:
                            c += 1;
                            break;
                        case 2:
                            d += 1;
                            break;
                        case 1:
                            e += 1;
                            break;
                        default:
                            break;
                    }
                }

                count[0] = a*5;
                count[1] = b*4;
                count[2] = c*3;
                count[3] = d*2;
                count[4] = e;

                System.out.println("总分数:" + (a*5+b*4+c*3+d*2+e));
                System.out.println("总人数:" + (a+b+c+d+e));

                rate = (a*5+b*4+c*3+d*2+e)/(a+b+c+d+e);

                data.put("count", count);
                data.put("rate", rate);
                data.put("data", filmEntity);

                map.put("code", code);
                map.put("msg", msg);
                map.put("data", data);
            }
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "你仿佛来到了荒芜的世界~");
        }

        return JSON.toJSONString(map);
    }
}
