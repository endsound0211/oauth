package com.endsound.security;

import com.endsound.security.entity.JwtGrantedAuthority;
import com.endsound.security.entity.JwtUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtUtil {
    @Value("${jwt.security.key}")
    private String securityKey;
    @Value("${jwt.expiration.time:15}")
    private Integer expirationTime;
    @Value("${jwt.refresh.time:5}")
    private Integer refreshTime;

    public JwtUserDetail parseToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(securityKey)
                .parseClaimsJws(token)
                .getBody();


        JwtUserDetail userDetail = new JwtUserDetail();
        //jwt info
        userDetail.setSubject(claims.getSubject())
                .setAudience(claims.getAudience())
                .setIssuer(claims.getIssuer())
                .setIssuedAt(new Timestamp(claims.getIssuedAt().getTime()))
                .setExpiration(new Timestamp(claims.getExpiration().getTime()))
                .setRefresh(new Timestamp((Long)claims.get("refresh")));
        //other info
        userDetail.setId((Integer)claims.get("id"))
                .setName(claims.get("name").toString())
                .setAuthorities(
                    ((List<String>)claims.get("roles")).parallelStream()
                            .map(role -> new JwtGrantedAuthority(role.toString()))
                            .collect(Collectors.toList())
        );


        return userDetail;
    }


    public String generateToken(JwtUserDetail userDetail){
        Timestamp current = Timestamp.from(Instant.now());
        Timestamp expiration = new Timestamp(current.getTime() + expirationTime * 60 * 1000);
        Timestamp refresh = new Timestamp(current.getTime() + (expirationTime - refreshTime) * 60 * 1000);
        Claims claims = Jwts.claims()
                .setSubject(userDetail.getSubject())
                .setAudience(userDetail.getAudience())
                .setIssuer(userDetail.getIssuer())
                .setIssuedAt(current)
                .setExpiration(expiration);
        claims.put("refresh", refresh);
        //set payload
        claims.put("id", userDetail.getId());
        claims.put("name", userDetail.getUsername());
        claims.put("roles", userDetail.getAuthorities().parallelStream().map(role -> role.getAuthority()).toArray());



        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, securityKey)
                .compact();
    }

    public Claims parseCommonToken(String token){
        return Jwts.parser()
                .setSigningKey(securityKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
