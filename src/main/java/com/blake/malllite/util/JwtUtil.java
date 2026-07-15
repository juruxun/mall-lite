package com.blake.malllite.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY="mall-lite-secret-key-blake-project-secure-123456";
    private static final long EXPIRATION=24*60*60*1000;
    private static final Key KEY =
            Keys.hmacShaKeyFor(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(long userid,String username ){
        Map<String,Object> claims=new HashMap<>();

        claims.put("userId",userid);
        claims.put("username",username);
        return  Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();

    }

    public static Claims parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
