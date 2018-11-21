package cn.com.scitc.movie.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(request.getRequestURI());

        final String fetchFilm = "/film/fetchFilm";
        final String fetchComment = "/comment/fetchComment";
        final String fetchRate = "/film/fetchRate";


        //获取电影列表不做验证
        if(fetchFilm.equals(request.getRequestURI()) || fetchComment.equals(request.getRequestURI()) || fetchRate.equals(request.getRequestURI())) {
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
