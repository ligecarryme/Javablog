package com.darin.blog.handler;

import com.darin.blog.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null){
            boolean result = TokenUtils.verify(token);
            if (result){
                logger.info("通过拦截器");
                return true;
            }else {
                logger.info("请求被拦截");
                return false;
            }
        }
        logger.info("请求没有token");
        return false;
//        if (request.getSession().getAttribute("user")==null){
//            return false;
//        }

    }
}
