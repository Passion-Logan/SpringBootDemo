package cn.com.scitc.movie.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURI());

        /*final String member = "/login/member";
        final String admin = "/login/admin";
        final String regist = "/regist/member";
        final String index = "/index";
        final String outadmin = "/logout/admin";
        final String outmember = "/logout/member";*/

        final String fetchList = "/film/fetchList";

        //登录,注册，主页不做拦截
        /*if(member.equals(request.getRequestURI()) || admin.equals(request.getRequestURI()) || regist.equals(request.getRequestURI()) || index.equals(request.getRequestURI()) || outadmin.equals(request.getRequestURI()) || outmember.equals(request.getRequestURI())) {
            return true;
        }*/
        //获取电影列表不做验证
        if(fetchList.equals(request.getRequestURI())) {
            return true;
        }

        //验证session
        Object obj = request.getSession().getAttribute("_session_user");

        if(obj == null) {
            response.sendRedirect("/index");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
