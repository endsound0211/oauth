package com.endsound.security.dao;

import com.endsound.security.entity.JwtUserDetail;
import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends org.jooq.tables.daos.UserDao{
    @Autowired
    public UserDao(@Qualifier("config")Configuration configuration){super(configuration);}

}
