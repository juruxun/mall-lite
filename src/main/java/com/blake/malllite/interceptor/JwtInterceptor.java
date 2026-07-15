package com.blake.malllite.interceptor;

import com.blake.malllite.common.UserHolder;
import com.blake.malllite.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)throws Exception{

        //获取请求头
    String token = request.getHeader("Authorization");

    //判断token是否存在
        if(token == null){
            response.setStatus(401);
            return false;
        }

        if(!token.startsWith("Bearer ")){
            response.setStatus(401);
            return false;
        }
        token=token.substring(7);

        try {
         //解析token
         Claims claims = JwtUtil.parseToken(token);
        //获取用户Id
         Long userId=claims.get("userId", Long.class);
         //保存用户信息
         UserHolder.saveUser(userId);
         return  true;
     }catch (Exception e){
            e.printStackTrace();
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"Token无效\"}");

         return false;
     }

    }
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex){
        UserHolder.remove();
    }

}
