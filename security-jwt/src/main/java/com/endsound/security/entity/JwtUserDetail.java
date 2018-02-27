package com.endsound.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class JwtUserDetail extends JwtInfo implements UserDetails {
    private String username;
    private String password;
    private List<? extends GrantedAuthority> authorities;
    private Boolean locked;
    private Boolean enable;
    private Timestamp expiredDate;
    private Timestamp pwdExpiredDate;

    private Integer id;
    private String name;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        if(Objects.nonNull(expiredDate))
            return Timestamp.from(Instant.now()).before(expiredDate);
        else
            return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(Objects.nonNull(pwdExpiredDate))
            return Timestamp.from(Instant.now()).before(pwdExpiredDate);
        else
            return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public JwtUserDetail setUsername(String username) {
        this.username = username;
        return this;
    }

    public JwtUserDetail setPassword(String password) {
        this.password = password;
        return this;
    }

    public JwtUserDetail setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public Boolean getLocked() {
        return locked;
    }

    public JwtUserDetail setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public JwtUserDetail setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public JwtUserDetail setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public Timestamp getPwdExpiredDate() {
        return pwdExpiredDate;
    }

    public JwtUserDetail setPwdExpiredDate(Timestamp pwdExpiredDate) {
        this.pwdExpiredDate = pwdExpiredDate;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public JwtUserDetail setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public JwtUserDetail setName(String name) {
        this.name = name;
        return this;
    }
}
