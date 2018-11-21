package cn.com.scitc.movie.controller;

import cn.com.scitc.movie.service.MemberService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    MemberService memberService;

    static Map<String , Object> map = Collections.synchronizedMap(new HashMap<>());

    /**
     * 通过session获取账户信息
     * @param request
     * @return
     */
    @RequestMapping("/profile")
    public Object profile(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute("_session_user");

        Object role = request.getSession().getAttribute("role");
        Object account = request.getSession().getAttribute("account");
        Object nickname = request.getSession().getAttribute("nickname");

        Map<String, Object> entity = Collections.synchronizedMap(new HashMap<>());

        //用户已登录
        try {
            if(obj != null) {
                entity.put("role", role);
                entity.put("account", account);
                entity.put("nickname", nickname);

                map.put("data", entity);
                map.put("code", 0);
                map.put("msg", "欢迎回来");
            }
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "您未登录！");
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping("/member/fetchMember")
    public Object fetchMember() {
        Integer code = 0;
        String msg = "获取成功";

        try {
            map.put("code", code);
            map.put("data", memberService.all());
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "你仿佛来到了荒芜的世界~");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 搜索用户
     * @param searchFeild 搜索字段，account、id、nickname
     * @param searchValue 搜索内容
     * @return
     */
    @RequestMapping("/member/searchMember")
    public Object searchMember(@Param("searchFeild")String searchFeild,
                               @Param("searchValue")String searchValue) {
        Integer code = 0;
        String msg = "获取成功";

        String id = "id";
        String account = "account";
        String nickname = "nickname";

        try {
            if(searchValue != null && searchValue != "") {
                if(searchFeild.equals(id)) {
                    map.put("code", code);
                    map.put("msg", msg);
                    map.put("data", memberService.findById(Integer.parseInt(searchValue)));
                }else if(searchFeild.equals(account)) {
                    map.put("code", code);
                    map.put("msg", msg);
                    map.put("data", memberService.findByAccount(searchValue));
                }else if(searchFeild.equals(nickname)) {
                    map.put("code", code);
                    map.put("msg", msg);
                    map.put("data", memberService.findByNickname(searchValue));
                }
            }else {
                map.put("code", 1);
                map.put("msg", "内容不能为空！");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "你仿佛来到了荒芜的世界~");
        }

        return JSON.toJSONString(map);
    }

}
