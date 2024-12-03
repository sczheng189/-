package com.sczheng.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sczheng.pojo.Result;
import com.sczheng.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override //目标方法执行之前
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        String url = req.getRequestURI();
        log.info("请求URL:{}", url);

        //2. 判断请求url是否包含login,如果包含,说明是登录,放行
        //
        if (url.contains("login"))
        {
            log.info("登录操作,放行");
            return true;    //这就是 放行
        }
        //3.获取请求投中的令牌
        String jwt=req.getHeader("token");

        //4.判断令牌是否存在,不存在,返回错误
        if (!StringUtils.hasLength(jwt))
        {
            log.info("请求头为空");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notlogin);

            return false; //不能放行
        }


        //5.解析token,失败就未登录

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析失败,返回错误登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notlogin);
            return false;
        }

        //6.放行
        log.info("令牌合法");


        //需要放行 browser才能访问资源

        return true;
    }

    @Override//目标方法执行之后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override// 页面渲染之后   最后的最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
