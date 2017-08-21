package com.endsound.security.service;

import com.endsound.dao.RoleDao;
import com.endsound.dao.UserDao;
import com.endsound.security.entity.JwtGrantedAuthority;
import com.endsound.security.entity.JwtUserDetail;
import org.jooq.tables.pojos.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public JwtUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.fetchOneByUsername(username);
        if(Objects.isNull(user))
            return null;

        List<String> roles = roleDao.fetchRolesByUserId(user.getId());



        JwtUserDetail jwtUserDetail = new JwtUserDetail();
        BeanUtils.copyProperties(user, jwtUserDetail);
        jwtUserDetail.setEnable(user.getEnable() == 1)
                .setLocked(user.getLocked() == 1)
                .setAuthorities(
                        roles.parallelStream()
                            .map(role -> new JwtGrantedAuthority(role))
                            .collect(Collectors.toList()));

        return jwtUserDetail;
    }
}
