package com.blake.malllite.interceptor;

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
                             Object handle)throws Exception{

        //获取请求头
    String token = request.getHeader("Authorization");

    //判断token是否存在
        if (token==null|| token.isEmpty()){
            response.setStatus(401);
            return false;
        }
     try {
         //解析token
         Claims claims = JwtUtil.parseToken(token);
         System.out.println(claims);
         return  true;
     }catch (Exception e){
         response.setStatus(401);
         return false;
     }

    }
}
