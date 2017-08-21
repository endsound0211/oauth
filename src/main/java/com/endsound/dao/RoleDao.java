package com.endsound.dao;

import org.jooq.Configuration;
import org.jooq.Tables;
import org.jooq.impl.DSL;
import org.jooq.tables.MapGroupRole;
import org.jooq.tables.MapUserGroup;
import org.jooq.tables.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao extends org.jooq.tables.daos.RoleDao {
    @Autowired
    public RoleDao(@Qualifier("config")Configuration configuration){super(configuration);}

    public List<String> fetchRolesByUserId(Integer userId){
        Role role = Tables.ROLE;
        MapUserGroup mapUserGroup = Tables.MAP_USER_GROUP;
        MapGroupRole mapGroupRole = Tables.MAP_GROUP_ROLE;

        return DSL.using(configuration())
                .selectDistinct(role.NAME)
                .from(mapUserGroup)
                .innerJoin(mapGroupRole)
                .on(mapUserGroup.GROUP_ID.eq(mapGroupRole.GROUP_ID))
                .innerJoin(role)
                .on(mapGroupRole.ROLE_ID.eq(role.ID))
                .where(mapUserGroup.USER_ID.eq(userId))
                .fetchInto(String.class);
    }

}
