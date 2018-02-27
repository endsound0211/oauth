package com.endsound.security.entity;

import java.sql.Timestamp;

public class JwtInfo {
    private String subject;
    private String audience;
    private Timestamp refresh;
    private Timestamp expiration;
    private Timestamp issuedAt;
    private String issuer;

    public Timestamp getRefresh() {
        return refresh;
    }

    public JwtInfo setRefresh(Timestamp refresh) {
        this.refresh = refresh;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public JwtInfo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getAudience() {
        return audience;
    }

    public JwtInfo setAudience(String audience) {
        this.audience = audience;
        return this;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public JwtInfo setExpiration(Timestamp expiration) {
        this.expiration = expiration;
        return this;
    }

    public Timestamp getIssuedAt() {
        return issuedAt;
    }

    public JwtInfo setIssuedAt(Timestamp issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public String getIssuer() {
        return issuer;
    }

    public JwtInfo setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }
}
