package com.q2333.flashTakeOut.filter;

import com.alibaba.fastjson.JSON;
import com.q2333.flashTakeOut.common.Return;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 *
 * @author q2333
 * @date 2022/06/14 10:04
 **/
@SuppressWarnings("all")
@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器,支持*通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter
            (ServletRequest servletRequest, ServletResponse servletResponse,
             FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到本次请求路径 {}" + requestURI);
        String[] release_urls = new String[]{//直接放行的请求:如登录页面
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        if (checkURI(release_urls, requestURI)) {
            log.info("本次请求资源:{}可以不需要处理", requestURI);
            filterChain.doFilter(request, response);//执行拦截器:放行
            return;//结束方法
        }
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户登录已经登录态,id为: {}",
                    request.getSession().getAttribute("employee"));//K:employee, V:id
            filterChain.doFilter(request, response);//执行拦截器:放行
            return;
        }
        log.info("用户未登录");
        //未登录:用输出流发送Return对象
        //且未执行任何放行操作
        response.getWriter().write
                (JSON.toJSONString(Return.error("NOTLOGIN")));
        return;
    }

    public boolean checkURI(String[] urls, String requestURI) {
        for (String url : urls) {
            final boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}


























