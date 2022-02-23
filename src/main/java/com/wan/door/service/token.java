package com.wan.door.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class token {

    public static String  CreateToken(String sessionId,String name,String role) {
        JwtBuilder jwtBuilder =  Jwts.builder()
                .setId(sessionId+"")
                .setSubject(name)
                .claim("roles",role)
                .claim("secret","I'm Chinese")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "my-123")
                .setExpiration(new Date(new Date().getTime()+86400000));
        return  jwtBuilder.compact();
    }

    public static Map<String, String> ParsingToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("my-123")
                .parseClaimsJws(token)
                .getBody();
        Map<String,String> temp = new HashMap<>();
        temp.put("sessionId",claims.getId());
        temp.put("name",claims.getSubject());
        temp.put("roles", (String) claims.get("roles"));
        temp.put("timeOut", new SimpleDateFormat("yyyyMMddHHmmss").format(claims.getExpiration()));
        return temp;
    }
}
