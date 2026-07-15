package com.blake.malllite.util;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;

public class JWTTest {
    public static void main(String[] args) {
        String token = JwtUtil.generateToken(1L,"admin");
        Claims claims = JwtUtil.parseToken(token);
        System.out.println(claims.get("userId"));
        System.out.println(claims.get("username"));
        System.out.println(token);

        }
    }


