package com.blake.malllite.util;

import io.jsonwebtoken.Claims;

public class JWTTest {
    public static void main(String[] args) {
        String token = JwtUtil.generateToken(1L,"admin");
        Claims claims = JwtUtil.parseToken(token);
        System.out.println(claims.get("userid"));
        System.out.println(claims.get("username"));

    }
}
