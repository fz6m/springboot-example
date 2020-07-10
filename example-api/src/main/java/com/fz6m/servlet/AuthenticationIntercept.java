package com.fz6m.servlet;

import com.fz6m.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationIntercept implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 是否登录
        boolean isLogin = false;

        // 请求头带上令牌 Authorization: Bearer jwtToken
        final String authHeader = request.getHeader("Authorization");

        if(StringUtils.isNotBlank(authHeader) && authHeader.startsWith("Bearer ")) {
            // 截取token，
            final String token = authHeader.substring(7);
            // 解析token
            Claims claims = jwtUtil.parseJWT(token);
            if(claims != null) {
                // 是否登录
                Boolean b = (Boolean) claims.get("isLogin");
                if(b) {
                    // 已经登录，放行请求
                    isLogin = true;
                }
            }
        }

        if(!isLogin) {
            // 未登录，则响应信息
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(401);
            response.getWriter().write("未通过认证，请在登录进行登录");
        }
        // 不放行
        return isLogin;
    }
}
